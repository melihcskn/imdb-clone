import { createContext, useContext, useEffect, useReducer } from 'react'
import { saveUser } from './actions/userActions'
import { userAccessToken, userRefreshToken, userName } from '@/constants'

const UserContext = createContext('context')
const defaultRoute = 'http://localhost:5173/'

const initial = { user: { userName: '' }, isSigned: false }

const reducer = (state, action) => {
  switch (action.type) {
    case 'CHECK_USER': {
      //Check if any user has signed in
      if (localStorage.getItem(userAccessToken)) {
        const user = localStorage.getItem(userName)
        return {
          ...state,
          user: { userName: user },
          isSigned: true,
        }
      } else {
        return { ...initial }
      }
    }
    case 'SIGN_IN': {
      if (!localStorage.getItem(userAccessToken)) {
        return {
          ...saveUser(action.payload.accessToken, action.payload.refreshToken),
        }
      } else {
        window.location.href = defaultRoute
        return { ...state }
      }
    }
    case 'SIGN_OUT': {
      localStorage.removeItem(userAccessToken)
      localStorage.removeItem(userRefreshToken)
      localStorage.removeItem(userName)
      return { ...initial }
    }
    default: {
      throw new Error()
    }
  }
}

const UserContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initial)

  useEffect(() => {
    dispatch({ type: 'CHECK_USER', payload: { ...state } })
  }, [])

  return (
    <UserContext.Provider value={{ state, dispatch }}>
      {children}
    </UserContext.Provider>
  )
}

export const useUserContext = () => {
  const context = useContext(UserContext)
  if (!context) {
    throw new Error('No UserContext found')
  }
  return context
}

export { UserContext, UserContextProvider }
