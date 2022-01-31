package com.mabogado.tulcheckout.controllers

import com.mabogado.tulcheckout.dataclass.CartProduct
import com.mabogado.tulcheckout.dto.Cart
import com.mabogado.tulcheckout.enum.Status
import com.mabogado.tulcheckout.service.CartProductService
import com.mabogado.tulcheckout.service.CartService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CartResource(@Autowired val cartService : CartService, @Autowired val cartProductService : CartProductService){
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

    @PostMapping("/cart/{id}")
    fun addProductToCart(@PathVariable id : String, @RequestBody cartProduct : CartProduct) : ResponseEntity<String>{
        return ResponseEntity<String>(null, HttpStatus.OK)
    }

    @PutMapping("/cart/{id}/product/")
    fun updateProduct(@PathVariable id : String,
                      @RequestBody cartProduct : CartProduct
    ) : ResponseEntity<String>{
        return ResponseEntity<String>(null, HttpStatus.OK)
    }


    @PostMapping("/cart/{id}/checkout/")
    fun checkout(@PathVariable id : String) : ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>{
        return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(null, HttpStatus.OK)
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

}