package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CartEmptyVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartEmptyWarningBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartEmptyViewHolder

class CartEmptyDelegate : Delegate<CartEmptyVo> {

    override fun isSupported(view: ListView) = view is CartEmptyVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CartEmptyVo> {
        val binding = CartEmptyWarningBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartEmptyViewHolder(binding)
    }
}