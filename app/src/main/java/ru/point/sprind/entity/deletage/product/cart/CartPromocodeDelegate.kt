package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.CartPromocodeVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartPromocodeBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.product.cart.CartPromocodeViewHolder

class CartPromocodeDelegate: Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is CartPromocodeVo
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CartPromocodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CartPromocodeViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? CartPromocodeViewHolder)?.let {
            viewHolder.bind()
        }
    }
}