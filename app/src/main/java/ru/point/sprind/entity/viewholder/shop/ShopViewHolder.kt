package ru.point.sprind.entity.viewholder.shop

import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.point.domain.entity.view.shop.ShopVo
import ru.point.domain.utils.UtilsConst.PICTURE_NOT_FOUND
import ru.point.sprind.databinding.CategoryCardBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ShopViewHolder(
    private val binding: CategoryCardBinding
) : ViewHolderV2<ShopVo>(binding.root) {
    override fun bind(view: ShopVo) {
        binding.apply {
            categoryTitle.text = view.name
            categoryImage.load(view.photoUrl ?: PICTURE_NOT_FOUND) {
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(20f))
            }
        }
    }
}