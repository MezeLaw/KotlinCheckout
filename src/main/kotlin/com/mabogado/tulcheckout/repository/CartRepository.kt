package com.mabogado.tulcheckout.repository

import com.mabogado.tulcheckout.dataclass.Cart
import org.springframework.data.repository.CrudRepository

interface CartRepository : CrudRepository<Cart, String> {

    //@Query("select * from carts")
    //fun findProductsBy(): List<Cart>

}