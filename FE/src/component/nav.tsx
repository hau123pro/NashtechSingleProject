import React, { FC, ReactElement, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import logo2 from "../images/icons/logo-01.png"

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

        // <-- DOM-Window, extends DOM-EventTarget

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
                        </ul>
                    </div>
                    <div className='help-line'>
                        <a href=''>0945437332</a></div>
                </div>
            </nav>
        </div>
    )
}
export default NavBar;