import { Container, Row, Carousel, Col } from 'react-bootstrap';
import { Navigation } from "swiper";

import { Swiper, SwiperSlide } from "swiper/react";
import 'swiper/css';
import 'swiper/css/navigation';
const BookBestSelling: React.FC = () => {
    const cardsDefault = [
        {
            id: "a",
            row: 1,
            col: 1,
            w: 1,
            h: 1,
            filter: ["test", "chart"],
            img: "https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/18-120x183.jpg",
            bname: "Dark in Death: An Eve Dallas Novel (In Death, Book 46)"
        },
        {
            id: "b",
            row: 1,
            col: 1,
            w: 1,
            h: 1,
            filter: ["test", "chart"],
            img: "https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg",
            bname: "Dark in Death: An Eve Dallas Novel (In Death, Book 46)"

        }, {
            id: "c",
            row: 1,
            col: 1,
            w: 1,
            h: 1,
            filter: ["test", "chart"],
            img: "https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/46-120x183.jpg",
            bname: "Dark in Death: An Eve Dallas Novel (In Death, Book 46)"

        }, {
            id: "d",
            row: 1,
            col: 1,
            w: 1,
            h: 1,
            filter: ["test", "chart"],
            img: "https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/30-300x449-1-120x183.jpg",
            bname: "Dark in Death: An Eve Dallas Novel (In Death, Book 46)"
        }
        , {
            id: "e",
            row: 1,
            col: 1,
            w: 1,
            h: 1,
            filter: ["test", "chart"],
            img: "https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/3-120x183.jpg",
            bname: "Think Like a Monk: Train Your Mind for Peace and Purpose Everyday"

        }
        , {
            id: "f",
            row: 1,
            col: 1,
            w: 1,
            h: 1,
            filter: ["test", "chart"],
            img: "https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/23-120x183.jpg",
            bname: "Dark in Death: An Eve Dallas Novel (In Death, Book 46)"
        }
    ];
    return (
        <Container className='space-bottom mt-5'>
            <div style={{ display: "flex", justifyContent: "space-between" }} className="mb-4">
                <div className='home-title-type'>
                    Bestselling Books
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
                        cardsDefault.map(
                            item => {
                                return (
                                    <SwiperSlide key={item.id}>
                                        <Col lg={10} md={10} sm={8} xs={5} className="item">
                                            <div className='item-info' style={{ padding: "5%" }}>
                                                <div style={{ paddingTop: "15%", paddingLeft: "20%", paddingRight: "20%" }}>
                                                    <img style={{ width: "100%" }} src={item.img}></img>
                                                </div>

                                                <div className="product-loop-info">
                                                    <div className=' h6 text-lh-md product-mb-2 text-height-2 crop-text-2 '>
                                                        <a href='#'>{item.bname}</a>
                                                    </div>
                                                    <div className='name-author-product'>
                                                        Jessica Simson
                                                    </div>
                                                    <div className='product-price'>
                                                        $20.20
                                                    </div>
                                                </div>
                                                <div className='product-hover'>
                                                    <div style={{ marginRight: "30%" }} className="pointer add-to-cart-text">
                                                        <a href=''>Add to cart</a>
                                                    </div>
                                                    <div className="wish-list-icon">
                                                        <i style={{ marginLeft: "23%", marginTop: "10%" }} className="bi bi-heart icon pointer"></i>
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
export default BookBestSelling;