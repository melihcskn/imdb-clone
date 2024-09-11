import React, { useEffect, useRef, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import styles from '@/pages/page.module.css'
import { LoginForm, RegisterForm } from '@/components'

export default function SignIn() {
  const [registerStatus, setRegisterStatus] = useState(false)

  useEffect(() => {
    document.body.style.backgroundColor = 'gray'
  }, [])

  function handleSwitch() {
    setRegisterStatus(!registerStatus)
  }

  return (
    <div className={styles.logIn}>
      <div className={styles.login__content}>
        {!registerStatus ? (
          <div className={styles.login__form}>
            <LoginForm handleSwitch={handleSwitch} />
          </div>
        ) : (
          <div className={styles.login__form}>
            <RegisterForm handleSwitch={handleSwitch} />
          </div>
        )}
      </div>
    </div>
  )
}
