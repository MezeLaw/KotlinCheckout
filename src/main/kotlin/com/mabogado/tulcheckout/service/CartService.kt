package com.mabogado.tulcheckout.service


import com.mabogado.tulcheckout.dataclass.CartProduct
import com.mabogado.tulcheckout.dataclass.Product
import com.mabogado.tulcheckout.dto.Cart
import com.mabogado.tulcheckout.enum.Status
import com.mabogado.tulcheckout.repository.CartRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class CartService(@Autowired val cartRepo : CartRepository) {


    fun checkout(cart : com.mabogado.tulcheckout.dataclass.Cart, products : List<CartProduct>, productsAvailable : List<Product>) : com.mabogado.tulcheckout.dataclass.Cart{
        return this.cartRepo.save(calculateFinalPriceAndChangeStatus(cart, products, productsAvailable))
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

    fun delete(id : String){
        return this.cartRepo.deleteById(id)
    }

    //Esta es una opcion tambien se podria ir actualizando cada vez que se agrega/modifica/deletea un producto para al hacer checkout solo
    //cambiar el status y retornar la suma de los productos.
    fun calculateFinalPriceAndChangeStatus(cart : com.mabogado.tulcheckout.dataclass.Cart, productsOnCart : List<CartProduct>, productsAvailable : List<Product>) : com.mabogado.tulcheckout.dataclass.Cart {
        var totalPrice = 0.0
        for(product : CartProduct in productsOnCart) {
            for ( productAvailable : Product in productsAvailable){
                if(productAvailable.id == product.product_id){
                    totalPrice += product.quantity.toDouble() * productAvailable.price
                }
            }
        }

        cart.price = totalPrice
        cart.status = Status.COMPLETED.toString()
        return cart
    }

}