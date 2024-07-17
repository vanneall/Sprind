package ru.point.sprind.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.appbar.MaterialToolbar
import ru.point.sprind.R
import ru.point.sprind.databinding.ToolbarTitleBinding

class TitleToolbar(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
) : MaterialToolbar(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val binding: ToolbarTitleBinding

    var text: String
        get() = binding.title.text.toString()
        set(value) {
            binding.title.text = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_title, this, true)
        binding = ToolbarTitleBinding.bind(this)
        initTitleToolbar(attrs, defStyleAttr)
    }

    private fun initTitleToolbar(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TitleToolbar,
            defStyleAttr,
            0
        )
        val text = typedArray.getString(R.styleable.TitleToolbar_text)
        binding.title.text = text

        val visibility = typedArray.getInt(R.styleable.TitleToolbar_backButtonVisibility, View.GONE)
        binding.back.visibility = visibility

        typedArray.recycle()
    }

    fun setOnBackClickListener(onClick: (View) -> Unit) {
        binding.back.setOnClickListener {
            onClick.invoke(binding.back)
        }
    }

}