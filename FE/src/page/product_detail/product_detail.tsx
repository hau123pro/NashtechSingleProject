
import { Container, Row, Col } from 'react-bootstrap';
import { useEffect, useState, useReducer, useContext } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import 'swiper/css';
import 'swiper/css/navigation';
import { Navigation } from "swiper";
import { Alert } from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import AddIcon from '@mui/icons-material/Add';
import IconButton from '@mui/material/IconButton';
import TextField from '@mui/material/TextField';
import RemoveIcon from '@mui/icons-material/Remove';
import Button from '@mui/material/Button';
import "./product-detail.css"
import Demo from './tab/tab-panel'
import { AuthContextInterface, AuthorResponse, CartAddRequest, CategoryResponse, FormatProductResponse, ProductResponse, ReviewResponse } from '../../types/type';
import productService from '../../service/productService';
import ErrorNotFound from '../../component/error/error';
import { ProductDetailContext, productDetailReducer, ProductDetailState } from '../../context/productDetailContext';
import { makeStyles } from '@material-ui/core';
import cartService from '../../service/cartService';
import { AuthContext } from '../../context/authContext';
const useStyles = makeStyles({
    input: {
        '& input[type=number]': {
            '-moz-appearance': 'textfield'
        },
        '& input[type=number]::-webkit-outer-spin-button': {
            '-webkit-appearance': 'none',
            margin: 0
        },
        '& input[type=number]::-webkit-inner-spin-button': {
            '-webkit-appearance': 'none',
            margin: 0
        }
    },
});
const initState: ProductDetailState = {
    product: null,
    loadingProduct: false,
    loadingCategory: false,
    loadingformat: false,
    loadingAuthor: false,
    loadingAdd: false,
    loading: false,
    category: [],
    format: [],
    author: null,
    review: []
}
interface Context {
    dispatch?: any,
    user?: AuthContextInterface
}
const ProductDetail: React.FC = () => {
    const params = useParams<{ id: string }>();
    const [detailState, dispatchDetail] = useReducer(productDetailReducer, initState);
    const { user, dispatch }: Context = useContext(AuthContext);
    const [formatId, setformatId] = useState<string>('');
    const [quantity, setQuantity] = useState<number>(0);
    const [isError, setIsError] = useState<boolean>(false);
    const [valueInput, setValueInput] = useState<number>(1);
    const navigation = useNavigate();
    // const []
    const classes = useStyles();
    const handleChange = (event: SelectChangeEvent) => {
        setformatId(event.target.value as string);
        setIsError(false);
        // var quantity = (event.target as Element).id;
        // setQuantity(+quantity);
        // console.log(quantity);
    };
    const handleClickOption = (event: any) => {
        var quantity = +(event.target as Element).id;
        setQuantity(quantity);
        console.log(quantity);
        if (quantity < valueInput)
            setValueInput(quantity);
    }
    useEffect(() => {
        const id = params.id !== undefined ? params.id : '';
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");
        productService.loadProductById(id).then(
            (res) => {
                console.log(res.data.categoryRespones);
                dispatchDetail({
                    type: 'set-product',
                    payload: res.data
                });
                dispatchDetail({
                    type: 'set-category',
                    payload: res.data.categoryRespones
                });
                dispatchDetail({
                    type: 'set-format',
                    payload: res.data.formatRespones
                });
                dispatchDetail({
                    type: 'set-author',
                    payload: res.data.authorResponse
                });
                dispatchDetail({
                    type: 'set-review',
                    payload: res.data.reviewRespones
                });
                dispatchDetail({
                    type: 'set-review',
                    payload: res.data.reviewRespones
                });
                dispatchDetail({
                    type: 'set-loadingAdd',
                    payload: false
                });
            }
        ).catch(
            (err) => {
                alert(err);
            }
        )
    }, [])
    useEffect(() => {
        console.log(detailState);
        if (detailState.loadingAdd) {
            productService.loadProductById(detailState.product.id).then(
                (res) => {
                    dispatchDetail({
                        type: 'set-review',
                        payload: res.data.reviewRespones
                    });
                    dispatchDetail({
                        type: 'set-product',
                        payload: res.data
                    });
                }
            )
            dispatchDetail({
                type: 'set-loading',
                payload: true
            });
        }
    }, [detailState.loadingAdd])
    useEffect(() => {
        dispatchDetail({
            type: 'set-loadingAdd',
            payload: false
        });
    }, [detailState.loading])
    const handleAddCart = () => {
        if (formatId === '')
            setIsError(true);
        else {
            const token = localStorage.getItem('token');
            const cartRequest: CartAddRequest = {
                productId: detailState.product.id,
                formatId: +formatId,
                quantity: valueInput

            }
            console.log(cartRequest);
            if (token)
                cartService.addCart(cartRequest, token).then(
                    (res) => {
                        alert(res.data);
                        if (user?.countItemCart) {
                            cartService.getCountItemCart(token).then(
                                (res) => {
                                    dispatch({
                                        type: 'set-Count',
                                        payload: res.data
                                    });
                                }
                            ).catch(
                                (err) => {
                                    alert(err.response.data.message);
                                }
                            )
                        }
                        console.log(user?.countItemCart);
                    }
                ).catch(
                    (err) => {
                        alert(err);
                    }
                )
            else
                navigation("/login");
        }
    }
    const handlePlusQuantity = () => {
        var quantityPlus = valueInput + 1;
        console.log(quantity);
        if (quantity != 0) {
            if (quantityPlus > quantity) {
                setValueInput(quantity);
            }
            else {
                setValueInput(quantityPlus);
            }
        } else {
            setValueInput(1);
        }
    }
    const handleMinusQuantity = () => {
        var quantityPlus = valueInput - 1;
        console.log(quantity);
        if (quantityPlus == 0)
            setValueInput(1);
        else setValueInput(quantityPlus);

    }
    const handleInput = (e: React.ChangeEvent<HTMLInputElement>) => {
        const newValue = +e.target.value;
        if (quantity != 0) {
            if (newValue > quantity) {
                setValueInput(quantity);
            }
            else {
                if (newValue == 0) setValueInput(1);
                else setValueInput(newValue);
            }
        } else {
            setValueInput(1);
        }

    };
    return (
        <>
            <div style={{ borderBottom: "1px solid #eae8e4" }}>
                <Container>
                    <div className='pt-4 pb-4'>
                        <a href="/" className='me-1'>Home</a>
                        <i className="bi bi-chevron-right me-1" style={{ fontSize: "10px" }}></i>
                        Shop
                    </div>
                </Container>
            </div>
            {detailState.loadingProduct ?
                <div>
                    <div >
                        <Container style={{ borderBottom: "1px solid #eae8e4" }}>
                            <Row>
                                <Col xs="9">
                                    <div className='pt-5 pb-5'>
                                        <Row className='product-detail-body'>
                                            <Col xs="5">
                                                <img src={detailState.product.imgUrl}
                                                    alt="" />
                                            </Col>
                                            <Col xs="7" className='pe-5'>
                                                <div className='pt-2 ps-4'>
                                                    <h2>{detailState.product.productName}</h2>
                                                </div>
                                                <div className='mt-3 ps-4'>
                                                    By (author) {`${detailState.author?.firstName} ${detailState.author?.lastName}`}
                                                </div>
                                                <div className=' ps-4 mt-3'>
                                                    <h4> {detailState.product.maxPrice === detailState.product.minPrice ? `$${detailState.product.maxPrice}`
                                                        : `$${detailState.product.minPrice} - $${detailState.product.maxPrice}`}</h4>
                                                </div>
                                                <div className='mt-3 ps-4'>
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat.
                                                </div>
                                                <div className='mt-4 ps-4'>
                                                    <p>Book Format</p>
                                                    <div>
                                                        <FormControl fullWidth>
                                                            <InputLabel id="demo-simple-select-label">Option</InputLabel>
                                                            <Select
                                                                labelId="demo-simple-select-label"
                                                                id="demo-simple-select"
                                                                value={formatId}
                                                                label="Option"
                                                                onChange={handleChange}
                                                                className={classes.input}
                                                            >
                                                                {
                                                                    detailState.format.map(
                                                                        (item: FormatProductResponse) => {
                                                                            return (
                                                                                <MenuItem onClick={handleClickOption} key={item.id} value={item.id} id={`${item.quantity}`}>{`${item.formatName}  $${item.price}`}</MenuItem>
                                                                            );
                                                                        }
                                                                    )
                                                                }
                                                            </Select>
                                                        </FormControl>
                                                    </div>
                                                </div>
                                                <div className='ms-4'>
                                                    {isError ? <Alert severity="error" className='mt-3 ps-4'>Option cannot null</Alert> : ''}
                                                </div>
                                                <div className='mt-3 ps-4' style={{ display: 'flex' }}>
                                                    <TextField
                                                        id="standard-name"
                                                        value={valueInput}
                                                        InputProps={{
                                                            startAdornment:
                                                                <IconButton sx={{ borderRadius: 0 }} edge="start" onClick={handleMinusQuantity}>
                                                                    <RemoveIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                                </IconButton>,
                                                            endAdornment:
                                                                <IconButton sx={{ borderRadius: 0 }} edge="end" onClick={handlePlusQuantity}>
                                                                    <AddIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                                </IconButton>,
                                                        }}
                                                        inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }}
                                                        type='number'
                                                        className={classes.input}
                                                        style={{ width: "9.5rem" }}
                                                        onChange={handleInput}
                                                    />
                                                    <Button variant="contained" style={{ width: "100%", backgroundColor: "gray" }} className='ms-4' onClick={handleAddCart}>
                                                        Add To Cart
                                                    </Button>


                                                </div>
                                            </Col>
                                        </Row>
                                    </div>

                                </Col>
                                <Col xs="3" >
                                    <div className='pt-4 pe-3 ps-1 mt-5 widget-product'>
                                        <ul className='product-list-widget mt-2'>
                                            <li className='mb-4'>
                                                <div style={{ display: "flex" }}>
                                                    <img src="https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg" alt="" />
                                                    <div className='ms-4'>
                                                        <h6 className='single-product-widget font-weight-normal'>
                                                            Until the End of Time: Mind, Matter, and Our Search for Meaning in an Evolving Universe
                                                        </h6>
                                                        <div className='price-bold'>
                                                            $25.5
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li className='mb-4'>
                                                <div style={{ display: "flex" }}>
                                                    <img src="https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg" alt="" />
                                                    <div className='ms-4'>
                                                        <h6 className='single-product-widget font-weight-normal'>
                                                            Until the End of Time: Mind, Matter, and Our Search for Meaning in an Evolving Universe
                                                        </h6>
                                                        <div className='price-bold'>
                                                            $25.5
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li className='mb-5'>
                                                <div style={{ display: "flex" }}>
                                                    <img src="https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg" alt="" />
                                                    <div className='ms-4'>
                                                        <h6 className='single-product-widget font-weight-normal'>
                                                            Until the End of Time: Mind, Matter, and Our Search for Meaning in an Evolving Universe
                                                        </h6>
                                                        <div className='price-bold'>
                                                            $25.5
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </Col>
                            </Row>
                        </Container>
                        <Container className='mt-2'>
                            <ProductDetailContext.Provider value={{ detailState, dispatchDetail }} >
                                <Demo />
                            </ProductDetailContext.Provider>
                        </Container>

                    </div>
                    <Container className='mt-5'>
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
                                },
                                1480: {
                                    slidesPerView: 5
                                }
                            }
                            }
                        >
                            <Row>
                                <SwiperSlide >
                                    <Col lg={10} md={10} sm={8} xs={5} className="item">
                                        <div className='item-info' style={{ padding: "5%" }}>
                                            <div style={{ paddingTop: "15%", paddingLeft: "20%", paddingRight: "20%" }}>
                                                <img style={{ width: "100%" }} src="https://bookworm.madrasthemes.com/wp-content/uploads/2020/08/48-150x200.jpg"></img>
                                            </div>

                                            <div className="product-loop-info">
                                                <div className=' h6 text-lh-md product-mb-2 text-height-2 crop-text-2 name-height'>
                                                    <a href='#'>1</a>
                                                </div>
                                                <div className='name-author-product'>
                                                    Jessica Simson
                                                </div>
                                                <div className='product-price'>
                                                    $200
                                                </div>
                                            </div>
                                            <div className='product-hover'>
                                                <div style={{ marginRight: "30%" }} className="pointer add-to-cart-text">
                                                    <a href=''>Add to cart</a>
                                                </div>
                                                <div className="wish-list-icon">
                                                    <i className="bi bi-heart icon pointer"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </Col>

                                </SwiperSlide>
                                <SwiperSlide >
                                    <Col lg={10} md={10} sm={8} xs={5} className="item">
                                        <div className='item-info' style={{ padding: "5%" }}>
                                            <div style={{ paddingTop: "15%", paddingLeft: "20%", paddingRight: "20%" }}>
                                                <img style={{ width: "100%" }} src="https://bookworm.madrasthemes.com/wp-content/uploads/2020/08/48-150x200.jpg"></img>
                                            </div>

                                            <div className="product-loop-info">
                                                <div className=' h6 text-lh-md product-mb-2 text-height-2 crop-text-2 name-height'>
                                                    <a href='#'>1</a>
                                                </div>
                                                <div className='name-author-product'>
                                                    sd
                                                </div>
                                                <div className='product-price'>
                                                    $s200
                                                </div>
                                            </div>
                                            <div className='product-hover'>
                                                <div style={{ marginRight: "30%" }} className="pointer add-to-cart-text">
                                                    <a href=''>Add to cart</a>
                                                </div>
                                                <div className="wish-list-icon">
                                                    <i className="bi bi-heart icon pointer"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </Col>

                                </SwiperSlide>

                            </Row>
                        </Swiper>
                    </Container>
                </div>
                : <ErrorNotFound />
            }
        </>
    )
}
export default ProductDetail;