package ru.point.sprind.entity.deletage.product.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.favorites.EmptyFavoritesVo
import ru.point.sprind.databinding.FavoritesEmptyWarningBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.favorites.EmptyFavoritesViewHolder

class EmptyFavoritesDelegate : Delegate<EmptyFavoritesVo> {

    override fun isSupported(view: ViewObject) = view is EmptyFavoritesVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<EmptyFavoritesVo> {
        val binding = FavoritesEmptyWarningBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return EmptyFavoritesViewHolder(binding = binding)
    }
}