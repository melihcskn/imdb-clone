import axios from 'axios'
import { ApiURLs } from '@/constants'

async function getData(dispatch, id, type) {
  try {
    dispatch({ type: 'UPDATE_SEARCH_DATA', payload: { isLoading: true } })
    let fetchedData = null
    if (type == 'movie') {
      fetchedData = await getMovieData(id)
      dispatch({
        type: 'UPDATE_SEARCH_DATA',
        payload: { data: { ...fetchedData.data }, isLoading: false },
      })
    } else if (type == 'actor') {
      fetchedData = await getActorData(id)
    }
  } catch (error) {
    console.log(error)
  }
}

const getActorData = async function (id) {
  const actorUrl = ApiURLs.getActorById.concat(id)
  try {
    let actorData = await axios.get(actorUrl)
    return actorData
  } catch (error) {
    console.log(error)
    return null
  }
}

const getMovieData = async function (id) {
  const movieUrl = ApiURLs.getMovieById.concat(id)
  try {
    let movieData = await axios.get(movieUrl)
    return movieData
  } catch (error) {
    console.log(error)
    return null
  }
}

export { getData }
