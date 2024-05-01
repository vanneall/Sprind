package ru.point.sprind.entity.deletage

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.Request
import ru.point.sprind.databinding.RequestHistoryItemBinding
import ru.point.sprind.entity.viewholder.RequestViewHolder
import ru.point.sprind.entity.viewholder.ViewHolderV2

class RequestDelegate : Delegate<Request> {

    override fun isSupported(view: ViewObject): Boolean = view is Request

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<Request> {
        val binding = RequestHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RequestViewHolder(binding = binding)
    }
}