import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { ROUTES } from "../../../constants";
import { useVideoApi } from "../../../hooks/api/video/use-video-api";
import { useVimeoApi } from "../../../hooks/api/vimeo/use-vimeo-api";
import {
  HeaderComponent,
  InputSeparatedSupportMaterial,
  InputVideoFile,
  Modal,
} from "../../components";
import { SectionComponent } from "../../components/section/section-component";
import { StarRating } from "../../components/start/start.component";

export function VideosAddScreen({ fromModal, courseId }) {
  const { upload } = useVimeoApi();
  const [videos, setVideos] = useState([]);
  const [page, setPage] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const videoApi = useVideoApi();
  const { includeSupportMaterial } = useVideoApi();

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

  const handleIncludeVideoClick = async (videoId, courseId) => {
    const response = await videoApi.includeVideoIntoCourse(videoId, courseId);
  };

  const handleClickRemoveVideo = async (videoId) => {
    const response = await videoApi.removeVideo(videoId);
  };

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <>
    <div className=" video-container">
    <h1>Vídeos da comunidade</h1>

        <div className="course-video-card">
        {videos?.map((video, index) => {
            return (
            <>
            <button
                className="btn third"
                onClick={() =>
                handleIncludeVideoClick(video.id, courseId)
                }
            >
                Adicionar Vídeo ao Curso
            </button>
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
                    <div  className="star-rating">
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
    </>
  );
}