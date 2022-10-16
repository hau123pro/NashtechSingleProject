
import { Container, Row, Col } from 'react-bootstrap';
import { useEffect, useState } from "react";
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
const ProductDetail: React.FC = () => {
    useEffect(() => {
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");
    }, [])
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
            <div style={{ borderBottom: "1px solid #eae8e4" }}>
                <Container style={{ borderBottom: "1px solid #eae8e4" }}>
                    <Row>
                        <Col xs="9">
                            <div className='pt-5 pb-5'>
                                <Row className='product-detail-body'>
                                    <Col xs="5">
                                        <img src="https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/50-300x444.jpg"
                                            alt="" />
                                    </Col>
                                    <Col xs="7" className='pe-5'>
                                        <div className='pt-2 ps-4'>
                                            <h2>All You Can Ever Know: A Memoir</h2>
                                        </div>
                                        <div className='mt-3 ps-4'>
                                            By (author) Anna Banks
                                        </div>
                                        <div className=' ps-4 mt-3'>
                                            <h4>$29.95 – $59.95</h4>
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
                                                        // value={age}
                                                        label="Option"
                                                    // onChange={handleChange}
                                                    >
                                                        <MenuItem value={10}>Ten</MenuItem>
                                                        <MenuItem value={20}>Twenty</MenuItem>
                                                        <MenuItem value={30}>Thirty</MenuItem>
                                                    </Select>
                                                </FormControl>
                                            </div>
                                        </div>
                                        <div className='mt-3 ps-4' style={{ display: 'flex' }}>
                                            <TextField
                                                id="standard-name"
                                                defaultValue={1}
                                                InputProps={{
                                                    startAdornment:
                                                        <IconButton sx={{ borderRadius: 0 }} edge="start">
                                                            <RemoveIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                        </IconButton>,
                                                    endAdornment:
                                                        <IconButton sx={{ borderRadius: 0 }} edge="end">
                                                            <AddIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                        </IconButton>
                                                }}

                                                style={{ width: "9.5rem" }}
                                            />

                                            <Button variant="contained" style={{ width: "100%", backgroundColor: "gray" }} className='ms-4'>
                                                Add To Cart
                                            </Button>


                                        </div>
                                    </Col>
                                </Row>
                            </div>

                        </Col>
                        <Col xs="3" >
                            <div className='pt-4 pe-3 ps-1 mt-5 widget-product'>
                                {/* <StyledEngineProvider injectFirst>
                                    <Demo />
                                </StyledEngineProvider> */}
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
                    <Demo />
                </Container>
            </div>
        </>
    )
}
export default ProductDetail;