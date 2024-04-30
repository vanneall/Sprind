package ru.point.sprind.entity.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.VerticalCardItemBinding

class ProductViewHolder(
    private val binding: VerticalCardItemBinding,
    private val onClickCard: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
        (view as? FeedProductDto)?.let {
            with(binding) {

                image.load(
                    view.photosUrl[0]
                ) {
                    scale(Scale.FIT)
                }
                name.text = view.name
                price.text = view.price.money.toString()
                root.setOnClickListener {
                    onClickCard(view.id)
                }
            }
        }
    }
}