package com.mabogado.tulcheckout.dataclass

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("CART_PRODUCTS")
data class CartProduct(@Id val id : String?,
                       var cart_id : String?,
                       val product_id : String?,
                       val quantity : Int,
                       val fecha_create : Timestamp?,
                       val fecha_update : Timestamp?,
                       val fecha_delete : Timestamp?
)
