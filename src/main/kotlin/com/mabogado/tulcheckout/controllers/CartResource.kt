package com.mabogado.tulcheckout.controllers

import com.mabogado.tulcheckout.dataclass.CartProduct
import com.mabogado.tulcheckout.dto.Cart
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
    fun getCartById(@PathVariable id : String) : ResponseEntity<List<CartProduct>>{
    return ResponseEntity<List<CartProduct>>(null,HttpStatus.OK)
    }

    @PostMapping("/cart")
    fun post(@RequestBody cart : Cart) : ResponseEntity<Cart>{
        try {

            if (cart.products.isEmpty()){
                return ResponseEntity(null, HttpStatus.BAD_REQUEST)
            }
            var cartCreated : com.mabogado.tulcheckout.dataclass.Cart = this.cartService.create(cart);
            this.cartProductService.create(cart.products, cartCreated)

            return ResponseEntity<Cart>(cart, HttpStatus.CREATED)
        } catch (e : Exception) {
            return ResponseEntity<Cart>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/cart/{id}")
    fun addProductToCart(@RequestBody cartProduct : CartProduct) : ResponseEntity<String>{
        return ResponseEntity<String>(null, HttpStatus.OK)
    }

    @PutMapping("/cart/{id}/product/{productId}")
    fun updateProduct(@PathVariable id : String, @PathVariable productId : String,
                      @RequestBody cartProduct : CartProduct
    ) : ResponseEntity<String>{
        return ResponseEntity<String>(null, HttpStatus.OK)
    }


    @PostMapping("/cart/{id}/checkout/")
    fun checkout(@PathVariable id : String) : ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>{
        return ResponseEntity<com.mabogado.tulcheckout.dataclass.Cart>(null, HttpStatus.OK)
    }

}