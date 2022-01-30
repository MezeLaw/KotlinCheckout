package com.mabogado.tulcheckout.dataclass

import org.springframework.data.annotation.Id
import java.sql.Timestamp

data class Cart(@Id val id : String?, var status : String,
                var fecha_create : Timestamp?,
                var fecha_update : Timestamp?,
                var fecha_delete : Timestamp?)
