export default function TitlePoster(props) {
  const { poster } = props
  return (
    <img
      style={{
        maxHeight: 'inherit',
      }}
      src={poster}
    />
  )
}

export { TitlePoster }
