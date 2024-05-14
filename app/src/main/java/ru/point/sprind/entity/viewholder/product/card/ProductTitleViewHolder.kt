package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.sprind.databinding.ProductCardTitleBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductTitleViewHolder(
    private val binding: ProductCardTitleBinding,
    private val onFavoriteCheckedChange: (Boolean, (Boolean) -> Unit) -> Unit,
) : ViewHolderV2<ProductTitleVo>(binding.root) {

    override fun bind(view: ProductTitleVo) {
        with(binding) {
            title.text = view.title
            price.text = view.price
            rating.text = view.rating
            favoriteCheckbox.isChecked = view.isFavorite

            favoriteCheckbox.setOnClickListener {
                val isChecked = favoriteCheckbox.isChecked
                onFavoriteCheckedChange(isChecked) { isSuccess ->
                    favoriteCheckbox.isChecked = if (isSuccess) isChecked else !isChecked
                }
            }
        }
    }
}