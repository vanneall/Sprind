package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.sprind.databinding.CartProductCardBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartProductViewHolder

class CartProductDelegate(
    private val onClick: (Long) -> Unit
) : Delegate<CartProductVo> {

    override fun isSupported(view: ViewObject) = view is CartProductVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CartProductVo> {
        val binding = CartProductCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartProductViewHolder(binding, onClick)
    }
}