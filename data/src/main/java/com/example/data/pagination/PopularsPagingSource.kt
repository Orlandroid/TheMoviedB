package com.example.data.pagination


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.TheMovieDbApi
import com.example.domain.entities.local.CategoriesHome
import com.example.domain.entities.remote.ResultMovie
import retrofit2.HttpException

class PopularsPagingSource(
    private val service: TheMovieDbApi,
    private val categoriesHome: CategoriesHome
) : PagingSource<Int, ResultMovie>() {

    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ResultMovie> {
        return try {
            val currentPage = params.key ?: START_PAGE
            val data: List<ResultMovie>
            when (categoriesHome) {
                CategoriesHome.POPULAR -> {
                    data = service.getPopulars(currentPage.toString()).results
                }

                CategoriesHome.NOW_PLAYING -> {
                    data = service.nowPlaying(currentPage.toString()).results

                }

                CategoriesHome.UP_COMING -> {
                    data = service.upComing(currentPage.toString()).results
                }

                CategoriesHome.TOP_RATED -> {
                    data = service.topRated(currentPage.toString()).results
                }
            }
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

    override fun getRefreshKey(state: PagingState<Int, ResultMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
