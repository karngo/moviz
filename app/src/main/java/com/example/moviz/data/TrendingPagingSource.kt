package com.example.moviz.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class TrendingPagingSource(private val apiService: ApiService) :
    PagingSource<Int, MovieCompact>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieCompact> {
        return try {
            val response = apiService.getTrending().body()
            val results = response?.results ?: emptyList()

            LoadResult.Page(
                data = results,
                prevKey = null,
                nextKey = null
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

