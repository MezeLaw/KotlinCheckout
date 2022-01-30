package com.mabogado.tulcheckout.repository

import com.mabogado.tulcheckout.dataclass.CartProduct
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CartProductRepository: CrudRepository<CartProduct, String> {

    @Query("select * from cart_products where id = 1?")
    fun getByCartId(id : String) : List<CartProduct>

    //TODO ver logic delete


}