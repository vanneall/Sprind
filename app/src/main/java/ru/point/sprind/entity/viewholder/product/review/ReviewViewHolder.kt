package ru.point.sprind.entity.viewholder.product.review

import ru.point.domain.entity.view.ReviewVo
import ru.point.sprind.databinding.ReviewBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ReviewViewHolder(
    private val binding: ReviewBinding,
) : ViewHolderV2<ReviewVo>(binding.root) {

    override fun bind(view: ReviewVo) {
        with(binding) {
            textRating.text = view.rating.toString()
            textUsername.text = view.username
            description.text = view.description
        }
    }
}