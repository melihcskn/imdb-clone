import styles from './entity.module.css'

function nFormatter(num, digits) {
  const lookup = [
    { value: 1, symbol: '' },
    { value: 1e3, symbol: 'k' },
    { value: 1e6, symbol: 'M' },
  ]
  const regexp = /\.0+$|(?<=\.[0-9]*[1-9])0+$/
  const item = lookup.findLast((item) => num >= item.value)
  return item
    ? (num / item.value).toFixed(digits).replace(regexp, '').concat(item.symbol)
    : '0'
}

function fixNumber(number) {
  return Math.round((number * 10) / 10).toFixed(1)
}

export default function TitleScore(props) {
  const { type } = props
  const ratings = {
    imdb: { rating: 9, voteNumber: 2800000, maxRating: '/10' },
    user: { rating: 9, maxRating: '/10' },
    popularity: { rating: '100', maxRating: '' },
  }
  const types = {
    imdbRating: {
      text: 'IMDb RATING',
      rating: fixNumber(ratings.imdb.rating),
      maxRating: ratings.imdb.maxRating,
      number: nFormatter(ratings.imdb.voteNumber, 1),
      class: 'bi bi-star-fill h4',
      link: '/',
      style: { color: '#f6ca2a' },
    },
    userRating: {
      text: 'YOUR RATING',
      rating: fixNumber(ratings.user.rating),
      maxRating: ratings.user.maxRating,
      class: ratings.user.rating ? 'bi bi-star-fill h4' : 'bi bi-star h4',
      link: '/',
      style: { color: '#64A1F0' },
    },
    popularity: {
      text: 'POPULARITY',
      rating: ratings.popularity.rating,
      class: 'bi bi-graph-up h4',
      link: '/',
      style: { color: '#6AA355' },
    },
  }

  return (
    <span className={styles.title}>
      <div className={styles.imdb__rating__text}>{types[`${type}`].text}</div>
      <a href={types[`${type}`].link} className={styles.imdb__rating__icon}>
        <span>
          <i
            style={types[`${type}`].style}
            className={types[`${type}`].class}
          />
          <div className={styles.imdb__rating}>
            <span>
              <p className={styles.imdb__rating__score}>
                {types[`${type}`].rating}
              </p>
              <p className={styles.imdb__rating__max}>
                {types[`${type}`].maxRating}
              </p>
            </span>
            {types[`${type}`].number && (
              <p className={styles.imdb__rating__number}>
                {types[`${type}`].number}
              </p>
            )}
          </div>
        </span>
      </a>
    </span>
  )
}
