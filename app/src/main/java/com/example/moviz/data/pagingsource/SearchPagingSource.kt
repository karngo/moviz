package com.example.moviz.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviz.data.ApiService
import com.example.moviz.data.model.MovieCompact

class SearchPagingSource(private val apiService: ApiService, private val query: String) :
    PagingSource<Int, MovieCompact>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieCompact> {
        if (query.trim().isEmpty()) {
            return LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        }

        val page = params.key ?: 1

        return try {
            val response = apiService.searchMovie(query, page).body()
            val results = response?.results ?: emptyList()

            val currentPage = response?.page ?: page
            val totalPages = response?.totalPages ?: currentPage

            val nextKey =
                if (currentPage >= totalPages || results.isEmpty()) null else currentPage + 1
            val prevKey = if (currentPage > 1) currentPage - 1 else null

            LoadResult.Page(
                data = results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieCompact>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { page ->
                page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
            }
        }
    }
}

