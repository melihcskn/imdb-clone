import styles from './entity.module.css'

const runtimeToHours = (runtime) => {
  const hours = Math.floor(runtime / 60)
  const minutes = runtime % 60
  return ' ' + hours + 'h ' + minutes + 'm'
}

export default function TitleHeader(props) {
  const { movie } = props
  const links = {
    movieReleaseData: '/',
    movieMpaRating: '/',
  }
  return (
    <span>
      <div className={styles['title__header--left']}>{movie?.movieName}</div>
      <div className={styles[`title__header--bottom`]}>
        <a href={links.movieReleaseData}>
          {movie?.movieReleaseDate.slice(0, 4)}
        </a>
        - <a href={links.movieMpaRating}>{movie?.movieMpaRating}</a>-
        {runtimeToHours(movie?.movieRuntime)}
      </div>
    </span>
  )
}

export { TitleHeader }
