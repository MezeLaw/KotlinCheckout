package com.mabogado.tulcheckout.dataclass

import java.sql.Timestamp

data class CartProduct(val id : String?,
                       var cart_id : String?,
                       val product_id : String?,
                       val quantity : Int?,
                       val fecha_create : Timestamp?,
                       val fecha_update : Timestamp?,
                       val fecha_delete : Timestamp?
)
