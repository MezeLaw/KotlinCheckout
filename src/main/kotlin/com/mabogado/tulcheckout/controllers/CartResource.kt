package com.mabogado.tulcheckout.controllers

import com.mabogado.tulcheckout.dataclass.CartProduct
import com.mabogado.tulcheckout.dto.Cart
import com.mabogado.tulcheckout.enum.Status
import com.mabogado.tulcheckout.service.CartProductService
import com.mabogado.tulcheckout.service.CartService
import com.mabogado.tulcheckout.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

@RestController
class CartResource(@Autowired val cartService : CartService, @Autowired val cartProductService : CartProductService, @Autowired val productService : ProductService){
    /*
    *Lista de todos los productos de un carrito
    * Agregar, eliminar y modificar productos al carrito
    * Checkout, debe devolver el costo final de los productos del carrito y cambiar su estado a
    completado.
    */

    //TODO notas:

    @GetMapping("/cart/{id}")
    fun getCartById(@PathVariable id : String) : ResponseEntity<Cart>{
        try {
            //TODO refactorizar para quitar exceso de logica aqui
            var cart = this.cartService.getByCartId(id)
            val products = this.cartProductService.getByCartId(id)

            var status = cart?.status.orEmpty()
            if (status.isEmpty()){
                status = "PENDING"
            }
            val cartResult = Cart(cart?.id, Status.valueOf(status), products.orEmpty())
            return ResponseEntity<Cart>(cartResult, HttpStatus.OK)
        } catch (e :Exception) {
            return ResponseEntity<Cart>(null, HttpStatus.OK)
        }
    }

    @PostMapping("/cart/")
    fun post(@RequestBody cart : Cart) : ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>{
        try {
        //Por default los carts tienen un 0,0 en el price y el mismo se actualiza al hacer el checkout
            if (cart.products.isEmpty()){
                return ResponseEntity(null, HttpStatus.BAD_REQUEST)
            }
            var cartCreated : com.mabogado.tulcheckout.dataclass.Cart = this.cartService.create(cart);
            this.cartProductService.create(cart.products, cartCreated)

            return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(cartCreated, HttpStatus.CREATED)
        } catch (e : Exception) {
            return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //Solo agrega productos que no existan en el carrito. Si desea agregar una unidad, utilizar el put con el qty aumentado
    @PostMapping("/cart/{id}/product/")
    fun addProductToCart(@PathVariable id : String, @RequestBody cartProduct : CartProduct) : ResponseEntity<String>{
        try {
            if (cartProduct.product_id.toString().isEmpty()
                ||cartProduct.cart_id.toString().isEmpty()
                ||cartProduct.cart_id.toString()!=id
                ||cartProduct.quantity == null){

                return ResponseEntity<String>(null, HttpStatus.BAD_REQUEST)
            }

            var cart = this.cartService.getByCartId(id)
            var products = this.cartProductService.getCartProducts()

            if (!productExistsOnCart(products, cartProduct) && cart!= null){
                this.cartProductService.create(listOf(cartProduct), cart)
                return ResponseEntity<String>(null, HttpStatus.CREATED)
            }

            return ResponseEntity<String>(null, HttpStatus.BAD_REQUEST)
        } catch ( ex : Exception) {
            return ResponseEntity<String>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
    //Modifica un producto existente en el carrito
    @PutMapping("/cart/{id}/product/")
    fun updateProduct(@PathVariable id : String,
                      @RequestBody cartProduct : CartProduct
    ) : ResponseEntity<CartProduct>{
        try{
            if(id != cartProduct.cart_id.toString() || cartProduct.id.isNullOrBlank()) {
                return ResponseEntity<CartProduct>(null, HttpStatus.BAD_REQUEST)
            }

            var product = this.cartProductService.update(cartProduct)
            return ResponseEntity<CartProduct>(product, HttpStatus.OK)
        } catch ( ex : Exception){
            return ResponseEntity<CartProduct>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Elimina un producto existente en el carrito
    @DeleteMapping("/cart/{id}/product/{cartProductId}")
    fun deleteProduct(@PathVariable id : String,
                      @PathVariable cartProductId : String
    ) : ResponseEntity<String>{
         try{
            this.cartProductService.delete(cartProductId)
            return ResponseEntity<String>(cartProductId, HttpStatus.OK)
        } catch ( ex : Exception){
            return ResponseEntity<String>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PostMapping("/cart/{id}/checkout/")
    fun checkout(@PathVariable id : String) : ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>{
        try {

            var cart = this.cartService.getByCartId(id)

            if (cart == null ){
                return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(null, HttpStatus.NOT_FOUND)
            }

            var productsAvailable = this.productService.findProducts()

            var products = this.cartProductService.getByCartId(id)
            if (products != null) {
                if ( !products.isEmpty()){
                    var cartCheckout = this.cartService.checkout(cart, products, productsAvailable)
                    return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(cartCheckout, HttpStatus.OK)
                }

            }
            return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(null, HttpStatus.BAD_REQUEST)
        } catch ( e : Exception) {
            return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }


    }

    @GetMapping("/cart/")
    fun getCarts() : ResponseEntity<List<com.mabogado.tulcheckout.dataclass.Cart>>{
        return try {
            val carts = this.cartService.getCarts()
            ResponseEntity<List<com.mabogado.tulcheckout.dataclass.Cart>>(carts, HttpStatus.OK)
        } catch (e : Exception) {
            ResponseEntity<List<com.mabogado.tulcheckout.dataclass.Cart>>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/cart/{id}")
    fun deleteCart(@PathVariable id : String) : ResponseEntity<String> {
        try {
            this.cartService.delete(id)
            return ResponseEntity<String>(id, HttpStatus.OK)
        } catch (e : Exception) {
            return ResponseEntity<String>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    fun productExistsOnCart(products : List<CartProduct>, product : CartProduct) : Boolean {
        for(p : CartProduct in products) {
            if (p.product_id == product.product_id) {
                return true
            }
        }
        return false
    }

}