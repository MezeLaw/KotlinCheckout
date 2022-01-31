package com.mabogado.tulcheckout.dataclass

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("CARTS")
data class Cart(@Id val id: String?,
                var status : String?,
                var price : Double?,
                var fecha_create : Timestamp?,
                var fecha_update : Timestamp?,
                var fecha_delete : Timestamp?)
