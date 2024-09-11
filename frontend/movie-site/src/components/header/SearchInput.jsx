import styles from './header.module.css'
import 'bootstrap/dist/css/bootstrap.css'
import { useSearchContext } from '@/contexts/SearchContext'

export default function SearchInput() {
  const { state: searchState, dispatch: searchDispatch } = useSearchContext()

  const input = searchState.searchInput

  const setIsListOpen = (isOpen) => {
    searchDispatch({ type: 'SET_IS_LIST_OPEN', payload: isOpen })
  }

  const setInput = (newInput) => {
    searchDispatch({ type: 'SET_INPUT', payload: newInput })
  }

  const handleInput = (e) => {
    searchDispatch({ type: 'SET_INPUT', payload: 'e.target.value' })
    setInput(e.target.value)
  }

  //Handle search button, open search page according to input
  const handleSearch = () => {
    if (input.length > 0) {
      setInput('')
      window.open(`https://www.imdb.com/find/?q=${input}`, '_blank')
    }
  }

  //Set focus condition
  const handleFocus = () => {
    setIsListOpen(true)
  }

  //Handle when focus is lost
  const handleBlur = () => {
    setTimeout(() => {
      setIsListOpen(false)
    }, 100)
  }

  return (
    <div>
      <input
        type="input"
        name="name"
        onFocus={handleFocus}
        onBlur={handleBlur}
        aria-autocomplete="none"
        value={input}
        placeholder="Search IMDb"
        className={styles.searchbar__input}
        onChange={handleInput}
        onKeyDown={(e) => {
          if (e.key === 'Enter') {
            handleSearch()
          }
        }}
      />
      <button
        className={styles.searchbar__input__searchbutton}
        onClick={handleSearch}
        title="SearcButton"
      >
        <i className="bi bi-search" />
      </button>
    </div>
  )
}
