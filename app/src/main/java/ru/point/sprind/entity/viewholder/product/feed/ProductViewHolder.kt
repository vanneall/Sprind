package ru.point.sprind.entity.viewholder.product.feed

import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.point.domain.entity.view.product.card.ProductFeedVo
import ru.point.domain.utils.UtilsConst.PICTURE_NOT_FOUND
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductViewHolder(
    private val binding: VerticalCardItemBinding,
    private val onClickCard: (Long) -> Unit,
    private val onBuyClick: (Long) -> Unit,
    private val onFavoriteCheckedChange: (Long, Boolean, (Boolean) -> Unit) -> Unit,
) : ViewHolderV2<ProductFeedVo>(binding.root) {

    override fun bind(view: ProductFeedVo) = with(binding) {
        image.load(
            if (view.photosUrl.isNotEmpty()) view.photosUrl.first()
            else PICTURE_NOT_FOUND
        ) {
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

        buyButton.setOnClickListener {
            onBuyClick(view.id)
        }
    }
}