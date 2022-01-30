package com.mabogado.tulcheckout.controllers

import com.mabogado.tulcheckout.dataclass.Product
import com.mabogado.tulcheckout.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.xml.ws.Response

@RestController
class ProductResource(@Autowired val service : ProductService) {

    @GetMapping("/ping")
    fun healthcheck(): String = "pong"

    @GetMapping("/product/")
    fun index(): ResponseEntity<List<Product>> {
        try {
            val products : List<Product> = service.findProducts()
            if (products.isNotEmpty()){
                return ResponseEntity<List<Product>>(products, HttpStatus.OK)
            }
            return ResponseEntity<List<Product> >(null, HttpStatus.OK)
        } catch (e : Exception) {
            return ResponseEntity<List<Product>>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/product/")
    fun post(@RequestBody product: Product): ResponseEntity<Product> {
        if (!product.is_simple) {
            val auxPrice: Double = product.price / 2
            product.price = auxPrice
        }
        return try {
            val result = service.post(product)
            ResponseEntity<Product>(result, HttpStatus.OK)

        } catch (e : Exception) {
            ResponseEntity<Product>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @GetMapping("/product/{id}")
    fun get(@PathVariable id : String): ResponseEntity<Product> {

        try {
            val result = service.get(id)
            //TODO revisar una sintaxis mas bonita para este if (revisar comunidad Kotlin)
            if ( result == null) {
                return ResponseEntity<Product>(null, HttpStatus.NOT_FOUND)
            }
            return ResponseEntity<Product>(result, HttpStatus.OK)
        } catch ( e : Exception){
            return ResponseEntity<Product>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @PutMapping("/product/{id}")
    fun update(@PathVariable id : String, @RequestBody producto : Product) : ResponseEntity<Product>{

        if (id != producto.id.toString()) {
            return ResponseEntity<Product>( null, HttpStatus.BAD_REQUEST)
        }

        return try {
            val result = service.update(producto)
            ResponseEntity<Product>(result, HttpStatus.OK)
        } catch ( e : Exception){
            ResponseEntity<Product>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/product/{id}")
    fun delete(@PathVariable id : String) : ResponseEntity<Int>{
          return try {
              service.delete(id)
              ResponseEntity<Int>(null, HttpStatus.OK)
          } catch ( e :Exception) {
              ResponseEntity<Int>(null, HttpStatus.INTERNAL_SERVER_ERROR)
          }
    }

}