package com.satdev.rickandmortyapp.presentation.ui.transform

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.util.Resource
import com.satdev.rickandmortyapp.domain.usecase.GetCharactersUseCase

class CharacterPagingSource(
    private val getCharactersUseCase: GetCharactersUseCase
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1
        val previousKey = if (nextPageNumber == 1) null else nextPageNumber - 1
        val response = getCharactersUseCase.execute(nextPageNumber.toString())
        response.exception?.let {
            return LoadResult.Error(it)
        }

        return LoadResult.Page(
            data = response.data!!.characters.map { it },
            prevKey = previousKey, // Only paging forward.
            nextKey = geNextIndexFromPage(response.data!!.information.next)
        )
    }

    private fun geNextIndexFromPage(page:String):Int{
        return page.split("?page=").get(1).toInt()
    }
}