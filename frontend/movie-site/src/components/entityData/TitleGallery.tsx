import styles from "./entity.module.css";

interface ITitleGalleryProps {
  videoNumber: number;
  photoNumber: number;
}

export default function TitleGallery({
  videoNumber,
  photoNumber,
}: ITitleGalleryProps) {
  const videoLink: string = "/";
  const photoLink: string = "/";

  return (
    <div className={styles.imdb__gallery}>
      <a href={videoLink} className={styles.imdb__gallery__box}>
        <i className="bi bi-play-btn-fill h2" />
        <div>{videoNumber} Videos</div>
      </a>
      <a href={photoLink} className={styles.imdb__gallery__box}>
        <i className="bi bi-images h2" />
        <div>{photoNumber} Photos</div>
      </a>
    </div>
  );
}

export { TitleGallery };
