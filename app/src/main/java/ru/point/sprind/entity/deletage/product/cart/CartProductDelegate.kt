package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.CartProductVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartProductCardBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.product.cart.CartProductViewHolder

class CartProductDelegate : Delegate {

    override fun forItem(view: ListView): Boolean {
        return view is CartProductVo
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CartProductCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartProductViewHolder(binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? CartProductViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}