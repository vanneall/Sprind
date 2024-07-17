package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.sprind.databinding.NestedRecyclerViewBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.NestedRecyclerViewViewHolder

class NestedRecyclerViewDelegate(
    private val delegates: List<Delegate<*>>,
    private val useViewPagerEffect: Boolean = false,
    private val itemDecoration: RecyclerView.ItemDecoration? = null
) : Delegate<NestedRecyclerViewVo> {

    override fun isSupported(view: ViewObject) = view is NestedRecyclerViewVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<NestedRecyclerViewVo> {
        val binding = NestedRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NestedRecyclerViewViewHolder(
            binding = binding,
            delegates = delegates,
            useViewPagerEffect = useViewPagerEffect,
            itemDecoration = itemDecoration
        )
    }
}