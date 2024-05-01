package ru.point.sprind.entity.viewholder

import coil.load
import coil.size.Scale
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.sprind.databinding.VerticalCardItemBinding

class ProductViewHolder(
    private val binding: VerticalCardItemBinding,
    private val onClickCard: (Long) -> Unit
) : ViewHolderV2<FeedProductDto>(binding.root) {

    override fun bind(view: FeedProductDto) {
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