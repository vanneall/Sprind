package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.sprind.adapters.SprindDefaultAdapter
import ru.point.sprind.databinding.NestedRecyclerViewBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class NestedRecyclerViewViewHolder(
    binding: NestedRecyclerViewBinding,
    delegates: List<Delegate<*>>,
    useViewPagerEffect: Boolean,
    itemDecoration: RecyclerView.ItemDecoration? = null
) : ViewHolderV2<NestedRecyclerViewVo>(binding.root) {

    private val adapter: SprindDefaultAdapter = SprindDefaultAdapter(delegates = delegates)

    init {
        binding.nestedRecyclerview.apply {
            adapter = this@NestedRecyclerViewViewHolder.adapter
            if (itemDecoration != null) addItemDecoration(itemDecoration)

            if (useViewPagerEffect) {
                PagerSnapHelper().attachToRecyclerView(this)
            }
        }

    }

    override fun bind(view: NestedRecyclerViewVo) {
        adapter.submitList(view.viewObjects)
    }
}