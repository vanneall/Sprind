package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.PagerSnapHelper
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.sprind.adapters.SprindDefaultAdapter
import ru.point.sprind.databinding.NestedRecyclerViewBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class NestedRecyclerViewViewHolder(
    binding: NestedRecyclerViewBinding,
    delegates: List<Delegate<*>>,
    useViewPagerEffect: Boolean,
) : ViewHolderV2<NestedRecyclerViewVo>(binding.root) {

    private val adapter: SprindDefaultAdapter = SprindDefaultAdapter(delegates = delegates)

    init {
        binding.nestedRecyclerview.adapter = adapter
        binding.nestedRecyclerview.tag = "nested recycler"
        if (useViewPagerEffect) {
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.nestedRecyclerview)
        }
    }

    override fun bind(view: NestedRecyclerViewVo) {
        adapter.submitList(view.viewObjects)
    }
}