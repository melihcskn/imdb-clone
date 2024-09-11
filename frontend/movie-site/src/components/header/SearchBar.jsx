import 'bootstrap/dist/css/bootstrap.css'
import {
  SearchInput,
  SearchBarListItem,
  FilterButton,
} from '@/components/index'
import { useEffect } from 'react'
import styles from './header.module.css'
import { useSearchContext } from '@/contexts/SearchContext'
import { getSearchData } from '@/contexts/actions'

export default function SearchBar() {
  const { state: searchState, dispatch: searchDispatch } = useSearchContext()

  const searchInput = searchState.searchInput
  const isSearchInputValid = searchState.isSearchInputValid
  const filter = searchState.searchBarFilter
  const isListOpen = searchState.isListOpen

  useEffect(() => {
    //Check if there is a valid input
    if (isSearchInputValid) {
      //Get data according to input and selected filter
      getSearchData(searchDispatch, searchInput, filter.id)
    } else {
      searchDispatch({
        type: 'UPDATE_SEARCH_DATA',
        payload: { actors: [], movies: [] },
      })
    }
  }, [searchInput, filter])

  return (
    <div
      className={styles.header__item}
      style={{ display: 'flex', alignItems: 'center' }}
    >
      <FilterButton />
      <SearchInput />
      {
        //If list is open then render list items
        isListOpen && <SearchBarListItem />
      }
    </div>
  )
}
