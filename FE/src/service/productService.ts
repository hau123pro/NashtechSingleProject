import React, { useState, useEffect, ReactElement } from 'react';
import { Filter, userlogin } from "../types/type"
import axios from 'axios';
const URL_getProductByPage = 'http://localhost:8080/api/v1/product/shop?';

class productService {


    async loadData(data: Filter) {
        const url = URL_getProductByPage + "page=" + data.page + "&size=" + data.size;
        return await axios({
            method: "GET",
            url: url,
        })
    }

}
export default new productService();