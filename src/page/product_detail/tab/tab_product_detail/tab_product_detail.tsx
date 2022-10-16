import { Container, Row, Col } from 'react-bootstrap';

const TabDetail: React.FC = () => {
    return (
        <Row className='d-flex-center'>
            <Col xs={8}>
                <div className='ps-5'>
                    <Row className='mt-5'>
                        <Col xs={6}>
                            Category:
                        </Col>
                        <Col xs={6}>
                            Thriller & Suspend
                        </Col>
                    </Row>
                    <Row className='mt-3'>
                        <Col xs={6}>
                            Book Author:
                        </Col>
                        <Col xs={6}>
                            Thriller & Suspend
                        </Col>
                    </Row>
                    <Row className='mt-3'>
                        <Col xs={6}>
                            Book Format:
                        </Col>
                        <Col xs={6}>
                            Thriller & Suspend
                        </Col>
                    </Row>
                </div>
            </Col>
        </Row>
    )
}
export default TabDetail;