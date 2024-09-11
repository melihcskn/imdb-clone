import axios from 'axios'
import { ApiURLs } from '@/constants'

async function getSearchData(dispatch, searchQuery, searchFilter) {
  try {
    const token = JSON.parse(localStorage.getItem('userToken'))
    if (token) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    }

    dispatch({ type: 'UPDATE_SEARCH_DATA', payload: { isLoading: true } })
    let movies = []
    let actors = []
    if (searchFilter == 0) {
      movies = await getMovieData(searchQuery)
      actors = await getActorData(searchQuery)
    } else if (searchFilter == 1) {
      movies = await getMovieData(searchQuery)
    } else if (searchFilter == 3) {
      actors = await getActorData(searchQuery)
    }

    dispatch({
      type: 'UPDATE_SEARCH_DATA',
      payload: { actors, movies, isLoading: false },
    })
  } catch (error) {
    console.log(error)
    dispatch({
      type: 'UPDATE_SEARCH_DATA',
      payload: { actors: [], movies: [] },
    })
  }
}

const getActorData = async function (input) {
  const default_actor_url = ApiURLs.getActorByNameStartingWith.concat(input)
  const alternative_actor_url = ApiURLs.getActorByNameContains.concat(input)
  try {
    var actor_default_response = await axios.get(default_actor_url)
    var resp = actor_default_response.data
    if (resp.length > 0) {
      return [...actor_default_response.data]
    } else {
      var actor_alternate_response = await axios.get(alternative_actor_url)
      var resp = actor_alternate_response.data
      if (resp.length > 0) {
        return [...actor_alternate_response.data]
      } else {
        return []
      }
    }
  } catch (error) {
    return []
  }
}

const getMovieData = async function (input) {
  const default_movie_url = ApiURLs.getMovieByNameStartingWith.concat(input)
  const alternative_movie_url = ApiURLs.getMovieByNameContains.concat(input)
  try {
    var movie_default_response = await axios.get(default_movie_url)
    var resp = movie_default_response.data
    if (resp.length > 0) {
      return [...movie_default_response.data]
    } else {
      var movie_alternate_response = await axios.get(alternative_movie_url)
      var resp = movie_alternate_response.data
      if (resp.length > 0) {
        return [...movie_alternate_response.data]
      } else {
        return []
      }
    }
  } catch (error) {
    console.log(error.response)
    return []
  }
}

export { getSearchData }
