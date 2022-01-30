package com.mabogado.tulcheckout.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

@RestController
class CartResource {
    /*
    *Lista de todos los productos de un carrito
    * Agregar, eliminar y modificar productos al carrito
    * Checkout, debe devolver el costo final de los productos del carrito y cambiar su estado a
    completado.
    */

    //TODO notas:

    @GetMapping("/car/{id}")
    fun getCartById(@PathVariable id : String) : ResponseEntity<List<CartProduct>>{

    }


    @PostMapping("/cart/{id}")
    fun addProductToCart(@RequestBody cartProduct : CartProduct) : ResponseEntity<String>{

    }

    @PutMapping("/cart/{id}/product/{productId}")
    fun updateProduct(@PathVariable id : String, @PathVariable productId : String,
                      @RequestBody cartProduct : CartProduct) : ResponseEntity<String>{

    }


    @PostMapping("/cart/{id}/checkout/")
    fun checkout(@PathVariable id : String) : ResponseEntity<Cart>{

    }

}