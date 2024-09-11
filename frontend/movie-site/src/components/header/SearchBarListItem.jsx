import React from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import Spinner from 'react-bootstrap/Spinner'
import { If, Then, ElseIf, Else } from 'react-if-elseif-else-render'
import { noPictureLogo } from '@/constants'
import styles from './header.module.css'
import { useSearchContext } from '@/contexts/SearchContext'

export default function SearchBarListItem() {
  const { state: searchState } = useSearchContext()
  const movies = searchState.movies
  const actors = searchState.actors
  const isLoading = searchState.isLoading
  const isSearchInputValid = searchState.isSearchInputValid
  let isDataExist = true
  const url = noPictureLogo

  /*
  Set condition to false if there is no actor and movie data found
  while user has entered a search input
  */
  if (movies.length === 0 && actors.length === 0 && isSearchInputValid) {
    isDataExist = false
  }

  return (
    <div className={styles.dropdown}>
      <If condition={isSearchInputValid === true}>
        <Then>
          <ul
            className={`${styles.dd__menu} ${styles.header__searchbar__list}`}
          >
            <If condition={isLoading == true}>
              <Then>
                <li key={1} style={{ textAlign: 'center' }}>
                  {/*If data are loading render spinner*/}
                  <Spinner animation="border" role="status" variant="light">
                    <span className="visually-hidden">Loading...</span>
                  </Spinner>
                </li>
              </Then>
              <ElseIf condition={isDataExist == false}>
                <li key={1}>
                  <button className={styles.dd__menu__button}>
                    {'No match'}
                  </button>
                </li>
              </ElseIf>
              <Else>
                {movies.map((item, index) => {
                  return (
                    <li key={index}>
                      <a
                        href={`/title/${item.movieId}`}
                        className={`${styles.dd__menu__button} ${styles.header__searchbar__list__item}`}
                      >
                        <img src={item.moviePoster ? item.moviePoster : url} />
                        <div>
                          <p>{item.movieName}</p>
                          <p>{item.movieReleaseDate.slice(0, 4)}</p>
                          <p>
                            {item.movieActors.slice(0, 2).map((x, index) => {
                              return (
                                <React.Fragment key={index}>
                                  {index == 0
                                    ? x.actorName
                                    : `, ${x.actorName}`}
                                </React.Fragment>
                              )
                            })}
                          </p>
                        </div>
                      </a>
                    </li>
                  )
                })}
                <>
                  {actors.map((item, index) => {
                    return (
                      <li key={index}>
                        <a
                          href={`/actor/${item.actorId}`}
                          className={`${styles.dd__menu__button} ${styles.header__searchbar__list__item}`}
                        >
                          <img
                            src={item.actorPoster ? item.actorPoster : url}
                          />

                          <div>
                            <p>{item.actorName + ' ' + item.actorSurname}</p>
                            <p>{item.actorBirthDay.slice(0, 4)}</p>
                            <p>
                              {item.movies.slice(0, 2).map((x, index) => {
                                return (
                                  <React.Fragment key={index}>
                                    {index == 0
                                      ? x.movieName
                                      : `, ${x.movieName}`}
                                  </React.Fragment>
                                )
                              })}
                            </p>
                          </div>
                        </a>
                      </li>
                    )
                  })}
                </>
              </Else>
            </If>
          </ul>
        </Then>
      </If>
    </div>
  )
}
