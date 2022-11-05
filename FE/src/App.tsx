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
import { AuthContext, authReducer } from './context/authContext';
import ErrorNotFound from './component/error/error';
import { AuthContextInterface, tokenBodyClaim } from './types/type';
import cartService from './service/cartService';
import CategoryAdmin from './page/admin/category/category';
const initialState: AuthContextInterface = {
  isAuthenticated: false,
  token: null,
  countItemCart: 0
};
const App: React.FC = () => {
  const [user, dispatch] = useReducer(authReducer, initialState);
  const [role, setRole] = useState<string>('');
  useEffect(() => {
    console.log(user);
    const token = localStorage.getItem('token');
    if (token) {
      dispatch({
        type: 'Login',
        payload: ''
      });
      cartService.getCountItemCart(token).then(
        (res) => {
          dispatch({
            type: 'set-Count',
            payload: res.data
          });
        }
      ).catch(
        (err) => {
          console.log(err);
        }
      );
    }
  }, [])
  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      const decoded: tokenBodyClaim = jwt_decode(token);
      console.log(decoded.role);
      setRole(decoded.role);
      if (decoded.role == "USER") {
        cartService.getCountItemCart(token).then(
          (res) => {
            dispatch({
              type: 'set-Count',
              payload: res.data
            });
          }
        ).catch(
          (err) => {
            console.log(err);
          }
        )
      }
    }
    else {
      dispatch({
        type: 'set-Count',
        payload: 0
      });
    }
  }, [user.token, user.countItemCart])

  return (
    <>{
      role == 'ADMIN' ?
        <AuthContext.Provider value={{ user, dispatch }}>
          <div className='container-fluid p-0 bg-color-admin'>
            <Router>
              <Routes >
                <Route path='/admin/category' element={<CategoryAdmin />} />
              </Routes>

            </Router>
          </div>
        </AuthContext.Provider>
        : <AuthContext.Provider value={{ user, dispatch }}>
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

    }
    </>

  );
}

export default App;
