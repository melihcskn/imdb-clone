import React, { useEffect } from 'react'
import { useUserContext } from '@/contexts/UserContext'

export default function SignOut() {
  const { dispatch } = useUserContext()

  useEffect(() => {
    dispatch({ type: 'SIGN_OUT' })
    window.location.href = '/'
  }, [])
  return <div></div>
}
