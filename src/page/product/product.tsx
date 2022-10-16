import Row from 'react-bootstrap/Row';
import { useEffect, useState } from "react";
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import "./product.css"
import Select, { SelectChangeEvent } from '@mui/material/Select';
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


const Product: React.FC = () => {
    const [expanded, setExpanded] = useState<string | false>(false);

    const handleChange =
        (panel: string) => (event: React.SyntheticEvent, isExpanded: boolean) => {
            setExpanded(isExpanded ? panel : false);
        };

    useEffect(() => {
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");
    }, [])
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
                                        <div>Author</div>
                                    </h5>
                                </AccordionSummary>
                                <AccordionDetails>
                                    <div className='px-3  '>
                                        <a href="">Art</a>
                                    </div>
                                </AccordionDetails>
                                <AccordionDetails>
                                    <div className='px-3  '>
                                        <a href="">Art</a>
                                    </div>
                                </AccordionDetails>
                                <AccordionDetails>
                                    <div className='px-3 '>
                                        <a href="">Art</a>
                                    </div>
                                </AccordionDetails>
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
                                <AccordionDetails>
                                    <Typography>
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
                                        malesuada lacus ex, sit amet blandit leo lobortis eget.
                                    </Typography>
                                </AccordionDetails>
                            </Accordion>
                        </div>
                    </Col>
                    <Col lg={9} md={8} sm={10}>
                        <Row>
                            <div className=' pb-4' style={{ fontSize: "18px", display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                                <div>Showing 1–12 of 89 results</div>
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
                                cardsDefault.map(
                                    item => {
                                        return (
                                            <>
                                                <Col key={item.id} lg={3} md={5} sm={6} xs={6} className="item">
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
                                                            <div style={{ marginRight: "30%" }} className="pointer add-to-cart-text">
                                                                <a href=''>Add to cart</a>
                                                            </div>
                                                            <div className="wish-list-icon">
                                                                <i style={{ marginLeft: "23%", marginTop: "10%" }} className="bi bi-heart icon pointer"></i>
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
                                <Pagination count={10} size="large" />
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container >
        </>)

}
export default Product;