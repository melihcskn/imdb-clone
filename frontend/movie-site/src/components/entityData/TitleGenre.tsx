import styles from "./entity.module.css";

interface ITitleGenreProps {
  genreLink: string;
  genreName: string;
}

export default function TitleGenre({ genreLink, genreName }: ITitleGenreProps) {
  return (
    <span className={styles.imdb__genre}>
      <a href={genreLink}>{genreName}</a>
    </span>
  );
}
