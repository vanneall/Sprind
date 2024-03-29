package ru.point.sprind.entity.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.domain.entity.ListView
import ru.point.domain.entity.Product

class ProductViewHolder(
    private val binding: VerticalCardItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ru.point.domain.entity.ListView) {
        (view as? ru.point.domain.entity.Product)?.let {
            binding.image.load(
                view.url[0].url
            ) {
                scale(Scale.FIT)
            }
            binding.name.text = view.name
            binding.price.text = view.price
        }
    }

}