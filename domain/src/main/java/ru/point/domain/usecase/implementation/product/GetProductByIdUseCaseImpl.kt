package ru.point.domain.usecase.implementation.product

import ru.point.domain.entity.response.mappers.toAllCharacteristicsVo
import ru.point.domain.entity.response.mappers.toNestedRecyclerViewVo
import ru.point.domain.entity.response.mappers.toProductDescriptionVo
import ru.point.domain.entity.response.mappers.toProductReviewVo
import ru.point.domain.entity.response.mappers.toProductTitleVo
import ru.point.domain.repository.ProductRepository
import ru.point.domain.usecase.interfaces.product.GetProductByIdUseCase

class GetProductByIdUseCaseImpl(
    private val repository: ProductRepository,
) : GetProductByIdUseCase {
    override fun invoke(id: Long) = repository.getProductById(id = id)
        .map { dto ->
            listOf(
                dto.toNestedRecyclerViewVo(),
                dto.toProductTitleVo(),
                dto.toProductDescriptionVo(),
                dto.toAllCharacteristicsVo(),
                dto.toProductReviewVo()
            )
        }
}