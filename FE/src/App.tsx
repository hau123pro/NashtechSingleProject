import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
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
const App: React.FC = () => {


  return (
    <>
      <div className='container-fluid p-0'>
        <Router>
          <NavBar />
          <Routes>
            <Route path='/' element={<Body />} />
            <Route path='/shop' element={<Product />} />
            <Route path='/productDetail' element={<ProductDetail />} />
            <Route path='/cart' element={<Cart />} />
            <Route path='/cartDrawer' element={<CartDrawer />} />
            <Route path='/order' element={<Order />} />
            <Route path='/login' element={<Login />} />
          </Routes>

        </Router>
        <Footer />
      </div>
    </>

  );
}

export default App;
