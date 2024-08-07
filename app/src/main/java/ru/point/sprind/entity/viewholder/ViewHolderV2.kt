package ru.point.sprind.entity.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.ViewObject

abstract class ViewHolderV2<in VO : ViewObject>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(view: VO)
    open fun bindWithPayload(view: VO, payload: MutableList<Any>) {}
}