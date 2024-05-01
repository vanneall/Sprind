package ru.point.sprind.entity.deletage

import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.entity.viewholder.ViewHolderV2

interface Delegate<VO : ViewObject> {
    fun isSupported(view: ViewObject): Boolean
    fun createViewHolder(parent: ViewGroup): ViewHolderV2<VO>
}