package ru.point.sprind.entity.viewholder.product.feed

import android.view.MotionEvent
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import ru.point.domain.entity.view.product.card.FeedProductVo
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductViewHolder(
    private val binding: VerticalCardItemBinding,
    private val onClickCard: (Long) -> Unit,
    private val onBuyClick: (Long) -> Unit,
    private val onFavoriteCheckedChange: (Long, Boolean) -> Unit
) : ViewHolderV2<FeedProductVo>(binding.root) {

    override fun bind(view: FeedProductVo) {
        binding.apply {
            root.setOnClickListener { onClickCard(view.id) }

            image.load(view.imagesUrl.first().url) {
                scale(Scale.FIT)
                transformations(RoundedCornersTransformation(50f, 50f))
            }

            name.text = view.name
            price.text = view.price
            rating.text = view.rating.toString()

            isFavorite.isChecked = view.isFavorite

            isFavorite.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    onFavoriteCheckedChange(view.id, !isFavorite.isChecked)
                    true
                } else {
                    false
                }
            }

            buyButton.isEnabled = !view.inCart
            buyButton.setOnClickListener { onBuyClick(view.id) }
        }
    }

    override fun bindWithPayload(view: FeedProductVo, payload: MutableList<Any>) {
        binding.apply {
            val isFavoriteCheckedChanged = payload.last() as? Boolean
            isFavoriteCheckedChanged?.let { isFavorite.isChecked = !isFavorite.isChecked }
        }
    }
}