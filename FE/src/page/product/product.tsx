import Row from 'react-bootstrap/Row';
import { useEffect, useState, useReducer } from "react";
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import "./product.css"
import Select, { SelectChangeEvent } from '@mui/material/Select';
import Slider from '@mui/material/Slider';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Pagination from '@mui/material/Pagination';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import RemoveIcon from '@mui/icons-material/Remove';
import Typography from '@mui/material/Typography';
import AddIcon from '@mui/icons-material/Add';
import { CategoryResponse, FormatResponse, listCategory, Filter, ProductResponse, AuthorResponse } from '../../types/type';
import productService from '../../service/productService';
import categoryService from '../../service/categoryService';
import formatService from '../../service/formatService';
import authorService from '../../service/authorService';
import { type } from 'os';
export interface ProductState {
    product: Array<ProductResponse>,
    pagesCount: number,
    totalElement: number,
    loadingState: boolean,
    filter: Filter,
    category: Array<CategoryResponse>,
    format: Array<FormatResponse>
}

const initState: ProductState = {
    product: [],
    pagesCount: 0,
    totalElement: 0,
    loadingState: false,
    category: [],
    filter: {
        page: 0,
        size: 1,
        categoryId: 0,
        authorId: 0,
        formatId: 0,
        finalPrice: 0,
        firstPrice: 100
    },
    format: []
}
const todoReducer = (state: any = initState, action: any) => {
    switch (action.type) {
        case 'get-category':
            return {
                ...state,
                category: action.payload,
            };
        case 'get-format':
            return {
                ...state,
                format: action.payload,
            };
        case 'get-author':
            return {
                ...state,
                author: action.payload,
            };
        case 'get-product':
            return {
                ...state,
                product: action.payload,
                loadingState: true,
                pagesCount: action.payload[0].pageResponse.totalPage,
                totalElement: action.payload[0].pageResponse.totalElement,
            };
        case "change-pagging":
            return {
                ...state,
                filter: {
                    page: action.payload,
                    size: 1
                }
            };
        case "change-filter-category":
            return {
                ...state,
                filter: {
                    ...state.filter,
                    categoryId: action.payload
                }
            }
        default:
            throw new Error();
    }
}
const Product: React.FC = () => {
    const [expanded, setExpanded] = useState<string | false>(false);
    const [state, dispatch] = useReducer(todoReducer, initState);
    const [price, setPrice] = useState<number[]>([20, 37]);

    const handleChangePrice = (event: Event, newValue: number | number[]) => {
        setPrice(newValue as number[]);
    };

    const handleChange =
        (panel: string) => (event: React.SyntheticEvent, isExpanded: boolean) => {
            setExpanded(isExpanded ? panel : false);
        };

    useEffect(() => {
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");

        productService.loadData(state.filter).then(
            (response) => {
                dispatch({
                    type: "get-product",
                    payload: response.data
                });

            }
        );
        categoryService.getActiveAllCategory().then(
            (response) => {
                dispatch({
                    type: "get-category",
                    payload: response.data
                })
            });
        formatService.getActiveAllFormat().then(
            (response) => {
                dispatch({
                    type: "get-format",
                    payload: response.data
                })
            }
        )
        authorService.getActiveAllAuthor().then(
            (response) => {
                dispatch({
                    type: "get-author",
                    payload: response.data
                })
            }
        )

    }, [state.filter])
    useEffect(() => {
        console.log(state);
    }, [state])

    const pageChangeHandler = (event: React.ChangeEvent<unknown>, page: number) => {
        // Your code 
        dispatch({
            type: "change-pagging",
            payload: page - 1
        });

    }
    const handleChangeCategoryId = (event: React.MouseEvent<HTMLElement>) => {
        console.log((event.target as Element).id);
        dispatch({
            type: "change-filter-category",
            payload: +(event.target as Element).id
        });
    }
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
            {state.loadingState &&
                <Container className='mt-5'>
                    <Row>
                        <Col lg={3} md={4} sm={12}>
                            <div>
                                <Accordion expanded={expanded === 'panel1'} onChange={handleChange('panel1')}>
                                    <AccordionSummary
                                        expandIcon={expanded === 'panel1' ? <RemoveIcon /> : <AddIcon />}
                                        aria-controls="panel1a-content"
                                        id="panel1a-header"
                                    >
                                        <h5 className='px-3 pt-2 pb-2'>
                                            <div>Category</div>
                                        </h5>
                                    </AccordionSummary>
                                    {
                                        state.category.map(
                                            (item: CategoryResponse) => {
                                                return (
                                                    <AccordionDetails >
                                                        <div className='px-3'>
                                                            <div style={{ cursor: "pointer" }} onClick={handleChangeCategoryId} id={`${item.id}`}>{item.name}</div>
                                                        </div>
                                                    </AccordionDetails>
                                                )
                                            }
                                        )
                                    }
                                </Accordion>
                                <Accordion expanded={expanded === 'panel2'} onChange={handleChange('panel2')}>
                                    <AccordionSummary
                                        expandIcon={expanded === 'panel2' ? <RemoveIcon /> : <AddIcon />}
                                        aria-controls="panel2a-content"
                                        id="panel2a-header"
                                    >
                                        <h5 className='px-3 pt-2 pb-2'>
                                            <div>Author</div>
                                        </h5>
                                    </AccordionSummary>
                                    {
                                        state.author.map(
                                            (item: AuthorResponse) => {
                                                return (
                                                    <AccordionDetails >
                                                        <div className='px-3'>
                                                            <div onClick={handleChangeCategoryId} id={`${item.id}`}>{item.firstName + " " + item.lastName}</div>
                                                        </div>
                                                    </AccordionDetails>
                                                )
                                            }
                                        )
                                    }
                                </Accordion>
                                <Accordion expanded={expanded === 'panel3'} onChange={handleChange('panel3')}>
                                    <AccordionSummary
                                        expandIcon={expanded === 'panel3' ? <RemoveIcon /> : <AddIcon />}
                                        aria-controls="panel2a-content"
                                        id="panel2a-header"
                                    >
                                        <h5 className='px-3 pt-2 pb-2'>
                                            <div>Format</div>
                                        </h5>
                                    </AccordionSummary>
                                    {
                                        state.format.map(
                                            (item: FormatResponse) => {
                                                return (
                                                    <AccordionDetails >
                                                        <div className='px-3'>
                                                            <div>{item.formatName}</div>
                                                        </div>
                                                    </AccordionDetails>
                                                )
                                            }
                                        )
                                    }
                                </Accordion>
                                <Slider
                                    getAriaLabel={() => 'Temperature range'}
                                    value={price}
                                    onChange={handleChangePrice}
                                    valueLabelDisplay="auto"
                                // getAriaValueText={valuetext}
                                />
                            </div>
                        </Col>
                        <Col lg={9} md={8} sm={10}>
                            <Row>
                                <div className=' pb-4' style={{ fontSize: "18px", display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                                    <div>{`Showing 1–${state.pagesCount} of ${state.totalElement} results`}</div>
                                    <div style={{ width: "40%" }}>
                                        <FormControl variant="standard" sx={{ m: 1, minWidth: 200 }}>
                                            <InputLabel id="demo-simple-select-standard-label">Default sorting</InputLabel>
                                            <Select
                                                labelId="demo-simple-select-standard-label"
                                                id="demo-simple-select-standard"

                                                label="Default sorting"
                                            >
                                                <MenuItem value="">
                                                    <em>None</em>
                                                </MenuItem>
                                                <MenuItem value={10}>Ten</MenuItem>
                                                <MenuItem value={20}>Twenty</MenuItem>
                                                <MenuItem value={30}>Thirty</MenuItem>
                                            </Select>
                                        </FormControl>
                                        <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
                                            <InputLabel id="demo-simple-select-standard-label">Show</InputLabel>
                                            <Select
                                                labelId="demo-simple-select-standard-label"
                                                id="demo-simple-select-standard"

                                                label="Show item"
                                            >
                                                <MenuItem value="">
                                                    <em>None</em>
                                                </MenuItem>
                                                <MenuItem value={10}>Ten</MenuItem>
                                                <MenuItem value={20}>Twenty</MenuItem>
                                                <MenuItem value={30}>Thirty</MenuItem>
                                            </Select>
                                        </FormControl>
                                    </div>
                                </div>

                            </Row>
                            <Row>
                                {
                                    state.product.map(
                                        (item: ProductResponse) => {
                                            return (
                                                <>
                                                    <Col key={item.id} lg={3} md={5} sm={6} xs={6} className="item">
                                                        <div className='item-info' style={{ padding: "5%" }}>
                                                            <div style={{ paddingTop: "15%", paddingLeft: "20%", paddingRight: "20%" }}>
                                                                <img style={{ width: "100%" }} src={item.imgUrl}></img>
                                                            </div>

                                                            <div className="product-loop-info">
                                                                <div className=' h6 text-lh-md product-mb-2 text-height-2 crop-text-2 '>
                                                                    <a href='#'>{item.productName}</a>
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
                                                                    <i className="bi bi-heart icon pointer"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </Col>

                                                </>
                                            );
                                        }
                                    )
                                }
                            </Row>
                            <div className='mt-5 mb-5 '>
                                <div style={{ width: "52%", marginLeft: "30%" }}>
                                    <Pagination count={state.pagesCount} onChange={pageChangeHandler} page={state.filter.page + 1} size="large" />
                                </div>
                            </div>
                        </Col>
                    </Row>
                </Container >
            }
        </>)

}
export default Product;