import { Container, Row, Carousel, Col } from 'react-bootstrap';
import { Navigation } from "swiper";

import { Swiper, SwiperSlide } from "swiper/react";
import 'swiper/css';
import 'swiper/css/navigation';
import "bootstrap-icons/font/bootstrap-icons.css";
import React, { useState, useEffect } from 'react';
import { CategoryResponse } from '../../../types/type';
import categoryService from '../../../service/categoryService';
import { Link } from 'react-router-dom';
const FeatureCategories: React.FC = () => {
    const [category, setCategory] = useState<Array<CategoryResponse>>([]);
    useEffect(() => {
        categoryService.getSomeCategory().then(
            (res) => {
                setCategory(res.data);
            }
        ).catch(
            (err) => {
                alert(err.response.data.message);
            }
        )
    }, [])
    return (
        <Container className='space-bottom'>
            <div style={{ display: "flex", justifyContent: "space-between" }} className="mb-4">
                <div className='home-title-type'>
                    Some Categories
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
                                            <Col lg={9} md={8} sm={8} xs={8} className="pt-5 pb-5 wrap-category-type" style={{ backgroundColor: "#fff6f6" }}>
                                                <div className='name-category mt-3'><h5>{item.name}</h5></div>
                                                <Link to={'/shop'}>
                                                    <div className='mb-3'>Shop now</div>
                                                </Link>
                                            </Col>
                                        </SwiperSlide>
                                    )
                                })}
                    </Row>

                </Swiper>
            </div>

        </Container >
    )
}
export default FeatureCategories;

