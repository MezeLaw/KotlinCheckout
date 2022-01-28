package com.mabogado.tulcheckout.controllers

import com.mabogado.tulcheckout.dataclass.Product
import com.mabogado.tulcheckout.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductResource(@Autowired val service : ProductService) {

    @GetMapping("/ping")
    fun healthcheck(): String = "pong"

    @GetMapping("/product/")
    fun index(): List<Product> = service.findProducts()

    @PostMapping("/product/")
    fun post(@RequestBody product: Product) {
        if (product.is_simple) {
            val auxPrice : Double = product.price / 2
            product.price = auxPrice
        }
        service.post(product)
    }

}