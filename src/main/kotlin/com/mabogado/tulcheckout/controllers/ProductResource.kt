package com.mabogado.tulcheckout.controllers

import com.mabogado.tulcheckout.dataclass.Product
import com.mabogado.tulcheckout.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.swing.text.html.Option

@RestController
class ProductResource(@Autowired val service : ProductService) {

    @GetMapping("/ping")
    fun healthcheck(): String = "pong"

    @GetMapping("/product/")
    fun index(): ResponseEntity<String> {
        val products : List<Product> = service.findProducts()
        if (products.isNotEmpty()){
            return ResponseEntity<String>(products.toString(), HttpStatus.OK)
        }

        return ResponseEntity<String>(null, HttpStatus.OK)
    }

    @PostMapping("/product/")
    fun post(@RequestBody product: Product) : ResponseEntity<String> {
        if (product.is_simple) {
            val auxPrice : Double = product.price / 2
            product.price = auxPrice
        }
        val result = service.post(product)

        return ResponseEntity<String>(result.toString(), HttpStatus.OK)
    }

    @GetMapping("/product/{id}")
    fun get(@PathVariable id : String): Optional<Product> {
        //TODO validar pathVariable tratar de manejar el retorno de Optionals para responder como en el post
       return service.get(id)
    }

    @PutMapping("/product/{id}")
    fun update(@PathVariable id : String, @RequestBody producto : Product) : Product{
        //TODO validar id en body y path variable sean iguales y otros
        return service.update(producto)
    }

    @DeleteMapping("/product/{id}")
    fun delete(@PathVariable id : String) {
        return service.delete(id)
    }

}