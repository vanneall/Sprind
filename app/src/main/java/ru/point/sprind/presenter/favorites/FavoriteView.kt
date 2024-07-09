package ru.point.sprind.presenter.favorites

import ru.point.sprind.presenter.general.ConnectionRequiredView
import ru.point.sprind.presenter.general.NavigableView
import ru.point.sprind.presenter.general.UsingPagingAdapter

interface FavoriteView : ConnectionRequiredView, UsingPagingAdapter, NavigableView {
}