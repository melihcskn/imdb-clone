import React from "react";
import "bootstrap/dist/css/bootstrap.css";
import Spinner from "react-bootstrap/Spinner";
import { noPictureLogo } from "@/constants";
import styles from "./header.module.css";
import { useSearchContext } from "@/contexts/SearchContext";
import { actor, movie } from "@/constants";

export default function SearchBarListItem() {
  const { state: searchState } = useSearchContext();
  const movies: movie[] = searchState.movies;
  const actors: actor[] = searchState.actors;
  const isLoading: boolean = searchState.isLoading;
  const isSearchInputValid: boolean = searchState.isSearchInputValid;
  let isDataExist: boolean = true;
  const url: string = noPictureLogo;

  //Render loading icon when fetch is not done
  function renderLoading() {
    if (isLoading) {
      return (
        <li key={1} style={{ textAlign: "center" }}>
          {/*If data are loading render spinner*/}
          <Spinner animation="border" role="status" variant="light">
            <span className="visually-hidden">Loading...</span>
          </Spinner>
        </li>
      );
    }
  }

  //Render a feedback that informs the user no data has found
  function renderNoData() {
    return (
      <li key={1}>
        <button className={styles.dd__menu__button}>{"No match"}</button>
      </li>
    );
  }

  //Render found movies
  function renderMovieList() {
    return (
      <>
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
                          {index == 0 ? x.actorName : `, ${x.actorName}`}
                        </React.Fragment>
                      );
                    })}
                  </p>
                </div>
              </a>
            </li>
          );
        })}
      </>
    );
  }

  //Render found actors
  function renderActorList() {
    return (
      <>
        {actors.map((item, index) => {
          return (
            <li key={index}>
              <a
                href={`/actor/${item.actorId}`}
                className={`${styles.dd__menu__button} ${styles.header__searchbar__list__item}`}
              >
                <img src={item.actorPoster ? item.actorPoster : url} />

                <div>
                  <p>{item.actorName + " " + item.actorSurname}</p>
                  <p>{item.actorBirthDay.slice(0, 4)}</p>
                  <p>
                    {item.movies.slice(0, 2).map((x, index) => {
                      return (
                        <React.Fragment key={index}>
                          {index == 0 ? x.movieName : `, ${x.movieName}`}
                        </React.Fragment>
                      );
                    })}
                  </p>
                </div>
              </a>
            </li>
          );
        })}
      </>
    );
  }

  //Render searchbar items
  function renderDataList(): React.JSX.Element {
    //Render searchbar items if found any
    if (movies.length > 0 && actors.length > 0) {
      return (
        <React.Fragment>
          {renderMovieList()}
          {renderActorList()}
        </React.Fragment>
      );
    } else if (movies.length > 0 && actors.length === 0) {
      return renderMovieList();
    } else if (movies.length === 0 && actors.length > 0) {
      return renderActorList();
    }
  }

  function renderSearchBar(): React.JSX.Element {
    /*
    Set condition to false if there is no actor and movie data found
    while user has entered a search input
    */
    if (movies.length === 0 && actors.length === 0 && isSearchInputValid) {
      isDataExist = false;
    }
    //If search input is not valid no need to check anything else
    if (isSearchInputValid === true) {
      if (isLoading) {
        return renderLoading();
      } else if (isDataExist == false && isLoading == false) {
        return renderNoData();
      } else {
        return renderDataList();
      }
    }
  }

  return (
    <div className={styles.dropdown}>
      <ul className={`${styles.dd__menu} ${styles.header__searchbar__list}`}>
        {renderSearchBar()}
      </ul>
    </div>
  );
}
