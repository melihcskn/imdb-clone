import { useEffect } from 'react'
import HomeSlider from '../components/home/HomeSlider'
import styles from './page.module.css'

export default function Home() {
  useEffect(() => {
    document.body.style.backgroundColor = 'black'
  }, [])
  return (
    <div
      className={` ${styles.page__container}  ${styles['page__container--left']} ${styles['page__container--right']}`}
    >
      <HomeSlider></HomeSlider>
    </div>
  )
}
