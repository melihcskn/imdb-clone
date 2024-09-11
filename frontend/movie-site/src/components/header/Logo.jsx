import { Link } from 'react-router-dom'
import styles from './header.module.css'
import { siteLogo } from '@/constants'

export default function Logo() {
  //When logo is clicked return home page

  return (
    <a href="/" className={styles.header__item}>
      <img src={siteLogo} className={styles.header__icon} alt="LogoIcon" />
    </a>
  )
}
