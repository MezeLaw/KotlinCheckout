package com.mabogado.tulcheckout.repository

import com.mabogado.tulcheckout.dataclass.Cart
import com.mabogado.tulcheckout.dataclass.Product
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CartRepository : CrudRepository<Cart, String> {

    @Query("select * from CARTS")
    fun findProducts(): List<Cart>
}