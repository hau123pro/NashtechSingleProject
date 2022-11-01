import React, { useState, useEffect, useReducer } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import jwt_decode from "jwt-decode";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import "./assets/css/main.css"
import Footer from './component/footer/footer';
import NavBar from './component/navbar/nav';
import Body from './page/home/body';
import Product from './page/product/product';
import ProductDetail from './page/product_detail/product_detail';
import Cart from './page/cart/cart';
import CartDrawer from './component/cart/cart-drawer'
import Order from './page/order/order';
import Login from './page/login/login';
import authService from './service/authService';
import { AuthContext, authReducer, initialState } from './context/authContext';
import ErrorNotFound from './component/error/error';
const App: React.FC = () => {
  const [user, dispatch] = useReducer(authReducer, initialState);
  useEffect(() => {
    console.log(user);
    const token = user.token;
    if (token) {
      const decoded = jwt_decode(token);
      console.log(decoded);
    }
  }, [user])
  return (
    <>
      <AuthContext.Provider value={{ user, dispatch }}>
        <div className='container-fluid p-0'>
          <Router>
            <NavBar />
            <Routes>
              <Route path='/' element={<Body />} />
              <Route path='/shop' element={<Product />} />
              <Route path={`/productDetail/:id`} element={<ProductDetail />} />
              <Route path='/cart' element={<Cart />} />
              <Route path='/cartDrawer' element={<CartDrawer />} />
              <Route path='/order' element={<Order />} />
              <Route path='/login' element={<Login />} />
              <Route path='/*' element={<ErrorNotFound />} />
            </Routes>

          </Router>
          <Footer />
        </div>
      </AuthContext.Provider>
    </>

  );
}

export default App;
