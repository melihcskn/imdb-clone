const ApiURLs = {
  getMovieByNameStartingWith:
    'http://localhost:8080/api/movies/getMovieStartingWith?searchParam=',
  getMovieByNameContains:
    'http://localhost:8080/api/movies/getMovieContains?searchParam=',
  getMovieById: 'http://localhost:8080/api/movies/',
  getActorByNameStartingWith:
    'http://localhost:8080/api/actors/getByNameStartingWith?searchParam=',
  getActorByNameContains:
    'http://localhost:8080/api/actors/getByNameContains?searchParam=',
  getActorById: 'http://localhost:8080/api/actors/',
}

const siteLogo = '/images/IMDB_Logo.png'
const noPictureLogo = '/images/no_picture_available.png'

const homePosters = [
  { img: '/images/avengers.jpg', alt: 'avengers_poster_1' },
  { img: '/images/avengers2.jpg', alt: 'avengers_poster_2' },
]

const filterOptions = [
  { name: 'All', id: 0 },
  { name: 'Titles', id: 1 },
  { name: 'TV Episodes', id: 2 },
  { name: 'Celebs', id: 3 },
]

const userOptions = [
  { name: 'Watchlist', id: 0, Link: '' },
  { name: 'User Settings', id: 1, Link: '' },
  { name: 'LogOut', id: 2, Link: '/SignOut' },
]

const userAccessToken = 'accessToken'
const userRefreshToken = 'resreshToken'
const userName = 'userName'

export {
  ApiURLs,
  siteLogo,
  filterOptions,
  noPictureLogo,
  userOptions,
  homePosters,
  userAccessToken,
  userRefreshToken,
  userName,
}
