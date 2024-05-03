package ru.point.sprind.entity.viewholder

import coil.load
import coil.size.Scale
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.sprind.databinding.VerticalCardItemBinding

class ProductViewHolder(
    private val binding: VerticalCardItemBinding,
    private val onClickCard: (Long) -> Unit,
    private val onBuyClick: (Long) -> Unit,
    private val onFavoriteCheckedChange: (Long, Boolean) -> Unit,
) : ViewHolderV2<FeedProductDto>(binding.root) {

    override fun bind(view: FeedProductDto) = with(binding) {
        image.load(
            view.photosUrl[0]
        ) {
            scale(Scale.FIT)
        }
        name.text = view.name
        price.text = view.price.money.toString()
        isFavorite.isChecked = view.isFavorite
        isFavorite.setOnCheckedChangeListener { _, isChecked ->
            onFavoriteCheckedChange(view.id, isChecked)
        }

        root.setOnClickListener {
            onClickCard(view.id)
        }

        buyButton.setOnClickListener {
            onBuyClick(view.id)
        }
    }
}