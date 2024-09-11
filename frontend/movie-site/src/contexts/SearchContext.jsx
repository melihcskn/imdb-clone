import { createContext, useContext, useReducer } from 'react'
import { filterOptions } from '@/constants'

const SearchContext = createContext('context')

const initial = {
  searchBarFilter: filterOptions[0],
  searchInput: '',
  isSearchInputValid: false,
  searchBarFilterOptions: filterOptions,
  isListOpen: false,
  actors: [],
  movies: [],
  isLoading: false,
}

const reducer = (state, action) => {
  switch (action.type) {
    case 'UPDATE_SEARCH_DATA': {
      return {
        ...state,
        ...action.payload,
      }
    }
    case 'SET_SEARCH_FILTER': {
      return { ...state, searchBarFilter: action.payload }
    }
    case 'SET_INPUT': {
      var isValidInput = action.payload.length > 0 ? true : false
      return {
        ...state,
        searchInput: action.payload,
        isSearchInputValid: isValidInput,
      }
    }
    case 'SET_IS_LIST_OPEN': {
      return {
        ...state,
        isListOpen: action.payload,
      }
    }
    default: {
      throw new Error()
    }
  }
}

const SearchContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initial)

  return (
    <SearchContext.Provider
      value={{
        state,
        dispatch,
      }}
    >
      {children}
    </SearchContext.Provider>
  )
}

export const useSearchContext = () => {
  const context = useContext(SearchContext)
  if (!context) {
    throw new Error('No SearchContext found')
  }
  return context
}

export { SearchContext, SearchContextProvider }
