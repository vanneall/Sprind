package ru.point.sprind.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.point.sprind.R
import ru.point.sprind.databinding.ConnectableLayoutBinding

class ConnectableLayout(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: ConnectableLayoutBinding

    var currentState: ConnectionState = ConnectionState.SUCCESS
        set(value) {
            showNewState(value)
            hideCurrentState(field)
            field = value
        }

    enum class ConnectionState {
        SUCCESS,
        LOADING,
        BAD_CONNECTION,
        NO_DATA
    }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.connectable_layout, this, true)
        binding = ConnectableLayoutBinding.bind(this)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    private fun hideCurrentState(state: ConnectionState) {
        with(binding) {
            when (state) {
                ConnectionState.SUCCESS -> {}
                ConnectionState.LOADING -> loadingScreen.root.visibility = GONE
                ConnectionState.BAD_CONNECTION -> badConnection.root.visibility = GONE
                ConnectionState.NO_DATA -> notFoundScreen.root.visibility = GONE
            }
        }
    }

    private fun showNewState(state: ConnectionState) {
        with(binding) {
            when (state) {
                ConnectionState.SUCCESS -> {}
                ConnectionState.LOADING -> loadingScreen.root.visibility = VISIBLE
                ConnectionState.BAD_CONNECTION -> badConnection.root.visibility = VISIBLE
                ConnectionState.NO_DATA -> notFoundScreen.root.visibility = VISIBLE
            }
        }

    }
}