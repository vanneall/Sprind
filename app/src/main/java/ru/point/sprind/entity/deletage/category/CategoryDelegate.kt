package ru.point.sprind.entity.deletage.category

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.category.CategoryVo
import ru.point.sprind.databinding.CategoryCardBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.category.CategoryViewHolder

class CategoryDelegate : Delegate<CategoryVo> {
    override fun isSupported(view: ViewObject) = view is CategoryVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CategoryVo> {
        val binding = CategoryCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CategoryViewHolder(binding = binding)
    }
}