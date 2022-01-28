package com.mabogado.tulcheckout.service

import com.mabogado.tulcheckout.dataclass.Product
import com.mabogado.tulcheckout.repository.ProductRepository
import org.springframework.stereotype.Service


@Service
class ProductService(val db: ProductRepository) {

    fun findProducts(): List<Product> = db.findProducts()

    fun post(product: Product){
        db.save(product)
    }
}