package ru.point.sprind.entity.deletage.order

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.order.OrderVo
import ru.point.sprind.databinding.OrderCardBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.order.OrderViewHolder

class OrderDelegate : Delegate<OrderVo> {

    override fun isSupported(view: ViewObject) = view is OrderVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<OrderVo> {
        val binding = OrderCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OrderViewHolder(binding = binding)
    }
}