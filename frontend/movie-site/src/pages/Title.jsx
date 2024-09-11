import { useSearchResultContext } from '@/contexts/SearchResultContext'
import { getData } from '@/contexts/actions/searchResultActions'
import { useEffect } from 'react'
import { useParams } from 'react-router-dom'
import Spinner from 'react-bootstrap/Spinner'
import styles from './page.module.css'
import {
  TitleHeader,
  TitleScore,
  TitlePoster,
  TitleVideo,
  TitleGallery,
} from '@/components'
import TitleGenre from '@/components/entityData/TitleGenre'

const runtimeToHours = (runtime) => {
  const hours = Math.floor(runtime / 60)
  const minutes = runtime % 60
  return ' ' + hours + 'h ' + minutes + 'm'
}

const types = ['imdbRating', 'userRating', 'popularity']
const movieDirector = { name: 'Christopher Nolan', link: '/' }
const movieWriters = [
  { name: 'Jonathan Nolan' },
  { name: 'Christopher Nolan' },
  { name: 'David S. Goyer' },
]

export default function Title() {
  const { id } = useParams()
  const { state: searchResultState, dispatch: searchResultDispatch } =
    useSearchResultContext()
  let isLoading = true
  let movie = null

  if (Object.keys(searchResultState.data).length !== 0) {
    movie = {
      ...searchResultState.data,
      movieDirector,
      movieWriters,
    }
    isLoading = false
  }

  useEffect(() => {
    getData(searchResultDispatch, id, 'movie')
    document.body.style.backgroundColor = '#1F1F1F'
  }, [id])

  return (
    <div
      className={` ${styles.page__container}  ${styles['page__container--left']} ${styles['page__container--right']}`}
    >
      {!isLoading ? (
        <>
          <div className={styles.data__header}>
            <TitleHeader movie={movie} />
            <div className={styles['data__header--right']}>
              {types.map((type, index) => {
                return <TitleScore type={type} key={index} />
              })}
            </div>
          </div>
          <div className={styles.title__gallery}>
            <TitlePoster poster={movie.moviePoster} />
            <TitleVideo trailer={movie.movieTrailer} />
            <TitleGallery videoNumber={50} photoNumber={100} />
          </div>
          <div className={styles.title__details}>
            <div style={{ display: 'grid', color: 'white' }}>
              <span className={styles.title__genres}>
                {movie.movieCategories.map((category, index) => {
                  return (
                    <TitleGenre
                      key={index}
                      genreName={category.categoryName}
                      genreLink={'/'}
                    />
                  )
                })}
              </span>
              <span
                style={{ paddingRight: '2rem' }}
                className={`${styles.title__details__item} ${styles.title__details__item__border}`}
              >
                {movie.movieDescription}
              </span>
              <span
                className={`${styles.title__details__item} ${styles.title__details__item__border}`}
              >
                Director
                <a href={movie.movieDirector.link}>
                  {movie.movieDirector.name}
                </a>
              </span>
              <span
                className={`${styles.title__details__item} ${styles.title__details__item__border}`}
              >
                Writers
                {movie.movieWriters.map((writer, index) => {
                  return (
                    <a href="/" key={index}>
                      {writer.name}
                    </a>
                  )
                })}
              </span>
              <span className={`${styles.title__details__item}`}>
                Stars
                {movie.movieActors?.slice(0, 3).map((actor, index) => {
                  return (
                    <a href="/" key={index}>
                      {actor.actorName}
                    </a>
                  )
                })}
              </span>
            </div>
            <span style={{ color: 'white' }}></span>
          </div>
        </>
      ) : (
        <div
          style={{
            display: 'flex',
            justifyContent: 'center',
          }}
        >
          <Spinner
            className={styles.spinner}
            animation="border"
            role="status"
            variant="dark"
          >
            <span className="visually-hidden">Loading...</span>
          </Spinner>
        </div>
      )}
    </div>
  )
}
