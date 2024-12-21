import { useNavigate } from "react-router-dom";
import styles from "./header.module.css";
import DropdownButton from "@/components/DropdownButton";
import { useUserContext } from "@/contexts/UserContext";
import { userOptions, user } from "@/constants";

export default function SignInButton() {
  const { state: userState } = useUserContext();
  const isSigned: boolean = userState.isSigned;
  const user: user = userState.user;

  const navigate = useNavigate();

  //Handle when user options are clicked
  const handleSelect = (e: React.MouseEvent<HTMLElement>) => {
    const id: number = parseInt(e.currentTarget.id);
    navigate(userOptions[id].Link);
  };

  //Navigate to sign in page
  const handleSignIn = () => {
    navigate("/signIn");
  };

  return (
    <div>
      {isSigned ? (
        <DropdownButton
          options={userOptions}
          selectedOption={user.userName}
          handleSelect={handleSelect}
          styles={styles}
          type={"signIn"}
          className={`${styles.header__button} ${styles.header__item}`}
        />
      ) : (
        <button
          className={`${styles.header__button} ${styles.header__item}`}
          onClick={handleSignIn}
        >
          <h1>Sign In</h1>
        </button>
      )}
    </div>
  );
}
