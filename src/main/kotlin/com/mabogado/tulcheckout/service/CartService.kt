package com.mabogado.tulcheckout.service


import com.mabogado.tulcheckout.dto.Cart
import com.mabogado.tulcheckout.enum.Status
import com.mabogado.tulcheckout.repository.CartRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Time
import java.sql.Timestamp

@Service
class CartService(@Autowired val cartRepo : CartRepository) {


    fun checkout(){

    }

    fun create(cart : Cart) : com.mabogado.tulcheckout.dataclass.Cart {

        var cartEntity : com.mabogado.tulcheckout.dataclass.Cart = com.mabogado.tulcheckout.dataclass.Cart(
            null,
            Status.PENDING.toString(),
            0.0,
            Timestamp(System.currentTimeMillis()), null, null)

        return this.cartRepo.save(cartEntity)
    }

    fun getByCartId(id : String) : com.mabogado.tulcheckout.dataclass.Cart? {
        return this.cartRepo.findById(id).orElse(null)
    }

    fun getCarts() : List<com.mabogado.tulcheckout.dataclass.Cart> {
        return this.cartRepo.findProducts()
    }

}