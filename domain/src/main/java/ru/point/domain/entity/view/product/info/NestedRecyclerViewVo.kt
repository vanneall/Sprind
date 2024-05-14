package ru.point.domain.entity.view.product.info

import ru.point.domain.entity.view.ViewObject

data class NestedRecyclerViewVo(
    val viewObjects: List<ViewObject>
) : ViewObject