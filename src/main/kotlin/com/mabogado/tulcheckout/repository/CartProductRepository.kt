package com.mabogado.tulcheckout.repository

import com.mabogado.tulcheckout.dataclass.CartProduct
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface CartProductRepository: CrudRepository<CartProduct, String> {

    @Query("select * from CART_PRODUCTS where cart_id = :id and fecha_delete is null")
    fun getByCartId(id : String) : List<CartProduct>

    @Query("select * from CART_PRODUCTS")
    fun getCartProducts() : List<CartProduct>

    //TODO ver logic delete


}