package ru.point.sprind.entity.deletage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.sprind.entity.viewholder.ProductViewHolder

class ProductDelegate(
    private val onClickCard: (Long) -> Unit
) : Delegate {

    override fun forItem(view: ListView) = view is FeedProductDto

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = VerticalCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding, onClickCard)
    }

    override fun bindViewHolder(
        view: ListView,
        viewHolder: RecyclerView.ViewHolder,
    ) {
        (viewHolder as? ProductViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}