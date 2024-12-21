import React from "react";
import ReactPlayer from "react-player";

interface TitleVideoProps {
  trailer: string;
}

export default function TitleVideo({ trailer: trailerLink }: TitleVideoProps) {
  return (
    <ReactPlayer
      controls={true}
      url={trailerLink}
      height={"inherit"}
      width={"inherit"}
    />
  );
}

export { TitleVideo };
