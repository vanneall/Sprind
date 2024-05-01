package ru.point.sprind.entity.deletage

import android.view.ViewGroup
import ru.point.domain.entity.view.ListView
import ru.point.sprind.entity.viewholder.ViewHolderV2

interface Delegate {
    fun forItem(view: ListView): Boolean
    fun createViewHolder(parent: ViewGroup): ViewHolderV2
    fun bindViewHolder(view: ListView, viewHolder: ViewHolderV2)
}