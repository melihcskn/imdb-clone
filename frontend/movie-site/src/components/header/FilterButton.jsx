import 'bootstrap/dist/css/bootstrap.css'
import { useSearchContext } from '@/contexts/SearchContext'
import { DropdownButton } from '@/components/index'
import styles from './header.module.css'

export default function FilterButton() {
  //Get navbar context data
  const { state: searchState, dispatch } = useSearchContext()
  const searchBarFilterOptions = searchState.searchBarFilterOptions
  const searchBarFilter = searchState.searchBarFilter.name

  //Handle filter selection button
  const handleSelectFilter = (e) => {
    dispatch({
      type: 'SET_SEARCH_FILTER',
      payload: { name: e.target.innerText, id: e.target.id },
    })
  }

  return (
    <DropdownButton
      options={searchBarFilterOptions}
      handleSelect={handleSelectFilter}
      selectedOption={searchBarFilter}
      styles={styles}
      type={'filter'}
    ></DropdownButton>
  )
}
