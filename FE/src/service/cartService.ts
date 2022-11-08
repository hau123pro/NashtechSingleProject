import axios from 'axios';
import { ReviewState } from '../page/product_detail/tab/tab_review/tab_review';
import { CartAddRequest, CartUpdateRequest } from '../types/type';
const URL_addItemCart = 'http://localhost:8080/api/v1/client/cart/add';
const URL_getCountItemCart = 'http://localhost:8080/api/v1/cart/countItem';
const URL_getCart = 'http://localhost:8080/api/v1/cart';
const URL_updateCart = 'http://localhost:8080/api/v1/client/cart/update';
const URL_deleteItemCart = 'http://localhost:8080/api/v1/client/cart/delete/item';
class cartService {
    async addCart(data: CartAddRequest, token: string) {
        return await axios({
            method: "POST",
            data: data,
            url: URL_addItemCart,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }

        })
    };
    async getCountItemCart(token: string) {
        return await axios({
            method: "GET",
            url: URL_getCountItemCart,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }

        })
    };
    async getCart(token: string) {
        return await axios({
            method: "GET",
            url: URL_getCart,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }

        })
    };
    async updateCart(cart: CartUpdateRequest, token: string) {
        return await axios({
            method: "PUT",
            url: URL_updateCart,
            data: cart,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }

        })
    };
}
export default new cartService();