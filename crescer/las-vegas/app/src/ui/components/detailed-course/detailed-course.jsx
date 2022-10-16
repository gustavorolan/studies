import "./detailed-course.css";
import { Link, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useCourseApi } from "../../../hooks/api/course/use-course-api";
import { useVideoApi } from "../../../hooks/api/video/use-video-api";
import { HeaderComponent, SectionComponent, StarRating, Modal } from "../index";
import { FaStar } from "react-icons/fa";
import shareButton from "../../../assets/image/share-button.svg";
import { useUserApi } from "../../../hooks/api/user/use-user-api";
import { VideosAddScreen } from "../../screens";
import { IMAGE, ROUTES } from "../../../constants";

export const DetailedCourse = () => {
  const [course, setCourse] = useState([]);
  const [showVideoIndex, setShowVideoIndex] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [user, setUser] = useState();
  const courseApi = useCourseApi();
  const videoApi = useVideoApi();
  const { courseId } = useParams();
  const { getDetailedUser } = useUserApi();

  useEffect(() => {
    const getDetailedCourse = async () => {
      const response = await courseApi.getDetailedCourse(courseId);
      const userResponse = await getDetailedUser(response.authorId);
      setUser(userResponse);
      setCourse(response);
    };
    getDetailedCourse();
  }, [courseApi, courseId]);

  const handleRemoveVideoFromCourse = async (videoId, courseId) => {
    await videoApi.removeVideoFromCourse(videoId, courseId);
  };

  const changeShowVideoIndex = (index) => {
    setShowVideoIndex(index);
  };

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <>
      <HeaderComponent />
      <SectionComponent>
        <div className="detailed-course">
          <div className="left-container">
            <div className="top-box">
              <div className="behind-box">
                {course?.image?.id && (
                  <img
                    className="img"
                    src={`${ROUTES.API_BASE_URL}/download/${course?.image?.id}`}
                    alt="Course"
                  />
                )}
                <h2 className="title">{course.name}</h2>
                <h2 className="description">{course.description}</h2>
                <h2 className="small-description">
                  Criado por {course.authorName} em {course.dateTimeCreation}
                </h2>
                <h2 className="small-description">0% assistido</h2>
              </div>
            </div>

            <button className="trilha margin-top" onClick={openModal}>
                Adicionar Vídeo ao Curso
              </button>
              <Modal
                children={
                  <VideosAddScreen fromModal={true} courseId={courseId} />
                }
                isModalOpen={isModalOpen}
                openModalClick={openModal}
              />

            <div className="course-videos">
              {course?.videos?.map((video, index) => {
                return (
                  <div key={index} className="detailed-course">
                    <div className="left-container">
                      <button
                        className="checkmark"
                        onClick={() => changeShowVideoIndex(index)}
                      >
                        <div class="checkmark_circle"></div>
                        <div class="checkmark_stem"></div>
                        <div class="checkmark_kick"></div>
                      </button>
                      <span className="line"></span>
                    </div>

                    <div className="video-small-box">
                      <h1 className="video-small-title">{video.name}</h1>
                      <h3 className="video-small-description">
                        {video.description}
                      </h3>
                      <div className="video-icons">
                        <button class="play"></button>
                        <button type="submit" className="submit-comment-button">
                          <img src={shareButton} alt="submitIcon" />
                        </button>
                        <Link
                          to={`/evaluations/${course?.videos[showVideoIndex]?.id}`}
                        >
                          <button>
                            <FaStar
                              className="star"
                              color={"black"}
                              size={15}
                            />
                          </button>
                        </Link>
                      </div>
                    </div>
                  </div>
                );
              })}
              <span className="endline-button inner"></span>
            </div>
          </div>

          <div className="right-container">
            {course?.videos?.length > 0 && (
              <>
                <div className="top-video">
                  <div className="behind-box video-background-low">
                    <div className="behind-box video-background">
                      <iframe
                        src={`${course?.videos[showVideoIndex]?.link}`}
                        frameBorder="0"
                        allow="autoplay; fullscreen; picture-in-picture"
                        title={course?.videos[showVideoIndex]?.name}
                        className="video-card"
                      ></iframe>
                      <div className="video-icons-right">
                        <div>
                          <Link to={`/user-details/${user?.id}`}>
                            <button>
                              <div className="detailed-course">
                                {user?.imageId && (
                                  <img
                                    className="img-user"
                                    src={`${ROUTES.API_BASE_URL}${IMAGE.DOWNLOAD}/${user?.imageId}`}
                                    alt="User"
                                  />
                                )}
                                <div>
                                  <h2 className="video-small-title">
                                    {user?.fullName}
                                  </h2>
                                  <h2 className="video-small-description">
                                    {user?.description}
                                  </h2>
                                  <Link
                                    to={`/evaluations/${course?.videos[showVideoIndex]?.id}`}
                                  >
                                    <button>
                                      <StarRating filled={user?.assessment} />
                                    </button>
                                  </Link>
                                </div>
                              </div>
                            </button>
                          </Link>
                        </div>
                        <button class="button play"></button>
                        <button type="submit" className="submit-comment-button">
                          <img src={shareButton} alt="submitIcon" />
                        </button>
                        <Link
                          to={`/evaluations/${course?.videos[showVideoIndex]?.id}`}
                        >
                          <button>
                            <FaStar
                              className="star"
                              color={"black"}
                              size={15}
                            />
                          </button>
                        </Link>
                      </div>
                      {/* <button
                            onClick={() =>
                              handleRemoveVideoFromCourse(
                                course?.videos[showVideoIndex]?.id,
                                courseId
                              )
                            }
                          >
                            Remover vídeo de curso
                          </button> */}
                    </div>
                  </div>
                </div>
                <div className="video">
                  <h1 className="video-title">
                    {course?.videos[showVideoIndex]?.name}
                  </h1>
                  <h2 className="video-description">
                    {course?.videos[showVideoIndex]?.description}
                  </h2>
                  <StarRating filled={course?.videos[showVideoIndex]?.score} />

                  <div className="contain">
                    <h3 className="title">Material de apoio</h3>
                    <a
                      className="video-description"
                      href={`ROUTES.API_BASE_URL/videos/${course?.videos[showVideoIndex]?.id}/materialSupport`}
                    >
                      Download
                    </a>
                  </div>
                </div>
              </>
            )}
          </div>
        </div>
      </SectionComponent>
    </>
  );
};
