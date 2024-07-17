package ru.point.sprind.view

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView.OnEditorActionListener
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.appbar.MaterialToolbar
import ru.point.sprind.R
import ru.point.sprind.databinding.ToolbarSearchBinding

class SearchToolbar(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
) : MaterialToolbar(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val binding: ToolbarSearchBinding

    private var isSearchbarClickable: Boolean = false

    var address: String? = null
        set(value) {
            binding.apply {
                if (value == null) {
                    address.text = resources.getText(R.string.select_address)
                    address.setTextColor(resources.getColor(R.color.gray, null))
                    editAddressIcon.setColorFilter(resources.getColor(R.color.gray, null))
                } else {
                    address.text = value
                    address.setTextColor(resources.getColor(R.color.md_theme_primary, null))
                    editAddressIcon.setColorFilter(resources.getColor(R.color.md_theme_primary, null))
                }
            }
            field = value
        }

    var trailingIconVisibility: Int = View.GONE
        set(value) {
            binding.searchButton.visibility = value
            field = value
        }

    var searchText: String? = null
        set(value) {
            binding.search.setText(value ?: resources.getText(R.string.search))
            field = value
        }
        get() = binding.search.text.toString()

    var clearButtonVisibility: Int = View.GONE
        set(value) {
            binding.clearButton.visibility = value
            field = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_search, this, true)
        binding = ToolbarSearchBinding.bind(this)
        initSearchToolbar(attrs, defStyleAttr)
    }

    private fun initSearchToolbar(attrs: AttributeSet?, defStyleAttr: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.SearchToolbar,
            defStyleAttr,
            0
        )

        initSearchbar(typedArray = typedArray)
        initAddress(typedArray = typedArray)

        typedArray.recycle()
    }

    private fun initSearchbar(typedArray: TypedArray) {
        val isFieldClickable = typedArray.getBoolean(R.styleable.SearchToolbar_isEditable, false)
        val hint = typedArray.getString(R.styleable.SearchToolbar_android_hint)
            ?: ""

        binding.search.apply {
            if (isFieldClickable) {
                isFocusable = true
                isClickable = true
                isCursorVisible = true
            } else {
                isFocusable = false
                isClickable = false
                isCursorVisible = false
            }

            this@SearchToolbar.isSearchbarClickable = isFieldClickable
            setHint(hint)
            inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        }

        val backButton = typedArray.getDrawable(R.styleable.SearchToolbar_backIcon)
            ?: ResourcesCompat.getDrawable(resources, R.drawable.arrow_back , null)
        val backButtonVisibility = typedArray
            .getInt(R.styleable.SearchToolbar_backVisibility, View.GONE)
        binding.back.apply {
            if (backButtonVisibility == View.VISIBLE) {
                visibility = View.VISIBLE
                setImageDrawable(backButton)
            }
        }

        val trailingButtonVisibility = typedArray
            .getInt(R.styleable.SearchToolbar_trailingVisibility, View.GONE)
        val trailingIcon = typedArray.getDrawable(R.styleable.SearchToolbar_trailingIcon)
            ?: resources.getDrawable(R.drawable.search_icon, null)

        binding.searchButton.apply {
            visibility = trailingButtonVisibility
            setImageDrawable(trailingIcon)
        }
    }

    private fun initAddress(typedArray: TypedArray) {
        val isAddressShowed = typedArray.getBoolean(R.styleable.SearchToolbar_showAddress, true)
        val isAddressSelected =
            typedArray.getBoolean(R.styleable.SearchToolbar_isAddressSelected, false)
        binding.apply {
            val isVisible = if (isAddressShowed) View.VISIBLE else View.GONE
            address.visibility = isVisible
            editAddressIcon.visibility = isVisible

            val color = if (isAddressSelected) R.color.md_theme_primary else R.color.gray
            address.setTextColor(resources.getColor(color))
            editAddressIcon.setColorFilter(resources.getColor(color, null))
        }
    }

    fun setOnSearchbarClickListener(onClick: (View) -> Unit) {
        binding.search.setOnClickListener { onClick(binding.search) }
    }

    fun setOnAddressClickListener(onClick: (View) -> Unit) {
        binding.address.setOnClickListener { onClick(binding.address) }
    }

    fun setOnBackClickListener(onClick: (View) -> Unit) {
        binding.back.setOnClickListener { onClick.invoke(binding.back) }
    }

    fun setOnTrailingIconClickListener(onClick: (View) -> Unit) {
        binding.searchButton.setOnClickListener { onClick.invoke(binding.searchButton) }
    }

    fun setOnSearchTextChangedListener(onChange: (Editable?) -> Unit) {
        binding.search.addTextChangedListener { onChange.invoke(binding.search.text) }
    }

    fun setOnClearButtonClickListener(onClick: (View) -> Unit) {
        binding.clearButton.setOnClickListener { onClick.invoke(binding.clearButton) }
    }

    fun setOnEditorClickListener(onEditorActionListener: OnEditorActionListener) {
        binding.search.setOnEditorActionListener(onEditorActionListener)
    }
}