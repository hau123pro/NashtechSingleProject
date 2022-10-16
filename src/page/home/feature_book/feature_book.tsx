import { Container, Row, Col } from 'react-bootstrap';


const FeatureBook: React.FC = () => {
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
            <div className="mb-4">
                <div className='home-title-type text-center'>
                    Featured Books
                </div>
            </div>
            <ul className='justifi-center mb-5' style={{ display: "flex", listStyle: "none", paddingRight: "2rem" }}>
                <li className='mx-5' style={{ fontSize: "1.2rem" }}>
                    <a href='#' className='feature-book-active'>Feature</a>
                </li>
                <li className='mx-5' style={{ fontSize: "1.2rem" }}>
                    New Arrivals
                </li>
                <li className='mx-5' style={{ fontSize: "1.2rem" }}>
                    Most View
                </li>
            </ul>
            <Row>
                {cardsDefault.map(
                    item => {
                        return (
                            <Col key={item.id} lg={2} md={5} sm={6} xs={6} className="item">
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
                                        <div style={{ marginRight: "65%" }} className="pointer add-to-cart-text">
                                            <a href=''><i className="bi bi-bag"></i></a>
                                        </div>
                                        <div className="wish-list-icon">
                                            <i style={{ marginLeft: "26%", marginTop: "10%" }} className="bi bi-heart icon pointer"></i>
                                        </div>
                                    </div>
                                </div>
                            </Col>
                        );
                    }
                )
                }
            </Row>
        </Container>
    )
}
export default FeatureBook;