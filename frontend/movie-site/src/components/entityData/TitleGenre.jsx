import styles from './entity.module.css'

export default function TitleGenre(props) {
  const { genreLink, genreName } = props

  return (
    <span className={styles.imdb__genre}>
      <a href={genreLink}>{genreName}</a>
    </span>
  )
}
