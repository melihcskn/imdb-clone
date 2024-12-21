const ApiURLs = {
  getMovieByNameStartingWith:
    "http://localhost:8080/api/movies/getMovieStartingWith?searchParam=",
  getMovieByNameContains:
    "http://localhost:8080/api/movies/getMovieContains?searchParam=",
  getMovieById: "http://localhost:8080/api/movies/",
  getActorByNameStartingWith:
    "http://localhost:8080/api/actors/getByNameStartingWith?searchParam=",
  getActorByNameContains:
    "http://localhost:8080/api/actors/getByNameContains?searchParam=",
  getActorById: "http://localhost:8080/api/actors/",
};

const siteLogo = "/images/IMDB_Logo.png";
const noPictureLogo = "/images/no_picture_available.png";

const homePosters = [
  { img: "/images/avengers.jpg", alt: "avengers_poster_1" },
  { img: "/images/avengers2.jpg", alt: "avengers_poster_2" },
];

type filterOption = {
  name: string;
  id: number;
};

const filterOptions: filterOption[] = [
  { name: "All", id: 0 },
  { name: "Titles", id: 1 },
  { name: "TV Episodes", id: 2 },
  { name: "Celebs", id: 3 },
];

const userOptions = [
  { name: "Watchlist", id: 0, Link: "" },
  { name: "User Settings", id: 1, Link: "" },
  { name: "LogOut", id: 2, Link: "/SignOut" },
];

type actor = {
  actorId: number;
  actorName: string;
  actorSurname: string;
  actorPoster: string;
  movies: movie[];
  actorBirthDay: string;
  actorBio: string;
};

type movie = {
  movieId: number;
  moviePoster: string;
  movieName: string;
  movieDescription: string;
  movieReleaseDate: any;
  movieActors: actor[];
  movieMpaRating: string;
  movieRuntime: number;
  movieTrailer: string;
};

type user = {
  userName: string;
};

const userAccessToken = "accessToken";
const userRefreshToken = "resreshToken";
const userName = "userName";

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
  movie,
  actor,
  filterOption,
  user,
};
