import { Container, Row, Carousel, Col } from 'react-bootstrap';
import { Navigation } from "swiper";

import { Swiper, SwiperSlide } from "swiper/react";
import 'swiper/css';
import 'swiper/css/navigation';
import React, { useState, useEffect } from 'react';
import { ProductResponse } from '../../../types/type';
import productService from '../../../service/productService';
const BookFeature: React.FC = () => {
    const [data, setData] = useState<Array<ProductResponse>>([]);
    useEffect(() => {
        productService.loadDataFeature().then(
            (res) => {
                setData(res.data);
            }
        )
    }, []);

    return (
        <Container className='space-bottom mt-5'>
            <div style={{ display: "flex", justifyContent: "space-between" }} className="mb-4">
                <div className='home-title-type'>
                    Books Feature
                </div>
                <div>
                    View All
                </div>
            </div>
            <Swiper
                slidesPerView={5}
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
                    },
                    1480: {
                        slidesPerView: 5
                    }
                }
                }
            >
                <Row>


                    {
                        data.map(
                            item => {
                                return (
                                    <SwiperSlide key={item.id}>
                                        <Col lg={10} md={10} sm={8} xs={5} className="item">
                                            <div className='item-info' style={{ padding: "5%" }}>
                                                <div style={{ paddingTop: "15%", paddingLeft: "20%", paddingRight: "20%" }}>
                                                    <img style={{ width: "100%" }} src={item.imgUrl}></img>
                                                </div>

                                                <div className="product-loop-info">
                                                    <div className=' h6 text-lh-md product-mb-2 text-height-2 crop-text-2 name-height'>
                                                        <a href={`productDetail/${item.id}`} >{item.productName}</a>
                                                    </div>
                                                    <div className='name-author-product'>
                                                        Jessica Simson
                                                    </div>
                                                    <div className='product-price'>
                                                        {item.maxPrice === item.minPrice ? `$${item.maxPrice}` : `$${item.minPrice} - $${item.maxPrice}`}
                                                    </div>
                                                </div>
                                                <div className='product-hover'>
                                                    <div style={{ marginRight: "30%" }} className="pointer add-to-cart-text">
                                                        <a href={`productDetail/${item.id}`} >Select Option</a>
                                                    </div>
                                                    <div className="wish-list-icon">
                                                        <i className="bi bi-heart icon pointer"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </Col>
                                    </SwiperSlide>
                                );
                            }
                        )
                    }


                </Row>


            </Swiper>
        </Container>
    )
}
export default BookFeature;