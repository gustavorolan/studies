import "./videos.style.css";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { ROUTES } from "../../../constants";
import { useVideoApi } from "../../../hooks/api/video/use-video-api";
import { useVimeoApi } from "../../../hooks/api/vimeo/use-vimeo-api";
import {
  HeaderComponent,
  InputVideoFile,
  Modal,
  StarRating,
  SectionComponent,
} from "../../components";

export function VideosScreen({ fromModal, courseId }) {
  const { upload } = useVimeoApi();
  const [videos, setVideos] = useState([]);
  const [page, setPage] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const videoApi = useVideoApi();

  useEffect(() => {
    async function listVideosByPage() {
      const videosResponse = await videoApi.listVideosByPage(page, "");
      setVideos(videosResponse);
    }
    listVideosByPage();
  }, [videoApi, page]);

  function handleBackPage() {
    if (page > 0) {
      setPage(page - 1);
    }
  }
  function handleNextPage() {
    setPage(page + 1);
  }

  async function handleFilterChange(event) {
    const filter = event.target.value;
    const videosResponse = await videoApi.listVideosByPage(page, filter);
    setVideos(videosResponse);
  }

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
        <Modal
          children={
            <InputVideoFile children={"Submeter vídeo"} callback={upload} />
          }
          isModalOpen={isModalOpen}
          openModalClick={openModal}
        />
        <div className=" video-container">
          <div className="top-container">
            <h1>Vídeos da comunidade</h1>
            <div className="search-create-container">
              <input
                name="filter"
                type="text"
                onChange={handleFilterChange}
                placeholder="Buscar"
              />
              <button className="btn third" onClick={openModal}>
                +
              </button>
            </div>
          </div>

          <div className="course-video-card">
            {videos?.map((video, index) => {
              return (
                <>
                  <iframe
                    src={`${video.link}`}
                    frameBorder="0"
                    allow="autoplay; fullscreen; picture-in-picture"
                    title={video.name}
                    className="video-card"
                  ></iframe>
                  <div className="title">
                    {video.supportMaterial ? (
                      <a href={ROUTES.API_BASE_URL + video.supportMaterial}>
                        Material Apoio
                      </a>
                    ) : (
                      <div></div>
                    )}
                    <Link to={`/evaluations/${video.id}`}>
                      <div className="star-rating">
                        <StarRating filled={video.score} />
                      </div>
                    </Link>
                  </div>
                  <p>{video.description}</p>
                </>
              );
            })}
          </div>

          <div className="pageButtons">
            <button className="btn fourth" onClick={handleBackPage}>
              ⇧
            </button>
            <button className="btn fourth" onClick={handleNextPage}>
              ⇩
            </button>
          </div>
        </div>
      </SectionComponent>
    </>
  );
}
