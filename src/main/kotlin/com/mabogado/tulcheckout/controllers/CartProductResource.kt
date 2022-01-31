package com.mabogado.tulcheckout.controllers

import com.mabogado.tulcheckout.dataclass.CartProduct
import com.mabogado.tulcheckout.service.CartProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CartProductResource(@Autowired val cartProductService : CartProductService) {

    @GetMapping("/cartProduct/")
    fun getCarProducts(): ResponseEntity<List<CartProduct>> {
        return try{
            val carProducts = this.cartProductService.getCartProducts()
            ResponseEntity<List<CartProduct>>(carProducts, HttpStatus.OK)
        } catch (e : Exception) {
            ResponseEntity<List<CartProduct>>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}