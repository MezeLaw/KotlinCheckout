package com.mabogado.tulcheckout.dataclass

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp
import javax.validation.constraints.NotBlank

@Table("PRODUCTS")
data class Product(@Id val id: String?, val name: String, val sku : String, val description : String, var price : Double,
                   val is_simple : Boolean,
                   var fecha_create : Timestamp?,
                   val fecha_update : Timestamp?,
                   val fecha_delete : Timestamp?)
