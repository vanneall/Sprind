package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.cart.CartPromocodeVo
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.databinding.CartPromocodeBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartPromocodeViewHolder

class CartPromocodeDelegate: Delegate<CartPromocodeVo> {

    override fun isSupported(view: ViewObject) = view is CartPromocodeVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CartPromocodeVo> {
        val binding = CartPromocodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CartPromocodeViewHolder(binding)
    }
}