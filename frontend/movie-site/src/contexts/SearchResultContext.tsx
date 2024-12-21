import { createContext, useContext, useReducer,ReactNode } from 'react'

type State = {
data:any
isLoading:boolean
}

type Action = {
  type:string
  payload?:any
}

type Context = {
  state:State
  dispatch:React.Dispatch<Action>
}

type ContextProviderProps = {
  children?: ReactNode
}

const initialState = { data: {}, isLoading: false }

const SearchResultContext = createContext<Context>({state:initialState,dispatch:null})

const reducer = (state:State, action:Action) : State => {
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

const SearchResultContextProvider = ({ children } : ContextProviderProps) => {
  const [state, dispatch] = useReducer(reducer, initialState)

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
