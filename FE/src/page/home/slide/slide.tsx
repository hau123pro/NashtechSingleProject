import React from 'react';
import { Carousel, Row, Col, Container } from 'react-bootstrap';
import slide01 from "../../../images/slide-book-01.jpg";
import "./slide.css";
const Slider: React.FC = () => {
    return (<>
        <Carousel>
            <Carousel.Item>
                <img className='shadow' style={{ width: '100%' }} src={slide01}></img>
                <Row className='banner-message slider-middle' style={{ width: "100%" }}>
                    <Col xs={5} >
                        <div className="slider-text fade-it-up">Feature of the book</div>
                        <div className='slider-more fade-it-left'>See now</div>
                    </Col>
                    <Col xs={6} className="fade-it-right">
                        <img src="https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/img1-12.png" style={{ width: '100%' }} alt="" />
                    </Col>
                </Row>
            </Carousel.Item>
        </Carousel>
    </>)
}
export default Slider;