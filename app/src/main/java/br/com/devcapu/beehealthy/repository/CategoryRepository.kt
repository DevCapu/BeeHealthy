package br.com.devcapu.beehealthy.repository

import br.com.devcapu.beehealthy.local.LocalCategoryDataSource

class CategoryRepository(
    private val localCategoryDataSource: LocalCategoryDataSource,
) {
    fun getAll(categoriesAsJSONString: String) =
        localCategoryDataSource.getAll(categoriesAsJSONString)
}