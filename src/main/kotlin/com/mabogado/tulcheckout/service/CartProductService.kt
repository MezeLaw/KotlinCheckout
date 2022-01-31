package com.mabogado.tulcheckout.service

import com.mabogado.tulcheckout.dataclass.Cart
import com.mabogado.tulcheckout.dataclass.CartProduct
import com.mabogado.tulcheckout.repository.CartProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartProductService(@Autowired val db : CartProductRepository) {

    fun getByCartId(id : String) : List<CartProduct>?{
        return this.db.getByCartId(id)
    }

    fun create(products : List<CartProduct>, cart : Cart){
            for (product: CartProduct in products) {
                product.cart_id = cart.id
                this.db.save(product)

            }
    }

    fun getCartProducts(): List<CartProduct> {
        return this.db.getCartProducts()
    }

    fun update(){

    }

    fun delete(){

    }
}