package ru.point.sprind.entity.deletage.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.shop.ShopVo
import ru.point.sprind.databinding.CategoryCardBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.shop.ShopViewHolder

class ShopDelegate(
    private val onClick: (Long, String) -> Unit
) : Delegate<ShopVo> {
    override fun isSupported(view: ViewObject) = view is ShopVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ShopVo> {
        val binding = CategoryCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ShopViewHolder(
            binding = binding,
            onClick = onClick
        )
    }
}