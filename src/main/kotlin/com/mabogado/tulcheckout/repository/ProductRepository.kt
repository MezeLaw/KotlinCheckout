package com.mabogado.tulcheckout.repository

import com.mabogado.tulcheckout.dataclass.Product
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, String> {

        @Query("select * from products")
        fun findProducts(): List<Product>

}