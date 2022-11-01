import React, { FC, ReactElement, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import logo2 from "../../images/icons/logo-01.png";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Col, Row } from "react-bootstrap";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import "./navbar.css"
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import { Badge } from "@mui/material";
const NavBar: React.FC = () => {
    const [classes, setClasses] = useState<string>("");
    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);
    useEffect(() => {
        console.log("hehe");
        window.addEventListener("scroll", () => {
            console.log("hehe");
            setClasses("fixed-header");
            if (window.scrollY === 0) {
                setClasses("");;
            }
        });

    }, []);

    const handleClick = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    return (
        <div className="container-menu-desktop" id="container-menu-desktop">
            <nav className={`navbar fixed-top how-shadow1 wrap-menu ${classes} `} >

                <div className='container-fluid  navbar-expand-lg navbar-expand-md navbar-expand-sm'>
                    <div style={{ display: "flex" }}>
                        <div className="logo">
                            <img src={logo2} />
                        </div>
                        <ul className="navbar-nav ">
                            <li><Link to={'/'} className="nav-link mr-auto "> Home </Link></li>
                            <li><Link to={'/shop'} className="nav-link mr-auto "> Shop </Link></li>
                            <li><Link to={'/productDetail'} className="nav-link mr-auto"> ProductDetail </Link></li>
                            <li><Link to={'/cart'} className="nav-link mr-auto "> Cart </Link></li>
                            <li><Link to={'/cartDrawer'} className="nav-link mr-auto "> Cart </Link></li>
                            <li><Link to={'/order'} className="nav-link mr-auto "> Order </Link></li>
                        </ul>
                    </div>
                    <div className='help-line'>
                        <Row>
                            <Col xs={3}>
                                <Badge badgeContent={4} color="secondary">
                                    <ShoppingCartIcon className="icon_cart" style={{ fontSize: "30px", color: "blue" }} />
                                </Badge>
                            </Col>
                            <Col xs={9}>
                                <div>
                                    <Button
                                        id="demo-positioned-button"
                                        aria-controls={open ? 'demo-positioned-menu' : undefined}
                                        aria-haspopup="true"
                                        aria-expanded={open ? 'true' : undefined}
                                        onClick={handleClick}
                                    >
                                        <AccountCircleIcon style={{ fontSize: "30px" }} />
                                    </Button>
                                    <Menu
                                        id="demo-positioned-menu"
                                        aria-labelledby="demo-positioned-button"
                                        anchorEl={anchorEl}
                                        open={open}
                                        disableScrollLock={true}
                                        onClose={handleClose}

                                    >
                                        <MenuItem onClick={handleClose}><Link to={'/login'} >Login</Link></MenuItem>
                                        <MenuItem onClick={handleClose}>My account</MenuItem>
                                        <MenuItem onClick={handleClose}>Logout</MenuItem>
                                    </Menu>
                                </div>
                            </Col>
                        </Row>
                    </div>

                </div>
            </nav >
        </div >
    )
}
export default NavBar;