package ru.point.sprind.entity.viewholder.category

import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.point.domain.entity.view.category.CategoryVo
import ru.point.sprind.databinding.CategoryCardBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CategoryViewHolder(
    private val binding: CategoryCardBinding,
    private val onClick: (Long, String) -> Unit
) : ViewHolderV2<CategoryVo>(binding.root) {
    override fun bind(view: CategoryVo) {
        binding.apply {
            categoryTitle.text = view.name
            categoryImage.load(view.photoUrl.url) {
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(20f))
            }
            root.setOnClickListener {
                onClick(view.id, view.name)
            }
        }
    }
}