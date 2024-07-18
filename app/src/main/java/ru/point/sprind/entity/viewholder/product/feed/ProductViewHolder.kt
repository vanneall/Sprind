package ru.point.sprind.entity.viewholder.product.feed

import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.point.domain.entity.view.product.card.FeedProductVo
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductViewHolder(
    private val binding: VerticalCardItemBinding,
    private val onClickCard: (Long) -> Unit,
    private val onBuyClick: (Long) -> Unit,
    private val onFavoriteCheckedChange: (Long, Boolean, (Boolean) -> Unit) -> Unit,
) : ViewHolderV2<FeedProductVo>(binding.root) {

    override fun bind(view: FeedProductVo) {
        binding.apply {

            image.load(view.imagesUrl.first().url) {
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(50f, 50f))
            }

            name.text = view.name
            price.text = view.price
            isFavorite.isChecked = view.isFavorite

            isFavorite.setOnClickListener {
                val isChecked = isFavorite.isChecked
                onFavoriteCheckedChange(view.id, isChecked) { isSuccess ->
                    isFavorite.isChecked = if (isSuccess) isChecked else !isChecked
                }
            }

            root.setOnClickListener {
                onClickCard(view.id)
            }

            if (view.inCart) {
                buyButton.isEnabled = false
            }

            buyButton.setOnClickListener { onBuyClick(view.id) }
        }
    }
}