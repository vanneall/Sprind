package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductTitle
import ru.point.sprind.databinding.ProductCardTitleBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.product.card.ProductTitleViewHolder

class ProductTitleDelegate : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is ProductTitle
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ProductCardTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductTitleViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? ProductTitleViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}