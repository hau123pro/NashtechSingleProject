import React, { FC, ReactElement, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import logo2 from "../../images/icons/logo-01.png";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Col, Row } from "react-bootstrap";
import MenuAccount from "./menu/menu";
import "./navbar.css"
const NavBar: React.FC = () => {
    const [classes, setClasses] = useState<string>("");
    useEffect(() => {
        window.addEventListener("scroll", () => {
            console.log("hehe");
            setClasses("fixed-header");
            if (window.scrollY === 0) {
                setClasses("");;
            }
        });

    }, []);

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
                            <Col xs={3}><a href=''><ShoppingCartIcon className="icon_cart" style={{ fontSize: "30px" }} /></a> </Col>
                            <Col xs={9}><MenuAccount /></Col>
                        </Row>
                    </div>

                </div>
            </nav>
        </div>
    )
}
export default NavBar;