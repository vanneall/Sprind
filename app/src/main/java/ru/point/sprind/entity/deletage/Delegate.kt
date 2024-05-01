package ru.point.sprind.entity.deletage

import android.view.ViewGroup
import ru.point.domain.entity.view.ListView
import ru.point.sprind.entity.viewholder.ViewHolderV2

interface Delegate<VO : ListView> {
    fun isSupported(view: ListView): Boolean
    fun createViewHolder(parent: ViewGroup): ViewHolderV2<VO>
}