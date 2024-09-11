import styles from '@/components/header/header.module.css'
import { MenuToggle, SearchBar, Logo, SignInButton } from '@/components/index'
import { SearchContextProvider } from '@/contexts/SearchContext'
import axios from 'axios'

export default function Header() {
  return (
    <div className={styles.header}>
      <div
        className={` ${styles.header__container}  ${styles['header__container--left']} ${styles['header__container--right']}`}
      >
        <Logo />
        <MenuToggle />
        <SearchContextProvider>
          <SearchBar />
        </SearchContextProvider>
        <SignInButton />
      </div>
    </div>
  )
}
