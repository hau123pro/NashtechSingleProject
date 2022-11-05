import React, { useState, useEffect, ReactElement } from 'react';
import { userlogin } from "../types/type"
import axios from 'axios';
const URL_addOrder = 'http://localhost:8080/api/v1/order/add';
// const URL_getCategoryActiveByPage = 'http://localhost:8080/api/v1/category/active?';
class categoryService {


    async addOrder(token: string) {
        return await axios({
            method: "POST",
            url: URL_addOrder,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        })
    }

}
export default new categoryService();