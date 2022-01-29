package com.mabogado.tulcheckout.service

import com.mabogado.tulcheckout.dataclass.Product
import com.mabogado.tulcheckout.repository.ProductRepository
import org.apache.tomcat.jni.Time
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.util.*


@Service
class ProductService(val db: ProductRepository) {

    fun findProducts(): List<Product> = db.findProducts()

    fun post(product: Product) : Product {
        product.fecha_create = Timestamp(System.currentTimeMillis())
        return db.save(product)
    }

    fun get(id : String) : Product? {
        return db.findById(id).orElse(null)
    }

    fun update(producto : Product) : Product {
        return db.save(producto)
    }

    fun delete(id : String) {
        return db.deleteById(id)
    }
}