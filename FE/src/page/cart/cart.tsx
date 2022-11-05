import { useEffect, useState, useReducer, useContext } from "react";
import { Container, Row, Col } from 'react-bootstrap';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails'
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import TextField from '@mui/material/TextField';
import IconButton from '@mui/material/IconButton';
import Button from '@mui/material/Button';
import './cart.css'
import ClearIcon from '@mui/icons-material/Clear';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import TablePagination from "@mui/material/TablePagination";
import { makeStyles } from "@material-ui/core";
import { AuthContextInterface, CartItemResponse, CartResponse, CartUpdateRequest, updateQuantityCart } from "../../types/type";
import cartService from "../../service/cartService";
import { AuthContext } from "../../context/authContext";
import orderService from "../../service/orderService";
import { useNavigate } from "react-router-dom";
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
export interface CartState {
    cart: CartResponse | null,
    cartItem: Array<CartItemResponse>,
    isCartEmpty: boolean,
    isLoading: boolean,
    quantityUpdate: updateQuantityCart
}
const initialState: CartState = {
    cart: null,
    cartItem: [],
    isCartEmpty: true,
    isLoading: false,
    quantityUpdate: {
        productId: 0,
        formatId: 0,
        quantity: 0
    }
}
const todoReducer = (state: any = initialState, action: any) => {
    switch (action.type) {
        case "set-cart":
            return {
                ...state,
                cart: action.payload,
                isLoading: true
            };
        case "set-cartItem":
            return {
                ...state,
                cartItem: action.payload,
            };
        case "set-empty":
            return {
                ...state,
                isCartEmpty: action.payload,
            };
        case "set-loading":
            return {
                ...state,
                isLoading: action.payload,
            };
        case "set-update-loading":
            return {
                ...state,
                quantityUpdate: action.payload,
            };
    }
}
interface userContext {
    dispatchUser?: any,
    user?: AuthContextInterface
}

const Cart: React.FC = () => {
    const classes = useStyles();
    const { user, dispatchUser }: userContext = useContext(AuthContext);
    const [state, dispatch] = useReducer(todoReducer, initialState);
    const [expanded, setExpanded] = useState('panel1');
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(3);
    const navigation = useNavigate();
    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };
    const handleChange =
        (panel: string) => (event: React.SyntheticEvent, isExpanded: boolean) => {
            setExpanded(isExpanded ? panel : 'panel1');
        };
    const handleCheckout = () => {
        const token = localStorage.getItem('token');
        if (token) {
            orderService.addOrder(token).then(
                (res) => {
                    alert(res.data);
                    dispatch({
                        type: "set-update-loading",
                        payload: -1
                    });
                }
            ).catch(
                (err) => {
                    alert(err);
                }
            )
        }
        else {
            navigation('/login')
        }
    }
    const handlePlusQuantity = (quantity: number, formatId: number, productId: number) => {
        var quantityPlus = quantity + 1;
        const cartItemRequest: CartUpdateRequest = {
            formatId: formatId,
            productId: productId,
            quantity: quantityPlus,
            cartId: state.cart.id
        }
        console.log(cartItemRequest)
        const token = localStorage.getItem('token');
        if (token) {
            cartService.updateCart(cartItemRequest, token).then(
                (res) => {
                    const updateCart: updateQuantityCart = {
                        formatId: formatId,
                        productId: productId,
                        quantity: quantity
                    }
                    dispatch({
                        type: "set-update-loading",
                        payload: updateCart
                    });
                }
            ).catch(
                (err) => {
                    alert(err);
                }
            )
        }

    }
    const handleMinusQuantity = (quantity: number, formatId: number, productId: number) => {
        var quantityMinus = quantity - 1;
        console.log(quantity);
        const cartItemRequest: CartUpdateRequest = {
            formatId: formatId,
            productId: productId,
            quantity: quantityMinus,
            cartId: state.cart.id
        }
        const token = localStorage.getItem('token');
        if (token) {
            cartService.updateCart(cartItemRequest, token).then(
                (res) => {
                    const updateCart: updateQuantityCart = {
                        formatId: formatId,
                        productId: productId,
                        quantity: quantity
                    }
                    dispatch({
                        type: "set-update-loading",
                        payload: updateCart
                    });
                }
            ).catch(
                (err) => {
                    console.log(err);
                }
            )
        }

    }
    const handleInput = (quantity: number, formatId: number, productId: number) => {
        const newValue = quantity;
        const token = localStorage.getItem('token');
        const cartItemRequest: CartUpdateRequest = {
            formatId: formatId,
            productId: productId,
            quantity: newValue,
            cartId: state.cart.id
        }
        if (token) {
            cartService.updateCart(cartItemRequest, token).then(
                (res) => {
                    const updateCart: updateQuantityCart = {
                        formatId: formatId,
                        productId: productId,
                        quantity: quantity
                    }
                    dispatch({
                        type: "set-update-loading",
                        payload: updateCart
                    });

                }
            ).catch(
                (err) => {
                    alert(err);
                }
            )
        }

    };
    useEffect(() => {
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");
        const token = localStorage.getItem('token');
        if (token) {
            cartService.getCart(token).then(
                (res) => {
                    dispatch({
                        type: "set-cart",
                        payload: res.data
                    });
                    dispatch({
                        type: "set-cartItem",
                        payload: res.data.itemRespones
                    });
                    if (res.data.itemRespones) {
                        dispatch({
                            type: "set-empty",
                            payload: false
                        });
                    }
                    console.log(state);
                }
            ).catch(
                (err) => {
                    console.log(err)
                }
            )
        }
        else {
            dispatch({
                type: "set-empty",
                payload: true
            });
        }

    }, [])
    useEffect(() => {
        console.log(state)
        const token = localStorage.getItem('token');
        if (token) {
            cartService.getCart(token).then(
                (res) => {
                    dispatch({
                        type: "set-cart",
                        payload: res.data
                    });
                    dispatch({
                        type: "set-cartItem",
                        payload: res.data.itemRespones
                    });
                    if (res.data.itemRespones.length > 0) {
                        dispatch({
                            type: "set-empty",
                            payload: false
                        });
                    }
                    console.log(state);
                }
            ).catch(
                (err) => {
                    console.log(err)
                }
            )
        }
        else {
            dispatch({
                type: "set-empty",
                payload: true
            });
        }
    }, [user])
    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            cartService.getCart(token).then(
                (res) => {
                    dispatch({
                        type: "set-cart",
                        payload: res.data
                    });
                    dispatch({
                        type: "set-cartItem",
                        payload: res.data.itemRespones
                    });
                    if (res.data.itemRespones) {
                        dispatch({
                            type: "set-empty",
                            payload: false
                        });
                    }
                    console.log(state);
                }

            ).catch(
                (err) => {
                    console.log(err)
                }
            )
        }
        else {
            dispatch({
                type: "set-empty",
                payload: true
            });
        }

    }, [state.quantityUpdate])
    return (<>
        <div style={{ borderBottom: "1px solid #eae8e4" }} >
            <Container>
                <div className='pt-4 pb-4'>
                    <a href="/" className='me-1'>Home</a>
                    <i className="bi bi-chevron-right me-1" style={{ fontSize: "10px" }}></i>
                    Shop
                </div>
            </Container>
        </div>
        <div style={{ width: '100%', height: "auto", backgroundColor: '#fff6f6' }} className='pt-5 pb-5'>
            <Container style={{ backgroundColor: 'white' }}>
                <div className="text-center pt-5">
                    {state.isLoading && state.isCartEmpty == false ? <h2>Your cart: {state.cartItem.length} item</h2> : <h2>Your cart: 0 item</h2>}
                </div>
                <Row className="mt-5">
                    <Col xs={8} className='me-4 ms-5' style={{ width: '100%', height: "auto" }}>

                        {state.isLoading && state.isCartEmpty == false ? <div>
                            {

                                <>
                                    <TableContainer component={Paper} sx={{ maxHeight: "40rem" }}>
                                        <Table sx={{ width: '100%' }} aria-label="customized table">
                                            <TableHead>
                                                <TableRow>
                                                    <TableCell>Product</TableCell>
                                                    <TableCell align="left">Format</TableCell>
                                                    <TableCell align="left">Price</TableCell>
                                                    <TableCell align="left">Quantity</TableCell>
                                                    <TableCell align="left">Total</TableCell>
                                                    <TableCell align="left"></TableCell>
                                                </TableRow>
                                            </TableHead>
                                            <TableBody >
                                                {state.cartItem
                                                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                                    .map((item: CartItemResponse) => (
                                                        <TableRow key={item.productId + '' + item.formatId} className='pt-3 pb-5' style={{ height: '13rem' }}>
                                                            <TableCell component="th" scope="row">
                                                                <div className='d-flex align-item-center'>
                                                                    <img style={{ maxWidth: '30%' }} src={item.imgUrl} alt="" />
                                                                    <div className='ms-3'>
                                                                        Book 1
                                                                    </div>
                                                                </div>
                                                            </TableCell>
                                                            <TableCell align="left">{item.formatName}</TableCell>
                                                            <TableCell align="left">{item.firstPrice}</TableCell>
                                                            <TableCell align="left">
                                                                <TextField
                                                                    id="standard-name"
                                                                    value={item.quantity}
                                                                    InputProps={{
                                                                        startAdornment:
                                                                            <IconButton sx={{ borderRadius: 0 }} edge="start" onClick={() =>
                                                                                handleMinusQuantity(item.quantity, item.formatId, item.productId)} >
                                                                                <RemoveIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                                            </IconButton>,
                                                                        endAdornment:
                                                                            <IconButton sx={{ borderRadius: 0 }} edge="end" onClick={() =>
                                                                                handlePlusQuantity(item.quantity, item.formatId, item.productId)} >
                                                                                <AddIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                                            </IconButton>,
                                                                    }}
                                                                    type='number'
                                                                    className={classes.input}
                                                                    style={{ width: "6.5rem" }}
                                                                    onChange={() =>
                                                                        handleInput(item.quantity, item.formatId, item.productId)
                                                                    }
                                                                />
                                                            </TableCell>
                                                            <TableCell align="left">{item.finalPrice}</TableCell>
                                                            <TableCell align="left">
                                                                <div>
                                                                    <IconButton sx={{ "&:hover": { backgroundColor: "white", color: 'red' } }}>
                                                                        <ClearIcon sx={{ fontSize: '28px' }} />
                                                                    </IconButton>
                                                                </div>
                                                            </TableCell>
                                                        </TableRow>
                                                    ))}
                                            </TableBody>
                                        </Table>
                                    </TableContainer>
                                    <TablePagination
                                        rowsPerPageOptions={[3, 5, 7]}
                                        component="div"
                                        count={state.cartItem.length}
                                        rowsPerPage={rowsPerPage}
                                        page={page}
                                        onPageChange={handleChangePage}
                                        onRowsPerPageChange={handleChangeRowsPerPage}
                                    />
                                </>

                            }
                        </div>
                            : <h4>Nothing in Cart</h4>
                        }
                    </Col>
                    <Col xs={3} style={{ width: '100%', height: "auto" }}>
                        <div >
                            <Accordion expanded={expanded === 'panel1'} onChange={handleChange('panel1')} disableGutters={true} defaultExpanded={true}>
                                <AccordionSummary
                                    expandIcon={expanded === 'panel1' ? <RemoveIcon /> : <AddIcon />}
                                    aria-controls="panel1a-content"
                                    id="panel1a-header"
                                >
                                    <h5 className='px-3 pt-2 pb-2'>
                                        <div>Cart Totals</div>
                                    </h5>
                                </AccordionSummary>
                                <AccordionDetails>
                                    <div className='px-3  d-flex' style={{ justifyContent: 'space-between' }}>
                                        <div>SubTotal</div>
                                        {state.isLoading && state.isCartEmpty == false ? <div>{`$${state.cart.firstPrice}`}</div> : <div>$0</div>}
                                    </div>
                                </AccordionDetails>
                                <AccordionDetails>
                                    <div className='px-3  d-flex' style={{ justifyContent: 'space-between' }}>
                                        <div>Quantity</div>
                                        {state.isLoading && state.isCartEmpty == false ? <div>{state.cart.quantity}</div> : <div>0</div>}
                                    </div>
                                </AccordionDetails>
                                {/* <AccordionDetails>
                                    <div className='px-3  d-flex' style={{ justifyContent: 'space-between' }}>
                                        <div>Discount</div>
                                        <div className="d-flex">
                                            <div className="amount me-2">
                                                $29
                                            </div>
                                            <div>
                                                $27
                                            </div>
                                        </div>
                                    </div>
                                </AccordionDetails> */}

                            </Accordion >
                            <Accordion expanded={expanded === 'panel2'} onChange={handleChange('panel2')} disableGutters={true}>
                                <AccordionSummary
                                    expandIcon={expanded === 'panel2' ? <RemoveIcon /> : <AddIcon />}
                                    aria-controls="panel2a-content"
                                    id="panel2a-header"
                                >
                                    <h5 className='px-3 pt-2 pb-2'>
                                        <div>Coupon</div>
                                    </h5>
                                </AccordionSummary>
                                <AccordionDetails >
                                    {/* <TextField
                                        id="standard-name"
                                        defaultValue={1}
                                        InputProps={{
                                            endAdornment:
                                                <IconButton sx={{ borderRadius: 0, fontSize: '15px' }} edge="end">
                                                    Apply Coupon
                                                </IconButton>
                                        }}

                                        style={{ width: "100%" }}
                                        className=" pb-2 px-3"
                                    /> */}
                                    <div className="p-4" style={{ textAlign: "center" }}><h5>Coming soon</h5></div>
                                </AccordionDetails>
                            </Accordion>
                            <div style={{ backgroundColor: 'white', justifyContent: 'space-between' }} className='pt-4 pb-3 d-flex ps-2' >
                                <div className="ps-4" style={{ fontSize: '19px' }}>
                                    Total
                                </div>
                                <div className="pe-4" style={{ fontSize: '19px' }}>
                                    {state.isLoading && state.isCartEmpty == false ? <div>{`$${state.cart.finalPrice}`}</div> : <div>$0</div>}
                                </div>
                            </div>
                        </div>
                        <div className="mt-4">
                            <Button variant="contained" className='border-btn-black btn-bg-white btn-proceed pt-3 pb-3' onClick={handleCheckout}>
                                Proceed to checkout
                            </Button>
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    </>
    )
}
export default Cart;