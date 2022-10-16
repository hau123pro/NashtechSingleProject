import { Container, Row, Carousel, Col } from 'react-bootstrap';
import { Navigation } from "swiper";

import { Swiper, SwiperSlide } from "swiper/react";
import 'swiper/css';
import 'swiper/css/navigation';
import "bootstrap-icons/font/bootstrap-icons.css";

const FeatureCategories: React.FC = () => {
    const category = [
        {
            id: 1,
            bgColor: "#faf1ff",
            icon: "bi bi-images",
            color: "#a200fc",
            categoryName: "Arts & Photography"
        },
        {
            id: 2,
            bgColor: "#faf4eb",
            icon: "bi bi-incognito",
            color: "black",
            categoryName: "Fiction"
        },
        {
            id: 3,
            bgColor: "#f4e6e5",
            icon: "bi bi-chat-square-heart",
            color: "rgb(248, 48, 48)",
            categoryName: "Arts & Photography"
        },
        {
            id: 4,
            bgColor: "#e6f2f4",
            icon: "bi bi-lungs",
            color: "#20c4ed",
            categoryName: "Arts & Photography"
        },
        {
            id: 5,
            bgColor: "#faf4eb",
            icon: "bi bi-mortarboard",
            color: "#f79400",
            categoryName: "Arts & Photography"
        },
    ]
    return (
        <Container className='space-bottom'>
            <div style={{ display: "flex", justifyContent: "space-between" }} className="mb-4">
                <div className='home-title-type'>
                    Feature Categories
                </div>
                <div>
                    All Categories
                </div>
            </div>


            <div style={{ width: "100%" }}>
                <Swiper
                    slidesPerView={4}
                    spaceBetween={0}
                    navigation={true}
                    modules={[Navigation]}
                    loop={true}
                    loopFillGroupWithBlank={true}
                    className="mySwiper"
                    breakpoints={{

                        300: {
                            slidesPerView: 1
                        },
                        768: {
                            slidesPerView: 2
                        },
                        992: {
                            slidesPerView: 3
                        },
                        1200: {
                            slidesPerView: 4
                        }

                    }
                    }
                >
                    <Row>
                        {
                            category.map(
                                item => {
                                    return (
                                        <SwiperSlide key={item.id}>
                                            <Col lg={9} md={8} sm={8} xs={8} className="pt-4 pb-5 wrap-category-type" style={{ backgroundColor: item.bgColor }}>
                                                <i className={`${item.icon} icon-category`} style={{ color: item.color }} ></i>
                                                <div className='name-category'>{item.categoryName}</div>
                                                <div>Shop now</div>
                                            </Col>
                                        </SwiperSlide>
                                    )
                                })}
                    </Row>

                </Swiper>
            </div>

        </Container>
    )
}
export default FeatureCategories;

