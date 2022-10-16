import { useEffect, useState } from "react/cjs/react.development";
import {
  HeaderComponent,
  InputSeparatedFile,
  InputVideoFile,
  Modal,
} from "../../components";
import { SectionComponent } from "../../components/section/section-component";
import { useVideoApi } from "../../../hooks/api/video/use-video-api";
import { useParams } from "react-router-dom";
import { ORDER, SCORE } from "../../../constants";
import { StarRating } from "../../components/start/start.component";
import "./evaluations.screen.css";

export const EvaluationsScreen = () => {
  const videoApi = useVideoApi();
  const [evaluations, setEvaluations] = useState([]);
  const { videoId } = useParams();
  const [page, setPage] = useState(0);
  const [titleFilter, setTitleFilter] = useState("");
  const [scoreFilter, setScoreFilter] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [video, setVideo] = useState();

  useEffect(() => {
    const getAllEvaluations = async () => {
      const response = await videoApi.listVideoEvaluations(videoId, page, "", "", "Desc");
      const videoResponse = await videoApi.getDetailedVideo(videoId);
      setVideo(videoResponse);
      setEvaluations(response?.content);
    };
    getAllEvaluations();
  }, [videoApi, page]);

  async function handleFilterChange(event) {
    const filter = event.target.value;
    setTitleFilter(titleFilter);
    const response = await videoApi.listVideoEvaluations(
      videoId,
      page,
      "",
      filter,
      "Desc"
    );
    setEvaluations(response?.content);
  }

  async function handleScoreFilterChange(event) {
    const filter = event.target.value;
    setScoreFilter(filter);
    const response = await videoApi.listVideoEvaluations(
      videoId,
      page,
      scoreFilter,
      titleFilter,
      "Desc"
    );
    setEvaluations(response?.content);
  }

  async function handleOrderFilterChange(event) {
    const filter = event.target.value;
    const response = await videoApi.listVideoEvaluations(
      videoId,
      page,
      scoreFilter,
      titleFilter,
      filter
    );
    setEvaluations(response?.content);
  }

  function handleBackPage() {
    if (page > 0) {
      setPage(page - 1);
    }
  }

  function handleNextPage() {
    setPage(page + 1);
  }

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const evaluateVideo = async (data) => {
    const response = await videoApi.evaluateVideo(data);
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
        <div className="main-container evaluations">
        <div className="top-video no-background">
          <iframe
            src={video?.link}
            frameBorder="0"
            allow="autoplay; fullscreen; picture-in-picture"
            title={video?.name}
            className="video-card"
          ></iframe>
          <h2 className="video-title">{video?.name}</h2>
          <StarRating filled={video?.score}/>
          <InputSeparatedFile
            children={"evaluation"}
            includeCallback={evaluateVideo}
            redirectUrl={"/video-details"}
            includeRoadmapInTeam={false}
            fromVideo={true}
            videoId={videoId}
          />
        </div>  
        
        <div className="rightBox">
        <form action="" className="form-criacao">
                <label htmlFor="filter" className="form-label">
                  Buscar por
                </label>
                <input class="form__field" name="filter" type="text" onChange={handleFilterChange} />

                <select
                  name="score"
                  onChange={handleScoreFilterChange}
                  className="select"
                >
                  <option selected disabled>
                  Filtre por nota
                  </option>
                  {SCORE.map(score =>
                      <option id="score" value={score}>{score}</option>
                    )}
                </select>

                <select
                    name="order"
                    onChange={handleOrderFilterChange}
                    className="select"
                >
                  <option selected disabled>
                  Filtre por ordenação
                  </option>
                  {ORDER.map(order =>
                      <option id="order" value={order}>{order}</option>
                    )}
              </select>
          </form>
          <h1 className="video-title">Avaliações</h1>
          <div className="evaluations-container">
            {evaluations?.map((evaluation, index) => {
              return (
                <>
                  <div className="evaluation-box">
                    <div className="evaluation-top-box">
                      <h1>{evaluation?.dateTimeCreation}</h1>
                      <StarRating filled={evaluation?.score}/>
                    </div>

                    <h1 className="evaluation-comment">{evaluation?.comment}</h1>
                  </div>
                </>
              );
            })}
            <div className="pagination">
              <button onClick={handleBackPage}>
                ❮
              </button>
              <button onClick={handleNextPage}>
                ❯
              </button>
            </div>
          </div>
        </div>
        </div>
        </SectionComponent>
    </>
  );
};
