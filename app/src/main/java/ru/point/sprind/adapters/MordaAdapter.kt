package ru.point.sprind.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2

class MordaAdapter(
    private val delegates: List<Delegate<*>>,
) : RecyclerView.Adapter<ViewHolderV2<ViewObject>>(), LifecycleEventObserver {

    private val compositeDisposable = CompositeDisposable()

    var views: List<ViewObject> = emptyList()
        set(new) {
            val disposable = Single
                .create { emitter ->
                    val callback = DiffUtilCallback(
                        oldList = field,
                        newList = new
                    )
                    emitter.onSuccess(DiffUtil.calculateDiff(callback))
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { diffResult ->
                    field = new
                    diffResult.dispatchUpdatesTo(this)
                }
            compositeDisposable.add(disposable)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderV2<ViewObject> {
        return delegates[viewType]
            .createViewHolder(parent)
            .run { this as ViewHolderV2<ViewObject> }
    }

    override fun onBindViewHolder(holder: ViewHolderV2<ViewObject>, position: Int) {
        holder.bind(views[position])
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { delegate -> delegate.isSupported(views[position]) }
    }

    override fun getItemCount(): Int = views.size

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_STOP) {
            Log.d("Adapter", "cleared ${compositeDisposable.size()}")
            compositeDisposable.clear()
        }
    }
}