
import StarIcon from '@mui/icons-material/Star';
import StarOutlineIcon from '@mui/icons-material/StarOutline';
import React, { SyntheticEvent, useEffect, useReducer, useState } from "react";
import { Row, Col } from 'react-bootstrap';
import Button from '@mui/material/Button';
import TextareaAutosize from '@mui/material/TextareaAutosize';
import Rating from '@mui/material/Rating';
import { Alert } from '@mui/material';
import { AuthContextInterface, ReviewResponse } from '../../../../types/type';
import './tab_review.css'
import { useContext } from 'react';
import { AuthContext } from '../../../../context/authContext';
import { useNavigate } from 'react-router-dom';
import reviewService from '../../../../service/reviewService';
import { productDetailContext, ProductDetailContext, ProductDetailState } from '../../../../context/productDetailContext';
export interface ReviewState {
    rating: number;
    productId: number;
    content: string | null,
    isEmptyContent: boolean,
    isEmptyRating: boolean
}
interface userContext {
    dispatchUser?: any,
    user?: AuthContextInterface
}


const reviewState: ReviewState = {
    rating: 0,
    productId: 0,
    content: null,
    isEmptyContent: false,
    isEmptyRating: false
}

const todoReducer = (state: any = reviewState, action: any) => {
    switch (action.type) {
        case 'set-rating':
            return {
                ...state,
                rating: action.payload,
                isEmptyRating: false
            };
        case 'set-productId':
            return {
                ...state,
                productId: action.payload,
            };
        case 'set-content':
            return {
                ...state,
                content: action.payload,
                isEmptyContent: false
            };
        case 'set-errorContent':
            return {
                ...state,
                isEmptyContent: true
            };
        case 'set-errorRating':
            return {
                ...state,
                isEmptyRating: true
            };
        default:
            throw new Error();
    }
}
const TabReview: React.FC = () => {
    const { detailState, dispatchDetail }: productDetailContext = useContext(ProductDetailContext);
    const { user }: userContext = useContext(AuthContext);
    const [state, dispatch] = useReducer(todoReducer, reviewState);
    const [star1, setStar1] = useState<number>(0);
    const [star2, setStar2] = useState<number>(0);
    const [star3, setStar3] = useState<number>(0);
    const [star4, setStar4] = useState<number>(0);
    const [star5, setStar5] = useState<number>(0);
    const [countReview, setCount] = useState<number>(0);
    const navigation = useNavigate();
    useEffect(() => {
        detailState?.review.forEach(function (value) {
            console.log(value);
            if (value.rating == 1) {
                var num = star1;
                setStar1(num + 1);

            } else if (value.rating == 2) {
                var num = star2;
                setStar2(num + 1);
            } else if (value.rating == 3) {
                var num = star3;
                setStar3(num + 1);
            } else if (value.rating == 4) {
                var num = star4;
                setStar4(num + 1);
            } else if (value.rating == 5) {
                var num = star5;
                setStar5(num + 1);
            }
        });
        // console.log(star);
        if (detailState?.review.length === 0)
            setCount(1);
        else setCount(detailState.review.length)
        dispatch({
            type: 'set-productId',
            payload: detailState.product?.id
        })
    }, [])
    useEffect(() => {
        console.log(star5 / detailState.review.length);
    }, [star5])
    const submitReview = () => {
        if (state.content === null)
            dispatch({
                type: 'set-errorContent',
                payload: ''
            });
        else
            if (state.rating === 0)
                dispatch({
                    type: 'set-errorRating',
                    payload: ''
                });
            else {
                const token = localStorage.getItem('token');
                if (!token) {
                    navigation("/login");
                }
                else {
                    var reviewList = detailState.review;
                    console.log(state);
                    reviewService.addReview(state, token).then(
                        (res) => {
                            dispatchDetail({
                                type: 'set-loadingAdd',
                                payload: true
                            })
                        }
                    ).catch(
                        (err) => {
                            alert(JSON.stringify(err.response));
                            console.log(JSON.stringify(err.response.data))
                        }
                    )
                }
            }
    }
    const handleChangeRating = (event: SyntheticEvent<Element, Event>, value: number | null) => {
        console.log(value);
        dispatch({
            type: 'set-rating',
            payload: value
        });
    }
    const handleChangeContent = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        dispatch({
            type: 'set-content',
            payload: e.target.value
        })
    }

    return (
        <>
            <Row className='d-flex-center'>
                <Col xs={8}>
                    <Row>
                        <Col xs={6} className='pt-4'>
                            <div>
                                <h5>Customer Reviews</h5>
                            </div>
                            <div className='d-flex align-item-center'>
                                <div className='font-size-15 font-weight-bold'>
                                    {detailState.product?.averageRating}
                                </div>
                                <div className='ms-3'>
                                    <div className='ps-1'>{detailState.review.length} review</div>
                                    <div>
                                        <Rating name="read-only" defaultValue={detailState.product?.averageRating} readOnly />
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
                                        <div className='rating_count' style={{ width: `${(star5 / countReview) * 100}%` }}></div>
                                    </div>
                                </Col>
                                <Col xs={2}>
                                    {star5}
                                </Col>
                            </Row>
                            <Row className='align-item-center pt-2'>
                                <Col xs={2}>
                                    4 star
                                </Col>
                                <Col xs={8}>
                                    <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                        <div className='rating_count' style={{ width: `${(star4 / countReview) * 100}%` }}></div>
                                    </div>
                                </Col>
                                <Col xs={2}>
                                    {star4}
                                </Col>
                            </Row>
                            <Row className='align-item-center pt-2'>
                                <Col xs={2}>
                                    3 star
                                </Col>
                                <Col xs={8}>
                                    <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                        <div className='rating_count' style={{ width: `${(star3 / countReview) * 100}%` }}></div>
                                    </div>
                                </Col>
                                <Col xs={2}>
                                    {star3}
                                </Col>
                            </Row>
                            <Row className='align-item-center pt-2'>
                                <Col xs={2}>
                                    2 star
                                </Col>
                                <Col xs={8}>
                                    <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                        <div className='rating_count' style={{ width: `${(star2 / countReview) * 100}%` }}></div>
                                    </div>
                                </Col>
                                <Col xs={2}>
                                    {star2}
                                </Col>
                            </Row>
                            <Row className='align-item-center pt-2'>
                                <Col xs={2}>
                                    1 star
                                </Col>
                                <Col xs={8}>
                                    <div style={{ height: '7px', width: "100%", backgroundColor: '#f6f5f3', borderRadius: "20px" }}>
                                        <div className='rating_count' style={{ width: `${(star1 / countReview) * 100}%` }}></div>
                                    </div>
                                </Col>
                                <Col xs={2}>
                                    {star1}
                                </Col>
                            </Row>
                        </Col>
                    </Row>
                    <div className='mt-10'>
                        {detailState?.review.map(
                            (item: ReviewResponse) => {
                                return (
                                    <Row className=' mt-5 font-size-reviews border-bottom'>
                                        <div className='d-flex'>
                                            <div>
                                                {item.userName}
                                            </div>
                                            <div className='ps-2'>
                                                <Rating name="read-only" defaultValue={item.rating} size="small" readOnly />
                                            </div>
                                        </div>
                                        <div className='pt-3'>
                                            {item.content}
                                        </div>
                                        <div className='pt-4 text-gray pb-4'>
                                            {item.dateCreate}
                                        </div>
                                    </Row>
                                );
                            }
                        )}
                    </div>
                    <div className='mt-10'>
                        <div><h2> Write a reviews</h2></div>
                        <div className='d-flex align-item-center'>
                            <div>Select a rating *</div>
                            <div className='ps-4'>
                                <Rating name="no-value" defaultValue={0} onChange={handleChangeRating} />

                            </div>

                        </div>
                        {state.isEmptyRating ? <Alert severity="error">Rating canot 0</Alert> : ''}
                        <div>
                            <div className='pt-3'>Details please! Your review helps other shoppers. *</div>
                            <div className='pt-3'>
                                <TextareaAutosize
                                    aria-label="empty textarea"
                                    style={{ width: '100%', height: 200 }}
                                    onChange={handleChangeContent}
                                />
                                {state.isEmptyContent ? <Alert severity="error">Content canot null</Alert> : ''}
                            </div>
                            <div className='pt-3'>
                                <Button variant="contained" className='pt-3 pb-3 pe-5 ps-5 border-btn-black btn-bg-white' onClick={submitReview} >
                                    See all reviews
                                </Button>
                            </div>
                        </div>
                    </div>
                </Col>

            </Row>
        </>
    )
}
export default TabReview;