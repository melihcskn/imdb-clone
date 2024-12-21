import {
  createContext,
  useContext,
  useEffect,
  useReducer,
  ReactNode,
} from "react";
import { saveUser } from "./actions/userActions";
import { userAccessToken, userRefreshToken, userName, user } from "@/constants";

type State = {
  user: user;
  isSigned: boolean;
};

type Action = {
  type: string;
  payload?: any;
};

type Context = {
  state: State;
  dispatch: React.Dispatch<Action>;
};

type ContextProviderProps = {
  children?: ReactNode;
};

const defaultRoute = "http://localhost:5173/";

const initialState = { user: { userName: "" }, isSigned: false };

const UserContext = createContext<Context>({
  state: initialState,
  dispatch: null,
});

const reducer = (state: State, action: Action): any => {
  switch (action.type) {
    case "CHECK_USER": {
      //Check if any user has signed in
      if (localStorage.getItem(userAccessToken)) {
        const user = localStorage.getItem(userName);
        return {
          ...state,
          user: { userName: user },
          isSigned: true,
        };
      } else {
        return { ...initialState };
      }
    }
    case "SIGN_IN": {
      if (!localStorage.getItem(userAccessToken)) {
        return {
          ...saveUser(action.payload.accessToken),
        };
      } else {
        window.location.href = defaultRoute;
        return { ...state };
      }
    }
    case "SIGN_OUT": {
      localStorage.removeItem(userAccessToken);
      localStorage.removeItem(userRefreshToken);
      localStorage.removeItem(userName);
      return { ...initialState };
    }
    default: {
      throw new Error();
    }
  }
};

const UserContextProvider = ({ children }: ContextProviderProps) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  useEffect(() => {
    dispatch({ type: "CHECK_USER", payload: { ...state } });
  }, []);

  return (
    <UserContext.Provider value={{ state, dispatch }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUserContext = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error("No UserContext found");
  }
  return context;
};

export { UserContext, UserContextProvider };
