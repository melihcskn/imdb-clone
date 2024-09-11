import { createContext, useContext, useReducer } from 'react'

const SearchResultContext = createContext()

const initial = { data: {}, isLoading: false }

const reducer = (state, action) => {
  switch (action.type) {
    case 'UPDATE_SEARCH_DATA': {
      return {
        ...state,
        data: { ...action.payload.data },
        isLoading: action.payload.isLoading,
      }
    }
    default: {
      throw new Error()
    }
  }
}

const SearchResultContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initial)

  return (
    <SearchResultContext.Provider value={{ state, dispatch }}>
      {children}
    </SearchResultContext.Provider>
  )
}

export const useSearchResultContext = () => {
  const context = useContext(SearchResultContext)
  if (!context) {
    throw new Error('No SearchResultContext found')
  }
  return context
}

export { SearchResultContext, SearchResultContextProvider }
