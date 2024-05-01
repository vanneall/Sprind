package ru.point.sprind.entity.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.ListView

abstract class ViewHolderV2<in VO : ListView>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(view: VO)
}