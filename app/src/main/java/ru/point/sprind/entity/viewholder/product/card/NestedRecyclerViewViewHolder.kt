package ru.point.sprind.entity.viewholder.product.card

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo
import ru.point.domain.entity.view.product.info.NestedRecyclerViewVo.Represented
import ru.point.sprind.R
import ru.point.sprind.adapters.SprindDefaultAdapter
import ru.point.sprind.databinding.NestedRecyclerViewBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class NestedRecyclerViewViewHolder(
    private val binding: NestedRecyclerViewBinding,
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
        val title = mapNestedRecyclerTitle(view.represented, binding.root.context)
        title?.let {
            binding.title.text = title
            binding.title.visibility = View.VISIBLE
        }

        adapter.submitList(view.viewObjects)
    }

    private fun mapNestedRecyclerTitle(
        represented: Represented,
        context: Context,
    ): String? {
        return when (represented) {
            Represented.UNDEFINED -> null
            Represented.FAVORITE -> context.resources.getString(R.string.products_in_favorite)
        }
    }
}