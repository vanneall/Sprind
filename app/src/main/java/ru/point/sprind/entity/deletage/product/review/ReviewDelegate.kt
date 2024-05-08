package ru.point.sprind.entity.deletage.product.review

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ReviewVo
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.databinding.ReviewBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.review.ReviewViewHolder

class ReviewDelegate : Delegate<ReviewVo> {

    override fun isSupported(view: ViewObject) = view is ReviewVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ReviewVo> {
        val binding = ReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding = binding)
    }
}