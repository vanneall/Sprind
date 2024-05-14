package ru.point.sprind.entity.viewholder.product.review

import ru.point.domain.entity.view.review.ReviewVo
import ru.point.sprind.databinding.ReviewBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ReviewViewHolder(
    private val binding: ReviewBinding,
) : ViewHolderV2<ReviewVo>(binding.root) {

    override fun bind(view: ReviewVo) {
        with(binding) {
            textRating.text = view.rating.toString()
            textUsername.text = view.username
            descriptionText.text = view.description
            disadvantageText.text = view.disadvantages
            advantageText.text = view.advantages
        }
    }
}