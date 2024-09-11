import { useState } from 'react'
import { Form, Button, FormGroup, Input, FormFeedback } from 'reactstrap'
import styled from 'styled-components'
import {
  GoogleLoginButton,
  FacebookLoginButton,
  AppleLoginButton,
} from 'react-social-login-buttons'
import { validateEmail } from '@/functions'
import { useUserContext } from '@/contexts/UserContext'
import axios from 'axios'

const FormItem = styled.p`
  margin-top: 1rem;
`

const userNameOrPasswordError = 'Invalid user credentials'

export default function LoginForm(props) {
  const [loginData, setLoginData] = useState({
    email: '',
    password: '',
  })

  const [emailValidity, setEmailValidity] = useState(true)

  const { dispatch } = useUserContext()

  const loginUrl =
    'http://localhost:8888/realms/imdb/protocol/openid-connect/token'

  const handleSwitchRegister = props.handleSwitch

  const handleInputChanges = (e) => {
    setLoginData({ ...loginData, [e.target.name]: e.target.value })
  }

  const handleGoogleLogin = async () => {
    //Add google login
  }

  const handleFacebookLogin = async () => {
    //Add facebook login
  }

  const handleLogIn = async () => {
    checkEmailValidity()
    if (true) {
      try {
        const config = {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        }

        const params = new URLSearchParams()
        params.append('client_id', 'imdb-client')
        params.append('grant_type', 'password')
        params.append('username', loginData.email)
        params.append('password', loginData.password)

        const response = await axios.post(loginUrl, params, config)

        dispatch({
          type: 'SIGN_IN',
          payload: {
            accessToken: response.data.access_token,
          },
        })
      } catch (error) {
        if (
          error.response?.data?.error_description == userNameOrPasswordError
        ) {
          alert('Username or password is wrong')
        } else if (error.response?.data?.error_description != undefined) {
          console.log(error.response.data.error_description)
        } else {
          console.log(error)
        }
      }
    }
  }

  function checkEmailValidity() {
    if (validateEmail(loginData.email)) {
      setEmailValidity(true)
    } else {
      setEmailValidity(false)
    }
  }

  return (
    <Form>
      <FormGroup>
        <FormItem htmlFor="email">Email</FormItem>
        <Input
          id="loginEmail"
          name="email"
          placeholder="test@mail.com"
          type="email"
          onChange={handleInputChanges}
          value={loginData.username}
          invalid={!emailValidity}
          onBlur={checkEmailValidity}
        />
        <FormFeedback>Please enter a valid email adress</FormFeedback>
        <FormItem htmlFor="password">Password</FormItem>
        <Input
          id="loginPassword"
          name="password"
          placeholder="password"
          type="password"
          onChange={handleInputChanges}
          value={loginData.password}
        />
      </FormGroup>
      <Button
        className="btn"
        style={{ width: '100%', marginTop: '0.5rem' }}
        onClick={handleLogIn}
      >
        Log In
      </Button>
      <Button
        className="btn"
        style={{ width: '100%', marginTop: '0.5rem' }}
        onClick={handleSwitchRegister}
      >
        Register
      </Button>
      <h5
        style={{
          textAlign: 'center',
          marginTop: '2.5rem',
          marginBottom: '2.5rem',
        }}
      >
        OR
      </h5>
      <GoogleLoginButton className="mb-2" />
      <FacebookLoginButton className="mb-2" />
      <AppleLoginButton className="mb-2" />
    </Form>
  )
}

export { LoginForm }
