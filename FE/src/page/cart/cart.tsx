import { useEffect, useState } from "react";
import { Container, Row, Col } from 'react-bootstrap';
import Table from './table_cart/table'
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails'
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import TextField from '@mui/material/TextField';
import IconButton from '@mui/material/IconButton';
import Button from '@mui/material/Button';
import './cart.css'
const Cart: React.FC = () => {
    const [expanded, setExpanded] = useState('panel1');

    const handleChange =
        (panel: string) => (event: React.SyntheticEvent, isExpanded: boolean) => {
            setExpanded(isExpanded ? panel : 'panel1');
        };
    useEffect(() => {
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");
    }, [])
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
                    <h2>Your cart: 3 item</h2>
                </div>
                <Row className="mt-5">
                    <Col xs={8} className='me-4 ms-5' style={{ width: '100%', height: "auto" }}>
                        <div>
                            <Table />
                        </div>
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
                                        <div>$29</div>
                                    </div>
                                </AccordionDetails>
                                <AccordionDetails>
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
                                </AccordionDetails>

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
                                    <TextField
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
                                    />
                                </AccordionDetails>
                            </Accordion>
                            <div style={{ backgroundColor: 'white', justifyContent: 'space-between' }} className='pt-4 pb-3 d-flex ps-2' >
                                <div className="ps-4" style={{ fontSize: '19px' }}>
                                    Total
                                </div>
                                <div className="pe-4" style={{ fontSize: '19px' }}>
                                    <b>$27</b>
                                </div>
                            </div>
                        </div>
                        <div className="mt-4">
                            <Button variant="contained" className='border-btn-black btn-bg-white btn-proceed pt-3 pb-3' >
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