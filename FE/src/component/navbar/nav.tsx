import React, { FC, ReactElement, useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import logo2 from "../../images/icons/logo-01.png";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Col, Row } from "react-bootstrap";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import "./navbar.css"
import jwt_decode from "jwt-decode";
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import { Badge } from "@mui/material";
import { AuthContext } from "../../context/authContext";
import { AuthContextInterface } from "../../types/type";
interface Context {
    dispatch?: any,
    user?: AuthContextInterface
}
const NavBar: React.FC = () => {
    const [classes, setClasses] = useState<string>("");
    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const { user, dispatch }: Context = useContext(AuthContext);
    const [name, setName] = useState<string | null>();
    const open = Boolean(anchorEl);
    // useEffect
    useEffect(() => {
        console.log("hehe");
        window.addEventListener("scroll", () => {
            console.log("hehe");
            setClasses("fixed-header");
            if (window.scrollY === 0) {
                setClasses("");;
            }
        });
        const token = localStorage.getItem('token');
        if (token) {
            const decoded: any = jwt_decode(token);
            console.log(decoded.name);
            setName(decoded.name);
        }
    }, [user]);

    const handleClick = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    const handleLogout = () => {
        dispatch({
            type: "Logout",
            payload: ""
        });
        localStorage.removeItem('token');
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
                                {user?.isAuthenticated ?
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
                                            <MenuItem onClick={handleClose}>Chao: {name}</MenuItem>
                                            <MenuItem onClick={() => {
                                                handleClose();
                                                handleLogout();
                                            }}>Logout</MenuItem>
                                        </Menu>
                                    </div>
                                    : <div>
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
                                            <MenuItem onClick={handleClose}>Register</MenuItem>
                                        </Menu>
                                    </div>
                                }
                            </Col>
                        </Row>
                    </div>

                </div>
            </nav >
        </div >
    )
}
export default NavBar;