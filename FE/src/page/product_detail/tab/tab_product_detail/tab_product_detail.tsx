import { Container, Row, Col } from 'react-bootstrap';
import { ProductDetailContext, productDetailContext, ProductDetailState } from '../../../../context/productDetailContext';
import { CategoryResponse, FormatProductResponse, FormatResponse } from '../../../../types/type';
import { useEffect, useState, useContext } from "react";

const TabDetail: React.FC = () => {
    const { detailState, dispatchDetail }: productDetailContext = useContext(ProductDetailContext);
    return (
        <Row className='d-flex-center'>
            <Col xs={8}>
                <div className='ps-5'>
                    <Row className='mt-5' style={{ backgroundColor: "rgba(240, 242, 245, 0.509) " }}>
                        <Col xs={6} className='p-2'>
                            Category:
                        </Col>
                        <Col xs={6} className='p-2'>
                            {
                                detailState.category.map(
                                    (item: CategoryResponse) => {
                                        return (
                                            <div>{item.name}</div>
                                        );
                                    }
                                )
                            }
                        </Col>
                    </Row>
                    <Row className='mt-3' >
                        <Col xs={6} className='p-2'>
                            Book Author:
                        </Col>
                        <Col xs={6} className='p-2'>
                            {`${detailState.author?.firstName} ${detailState.author?.lastName}`}
                        </Col>
                    </Row>
                    <Row className='mt-3' style={{ backgroundColor: "rgba(240, 242, 245, 0.509) " }}>
                        <Col xs={6} className='p-2'>
                            Book Format:
                        </Col>
                        <Col xs={6} className='p-2'>
                            {
                                detailState.format.map(
                                    (item: FormatProductResponse) => {
                                        return (
                                            <div>{item.formatName}</div>
                                        );
                                    }
                                )
                            }
                        </Col>
                    </Row>
                </div>
            </Col>
        </Row>
    )
}
export default TabDetail;