package ru.point.sprind.entity.deletage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.FeedProductDto
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.sprind.entity.viewholder.ProductViewHolder

class ProductDelegate : Delegate {

    override fun forItem(view: ru.point.domain.entity.ListView) = view is FeedProductDto

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = VerticalCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun bindViewHolder(
        view: ru.point.domain.entity.ListView,
        viewHolder: RecyclerView.ViewHolder,
    ) {
        (viewHolder as? ProductViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}