package ru.point.sprind.entity.deletage.product.request

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.search.EmptyRequestResultVo
import ru.point.sprind.databinding.RequestResultEmptyBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.request.EmptyRequestResultViewHolder

class EmptyRequestResultDelegate : Delegate<EmptyRequestResultVo> {
    override fun isSupported(view: ViewObject): Boolean = view is EmptyRequestResultVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<EmptyRequestResultVo> {
        val binding = RequestResultEmptyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmptyRequestResultViewHolder(binding = binding)
    }
}