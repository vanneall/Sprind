package ru.point.sprind.entity.viewholder.product.favorites

import ru.point.domain.entity.view.favorites.EmptyFavoritesVo
import ru.point.sprind.databinding.FavoritesEmptyWarningBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class EmptyFavoritesViewHolder(
    private val binding: FavoritesEmptyWarningBinding,
) : ViewHolderV2<EmptyFavoritesVo>(binding.root) {

    override fun bind(view: EmptyFavoritesVo) {}
}