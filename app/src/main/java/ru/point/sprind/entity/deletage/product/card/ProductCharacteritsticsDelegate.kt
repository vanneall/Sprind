package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductDescription
import ru.point.sprind.databinding.ProductCardDescriptionBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.product.card.ProductDescriptionViewHolder

class ProductDescriptionDelegate : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is ProductDescription
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ProductCardDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductDescriptionViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? ProductDescriptionViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}