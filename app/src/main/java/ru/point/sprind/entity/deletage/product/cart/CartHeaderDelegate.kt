package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.cart.CartHeaderVo
import ru.point.sprind.databinding.CartDeliveryHeaderBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartHeaderViewHolder

class CartHeaderDelegate : Delegate<CartHeaderVo> {

    override fun isSupported(view: ViewObject) = view is CartHeaderVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CartHeaderVo> {
        val binding = CartDeliveryHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CartHeaderViewHolder(binding = binding)
    }
}