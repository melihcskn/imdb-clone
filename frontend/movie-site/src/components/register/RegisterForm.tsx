import { useState } from "react";
import styled from "styled-components";
import { Form, Button, FormGroup, Input, FormFeedback } from "reactstrap";
import { useUserContext } from "@/contexts/UserContext";
import { validateEmail } from "@/functions";
import axios from "axios";

const FormItem = styled.p`
  margin-top: 1rem;
`;

interface IRegisterFormProps {
  handleSwitch: any;
}

interface IRegisterData {
  email: string;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
}

interface IPepeatedPassword {
  password: string;
  isValid: boolean;
}

export default function RegisterForm(props: IRegisterFormProps) {
  const [registerData, setRegisterData] = useState<IRegisterData>({
    email: "",
    username: "",
    password: "",
    firstName: "",
    lastName: "",
  });

  const [repeatedPassword, setRepeatedPassword] = useState<IPepeatedPassword>({
    password: "",
    isValid: true,
  });

  const [emailValidity, setEmailValidity] = useState<boolean>(true);

  const { dispatch } = useUserContext();

  const registerUrl: string = "http://localhost:8084/api/users/register";

  const handleInputChanges = (e: React.ChangeEvent<HTMLInputElement>) => {
    setRegisterData({ ...registerData, [e.target.name]: e.target.value });
  };

  const handleRepeatedPasswordChanges = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRepeatedPassword({
      ...repeatedPassword,
      password: e.target.value,
    });
  };

  const checkRepeatedPassword = () => {
    if (repeatedPassword.password == registerData.password) {
      setRepeatedPassword({
        ...repeatedPassword,
        isValid: true,
      });
    } else {
      setRepeatedPassword({
        ...repeatedPassword,
        isValid: false,
      });
    }
  };

  const handleRepeatedPasswordBlur = () => {
    console.log(repeatedPassword);
  };

  function checkEmailValidity() {
    if (validateEmail(registerData.email)) {
      setEmailValidity(true);
    } else {
      setEmailValidity(false);
    }
  }

  async function handleRegister() {
    checkEmailValidity();
    if (emailValidity) {
      try {
        const response = await axios.post(registerUrl, registerData);

        if (response.status <= 201 && response.status >= 200) {
          alert("User created successfully");
          console.log(response);
          setTimeout(() => {
            dispatch({
              type: "SIGN_IN",
              payload: {
                accessToken: response.data,
              },
            });
          }, 1000);
        }
      } catch (error) {
        if (error.response?.data != undefined) {
          alert(error.response.data);
        }
        console.log(error);
      }
    }
  }

  const handleSwitchLogin = props.handleSwitch;

  return (
    <Form>
      <FormGroup>
        <FormItem>Email</FormItem>
        <Input
          id="registerEmail"
          name="email"
          placeholder="test@mail.com"
          type="email"
          onChange={handleInputChanges}
          value={registerData.email}
          onBlur={checkEmailValidity}
          invalid={!emailValidity}
        />
        <FormFeedback>Please enter a valid email</FormFeedback>
        <FormItem>Username</FormItem>
        <Input
          id="registerUsername"
          name="username"
          placeholder="username"
          onChange={handleInputChanges}
          value={registerData.username}
        />
        <FormItem>Password</FormItem>
        <Input
          id="registerPassword"
          name="password"
          placeholder="password"
          type="password"
          onChange={handleInputChanges}
          value={registerData.password}
        />
        <FormItem>Password</FormItem>
        <Input
          id="repeatedPassword"
          name="repeatedPassword"
          placeholder="please enter your password again"
          type="password"
          onChange={handleRepeatedPasswordChanges}
          value={repeatedPassword.password}
          onBlur={checkRepeatedPassword}
          invalid={!repeatedPassword.isValid}
        />
        <FormFeedback>Please enter the same password</FormFeedback>
        <FormItem>Firstname</FormItem>
        <Input
          id="registerFirstName"
          name="firstName"
          placeholder="Firstname"
          onChange={handleInputChanges}
          value={registerData.firstName}
        />
        <FormItem>Lastname</FormItem>
        <Input
          id="registerLastName"
          name="lastName"
          placeholder="Lastname"
          onChange={handleInputChanges}
          value={registerData.lastName}
        />
      </FormGroup>
      <Button
        className="btn"
        name="register"
        style={{ width: "100%", marginTop: "0.5rem" }}
        onClick={handleRegister}
      >
        Register
      </Button>
      <h5
        style={{
          textAlign: "center",
          marginTop: "1.5rem",
          marginBottom: "1.5rem",
        }}
      >
        OR
      </h5>
      <Button
        className="btn"
        name="login"
        style={{ width: "100%", marginTop: "0.5rem" }}
        onClick={handleSwitchLogin}
      >
        Log In
      </Button>
    </Form>
  );
}
