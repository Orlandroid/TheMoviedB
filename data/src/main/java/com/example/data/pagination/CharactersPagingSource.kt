package com.example.data.pagination


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.TheMovieDbApi
import com.example.domain.entities.remote.Result
import retrofit2.HttpException

class CharactersPagingSource(
    private val service: TheMovieDbApi
) : PagingSource<Int, Result>() {

    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: START_PAGE
            val data = service.getPopulars(currentPage.toString()).results
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == START_PAGE) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage.plus(1)
            )
        } catch (e: Exception) {
            if (e is HttpException) {
                val errorString =
                    e.response()?.errorBody()?.byteStream()?.bufferedReader().use { it?.readText() }
                LoadResult.Error(Throwable(errorString))
            } else {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
