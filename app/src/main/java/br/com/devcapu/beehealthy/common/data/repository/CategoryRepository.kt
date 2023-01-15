package br.com.devcapu.beehealthy.common.data.repository

import br.com.devcapu.beehealthy.common.data.datasource.LocalCategoryDataSource

class CategoryRepository(
    private val localCategoryDataSource: LocalCategoryDataSource,
) {
    fun getAll(categoriesAsJSONString: String) =
        localCategoryDataSource.getAll(categoriesAsJSONString)
}