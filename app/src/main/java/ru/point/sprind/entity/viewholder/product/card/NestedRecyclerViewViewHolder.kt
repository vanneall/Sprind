package ru.point.sprind.entity.viewholder.product.card

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.PagerSnapHelper
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.sprind.adapters.MordaAdapter
import ru.point.sprind.databinding.NestedRecyclerViewBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class NestedRecyclerViewViewHolder(
    private val binding: NestedRecyclerViewBinding,
    delegates: List<Delegate<*>>,
    useViewPagerEffect: Boolean,
    lifecycle: Lifecycle
) : ViewHolderV2<NestedRecyclerViewVo>(binding.root) {

    private val adapter: MordaAdapter = MordaAdapter(delegates = delegates)

    init {
        lifecycle.addObserver(adapter)
    }

    init {
        binding.nestedRecyclerview.adapter = adapter
        binding.nestedRecyclerview.tag = "nested recycler"
        if (useViewPagerEffect) {
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.nestedRecyclerview)
        }
    }

    override fun bind(view: NestedRecyclerViewVo) {
        adapter.views = view.viewObjects
    }
}