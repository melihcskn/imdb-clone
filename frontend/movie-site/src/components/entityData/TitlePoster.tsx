interface TitlePosterProps {
  poster: string;
}

export default function TitlePoster({ poster }: TitlePosterProps) {
  return (
    <img
      style={{
        maxHeight: "inherit",
      }}
      src={poster}
    />
  );
}

export { TitlePoster };
