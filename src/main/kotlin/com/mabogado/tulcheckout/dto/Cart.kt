package com.mabogado.tulcheckout.dto

import com.mabogado.tulcheckout.dataclass.CartProduct
import com.mabogado.tulcheckout.enum.Status

data class Cart (val id: String?, var status: Status?, val products: List<CartProduct>)