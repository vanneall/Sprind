package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CartSummaryVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartSummaryBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartSummaryViewHolder

class CartSummaryDelegate : Delegate<CartSummaryVo> {

    override fun isSupported(view: ListView) = view is CartSummaryVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CartSummaryVo> {
        val binding = CartSummaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CartSummaryViewHolder(binding = binding)
    }
}