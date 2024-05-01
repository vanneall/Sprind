package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CartEmptyVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartEmptyWarningBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartEmptyViewHolder

class CartEmptyDelegate : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is CartEmptyVo
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2 {
        val binding = CartEmptyWarningBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartEmptyViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: ViewHolderV2) {}
}