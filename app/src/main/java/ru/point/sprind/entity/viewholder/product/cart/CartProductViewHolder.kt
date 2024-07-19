package ru.point.sprind.entity.viewholder.product.cart

import android.view.MotionEvent
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.sprind.databinding.CartProductCardBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartProductViewHolder(
    private val binding: CartProductCardBinding,
    private val onClick: (Long) -> Unit,
    private val onFavoriteCheckedChange: (Long, Boolean) -> Unit,
    private val delete: (Long) -> Unit
) : ViewHolderV2<CartProductVo>(binding.root) {

    override fun bind(view: CartProductVo) {
        binding.apply {
            image.load(view.imageUrl.url) {
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(30f))
            }
            name.text = view.name
            price.text = view.price

            isFavorite.isChecked = view.isFavorite
            isFavorite.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    onFavoriteCheckedChange(view.id, !isFavorite.isChecked)
                    true
                } else {
                    false
                }
            }

            delete.setOnClickListener {
                delete(view.id)
            }

            root.setOnClickListener {
                onClick(view.id)
            }
        }
    }

    override fun bindWithPayload(view: CartProductVo, payload: MutableList<Any>) {
        binding.apply {
            val isFavoriteCheckedChanged = payload.last() as? Boolean
            isFavoriteCheckedChanged?.let { isFavorite.isChecked = !isFavorite.isChecked }
        }
    }
}