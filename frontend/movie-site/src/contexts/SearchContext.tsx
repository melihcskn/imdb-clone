import { createContext, useContext, useReducer, ReactNode } from "react";
import { filterOptions, movie, actor, filterOption } from "@/constants";

type State = {
  searchBarFilter: filterOption;
  searchInput: string;
  isSearchInputValid: boolean;
  searchBarFilterOptions: filterOption[];
  isListOpen: boolean;
  actors: actor[];
  movies: movie[];
  isLoading: boolean;
};

type Action = {
  type: string;
  payload?: any;
};

type SearchContext = {
  state: State;
  dispatch: React.Dispatch<Action>;
};

type ContextProviderProps = {
  children?: ReactNode;
};

const initialState: State = {
  searchBarFilter: filterOptions[0],
  searchInput: "",
  isSearchInputValid: false,
  searchBarFilterOptions: filterOptions,
  isListOpen: false,
  actors: [],
  movies: [],
  isLoading: false,
};

const SearchContext = createContext<SearchContext>({
  state: initialState,
  dispatch: null,
});

const reducer = (state: State, action: Action): any => {
  switch (action.type) {
    case "UPDATE_SEARCH_DATA": {
      return {
        ...state,
        ...action.payload,
      };
    }
    case "SET_SEARCH_FILTER": {
      return { ...state, searchBarFilter: action.payload };
    }
    case "SET_INPUT": {
      var isValidInput = action.payload.length > 0 ? true : false;
      return {
        ...state,
        searchInput: action.payload,
        isSearchInputValid: isValidInput,
      };
    }
    case "SET_IS_LIST_OPEN": {
      return {
        ...state,
        isListOpen: action.payload,
      };
    }
    default: {
      throw new Error();
    }
  }
};

const SearchContextProvider = ({ children }: ContextProviderProps) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <SearchContext.Provider
      value={{
        state,
        dispatch,
      }}
    >
      {children}
    </SearchContext.Provider>
  );
};

export const useSearchContext = () => {
  const context = useContext(SearchContext);
  if (!context) {
    throw new Error("No SearchContext found");
  }
  return context;
};

export { SearchContext, SearchContextProvider };
