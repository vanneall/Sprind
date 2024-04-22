package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductImage
import ru.point.sprind.databinding.ProductCardImageBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.product.card.ProductImageViewHolder

class ProductImageDelegate : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is ProductImage
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ProductCardImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductImageViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? ProductImageViewHolder)?.let {
            viewHolder.bind(productImage = view)
        }
    }
}