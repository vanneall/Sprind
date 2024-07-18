package ru.point.sprind.entity.deletage.product.request

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.search.SearchRequestVo
import ru.point.sprind.databinding.RequestHistoryItemBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.product.request.RequestViewHolder
import ru.point.sprind.entity.viewholder.ViewHolderV2

class RequestDelegate : Delegate<SearchRequestVo> {

    override fun isSupported(view: ViewObject): Boolean = view is SearchRequestVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<SearchRequestVo> {
        val binding = RequestHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RequestViewHolder(binding = binding)
    }
}