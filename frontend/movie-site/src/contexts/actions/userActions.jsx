import { jwtDecode } from 'jwt-decode'
import { userAccessToken, userName } from '@/constants'

const initial = {}

const checkToken = (accessToken) => {
  const decodedAccessToken = jwtDecode(accessToken, { payload: true })
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
const saveUser = (accessToken) => {
  const { result } = checkToken(accessToken)
  if (result.isValid) {
    localStorage.setItem(userAccessToken, JSON.stringify(accessToken))
    localStorage.setItem(userName, result.decodedAccessToken.preferred_username)
    return {
      ...initial,
      user: {
        userName: result.decodedToken.preferred_username,
      },
      isSigned: true,
    }
  } else {
    return { initial }
  }
}

export { checkToken, saveUser }
