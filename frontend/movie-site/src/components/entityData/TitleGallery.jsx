import styles from './entity.module.css'

export default function TitleGallery(props) {
  const { videoNumber, photoNumber } = props
  const videoLink = '/'
  const photoLink = '/'

  return (
    <div className={styles.imdb__gallery}>
      <a href={videoLink} className={styles.imdb__gallery__box}>
        <i className="bi bi-play-btn-fill h2" />
        <div>{videoNumber} Videos</div>
      </a>
      <a href={photoLink} className={styles.imdb__gallery__box}>
        <i className="bi bi-images h2" />
        <div>{photoNumber} Photos</div>
      </a>
    </div>
  )
}

export { TitleGallery }
