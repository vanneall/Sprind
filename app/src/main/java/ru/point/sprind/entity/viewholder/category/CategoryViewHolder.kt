package ru.point.sprind.entity.viewholder.category

import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.point.domain.entity.view.category.CategoryVo
import ru.point.domain.utils.UtilsConst.PICTURE_NOT_FOUND
import ru.point.sprind.databinding.CategoryCardBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CategoryViewHolder(
    private val binding: CategoryCardBinding
) : ViewHolderV2<CategoryVo>(binding.root) {
    override fun bind(view: CategoryVo) {
        binding.apply {
            categoryTitle.text = view.name
            categoryImage.load(view.photoUrl ?: PICTURE_NOT_FOUND) {
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(20f))
            }
        }
    }
}