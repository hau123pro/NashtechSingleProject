
import StarIcon from '@mui/icons-material/Star';
import StarOutlineIcon from '@mui/icons-material/StarOutline';
import { Container, Row, Col } from 'react-bootstrap';
import Button from '@mui/material/Button';
import TextareaAutosize from '@mui/material/TextareaAutosize';
import Rating from '@mui/material/Rating';
const TabReview: React.FC = () => {
    return (
        <Row className='d-flex-center'>
            <Col xs={8}>
                <Row>
                    <Col xs={6} className='pt-4'>
                        <div>
                            <h5>Customer Reviews</h5>
                        </div>
                        <div className='d-flex align-item-center'>
                            <div className='font-size-15 font-weight-bold'>
                                4.5
                            </div>
                            <div className='ms-3'>
                                <div className='ps-1'>2 reviews</div>
                                <div>
                                    <Rating name="read-only" defaultValue={3} readOnly />
                                </div>
                            </div>
                        </div>
                        <div className='d-flex '  >
                            {/* <a href='#' className='pt-3 pb-3 pe-5 ps-5 border-btn'>See all reviews</a> */}
                            <Button variant="contained" className='pt-3 pb-3 pe-5 ps-5 border-btn-black btn-bg-white' >
                                See all reviews
                            </Button>
                        </div>
                    </Col>
                    <Col xs={6}>
                        <Row className='align-item-center pt-5'>
                            <Col xs={2}>
                                5 star
                            </Col>
                            <Col xs={8}>
                                <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                    <div style={{ width: "50%", backgroundColor: '#ffbd00', height: "100%", borderRadius: "20px" }}></div>
                                </div>
                            </Col>
                            <Col xs={2}>
                                1
                            </Col>
                        </Row>
                        <Row className='align-item-center pt-2'>
                            <Col xs={2}>
                                4 star
                            </Col>
                            <Col xs={8}>
                                <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                    <div style={{ width: "50%", backgroundColor: '#ffbd00', height: "100%", borderRadius: "20px" }}></div>
                                </div>
                            </Col>
                            <Col xs={2}>
                                1
                            </Col>
                        </Row>
                        <Row className='align-item-center pt-2'>
                            <Col xs={2}>
                                3 star
                            </Col>
                            <Col xs={8}>
                                <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                    <div style={{ width: "0%", backgroundColor: '#ffbd00', height: "100%", borderRadius: "20px" }}></div>
                                </div>
                            </Col>
                            <Col xs={2}>
                                0
                            </Col>
                        </Row>
                        <Row className='align-item-center pt-2'>
                            <Col xs={2}>
                                2 star
                            </Col>
                            <Col xs={8}>
                                <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                    <div style={{ width: "0%", backgroundColor: '#ffbd00', height: "100%", borderRadius: "20px" }}></div>
                                </div>
                            </Col>
                            <Col xs={2}>
                                0
                            </Col>
                        </Row>
                        <Row className='align-item-center pt-2'>
                            <Col xs={2}>
                                1 star
                            </Col>
                            <Col xs={8}>
                                <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                    <div style={{ width: "0%", backgroundColor: '#ffbd00', height: "100%", borderRadius: "20px" }}></div>
                                </div>
                            </Col>
                            <Col xs={2}>
                                0
                            </Col>
                        </Row>
                    </Col>
                </Row>
                <div className='mt-10'>
                    <Row className=' mt-5 font-size-reviews border-bottom'>
                        <div className='d-flex'>
                            <div>
                                Name
                            </div>
                            <div className='ps-2'>
                                <Rating name="read-only" defaultValue={5} size="small" readOnly />
                            </div>
                        </div>
                        <div className='pt-3'>
                            Content here!!!!!!!!!!!!!!!
                        </div>
                        <div className='pt-4 text-gray pb-4'>
                            August 17, 2020
                        </div>
                    </Row>
                    <Row className=' mt-5 font-size-reviews border-bottom'>
                        <div className='d-flex'>
                            <div> Name</div>
                            <div className='ps-2'>
                                <Rating name="read-only" defaultValue={4} size="small" readOnly />
                            </div>
                        </div>
                        <div className='pt-3'> Content here!!!!!!!!!!!!!!!</div>
                        <div className='pt-4 text-gray pb-4'>August 17, 2020</div>
                    </Row>
                </div>
                <div className='mt-10'>
                    <div><h2> Write a reviews</h2></div>
                    <div className='d-flex align-item-center'>
                        <div>Select a rating *</div>
                        <div className='ps-4'>
                            <Rating name="no-value" defaultValue={0} />
                        </div>
                    </div>
                    <div>
                        <div className='pt-3'>Details please! Your review helps other shoppers. *</div>
                        <div className='pt-3'>
                            <TextareaAutosize
                                aria-label="empty textarea"
                                style={{ width: '100%', height: 200 }}
                            />
                        </div>
                        <div className='pt-3'>
                            <Button variant="contained" className='pt-3 pb-3 pe-5 ps-5 border-btn-black btn-bg-white' >
                                See all reviews
                            </Button>
                        </div>
                    </div>
                </div>
            </Col>

        </Row>
    )
}
export default TabReview;