import React from 'react'
import Carousel from 'react-bootstrap/Carousel'
import 'bootstrap/dist/css/bootstrap.css'
import styles from './home.module.css'
import { homePosters } from '@/constants'

export default function HomeSlider() {
  return (
    <div className={styles.home}>
      <Carousel className={styles.carousel} indicators={false} interval={null}>
        {homePosters.map((poster, index) => {
          return (
            <Carousel.Item key={index}>
              <img src={poster.img} alt={poster.alt} />
            </Carousel.Item>
          )
        })}
      </Carousel>
    </div>
  )
}
