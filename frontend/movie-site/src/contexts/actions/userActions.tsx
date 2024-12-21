import { jwtDecode,JwtPayload } from 'jwt-decode'
import { userAccessToken, userName } from '@/constants'

const initialState = {}

interface UserJwtPayload extends JwtPayload{
  preferred_username:string
}

const checkToken = (accessToken:string) => {
  const decodedAccessToken : UserJwtPayload = jwtDecode(accessToken)
  if (decodedAccessToken) {
    return {
      result: {
        decodedAccessToken: decodedAccessToken,
        isValid: true,
      },
    }
  } else {
    return { result: { isValid: false } }
  }
}

//Save user to local
const saveUser = (accessToken:string) => {
  const { result } = checkToken(accessToken)
  if (result.isValid) {
    localStorage.setItem(userAccessToken, JSON.stringify(accessToken))
    localStorage.setItem(userName, result.decodedAccessToken.preferred_username)
    return {
      ...initialState,
      user: {
        userName: result.decodedAccessToken.preferred_username,
      },
      isSigned: true,
    }
  } else {
    return { initialState }
  }
}

export { checkToken, saveUser }
