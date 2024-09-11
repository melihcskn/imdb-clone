import React from 'react'
import ReactPlayer from 'react-player'

export default function TitleVideo(props) {
  const { trailer: trailerLink } = props

  return (
    <ReactPlayer
      controls={true}
      url={trailerLink}
      height={'inherit'}
      width={'inherit'}
    />
  )
}

export { TitleVideo }
