import { Filter } from "../types/type"
import axios from 'axios';
import queryString from 'query-string';

const URL_getProductByPage = 'http://localhost:8080/api/v1/product/shop?';
const URL_getProductFilterByPage = 'http://localhost:8080/api/v1/product/filter?';
const URL_getProductFeaturer = 'http://localhost:8080/api/v1/product/feature';
const URL_getProductById = 'http://localhost:8080/api/v1/product';
class productService {


    async loadData(data: Filter) {
        const url = URL_getProductByPage + "page=" + data.page + "&size=" + data.size;
        return await axios({
            method: "GET",
            url: url,
        })
    }
    async loadDataFilter(data: Filter) {
        const paramFilter = queryString.stringify(data);
        console.log(paramFilter);
        const url = URL_getProductFilterByPage + paramFilter;
        return await axios({
            method: "GET",
            url: url,
            data: data,
            headers: {
                "Content-Type": "application/json"
            }
        })
    }
    async loadDataFeature() {
        return await axios({
            method: "GET",
            url: URL_getProductFeaturer
        })
    }
    async loadProductById(id: string) {
        const url = URL_getProductById + "/" + id;
        return await axios({
            method: "GET",
            url: url
        })
    }
}
export default new productService();