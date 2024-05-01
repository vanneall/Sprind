package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductTitleView
import ru.point.sprind.databinding.ProductCardTitleBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.ProductTitleViewHolder

class ProductTitleDelegate : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is ProductTitleView
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2 {
        val binding = ProductCardTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductTitleViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: ViewHolderV2) {
        (viewHolder as? ProductTitleViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}