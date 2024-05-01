package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CartProductVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartProductCardBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartProductViewHolder

class CartProductDelegate : Delegate {

    override fun forItem(view: ListView): Boolean {
        return view is CartProductVo
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2 {
        val binding = CartProductCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartProductViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: ViewHolderV2) {
        (viewHolder as? CartProductViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}