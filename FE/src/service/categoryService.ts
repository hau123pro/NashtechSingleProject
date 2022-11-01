import React, { useState, useEffect, ReactElement } from 'react';
import { userlogin } from "../types/type"
import axios from 'axios';
const URL_getAllCategoryActive = 'http://localhost:8080/api/v1/category/active/all';
const URL_getCategoryActiveByPage = 'http://localhost:8080/api/v1/category/active?';
class categoryService {


    async getActiveAllCategory() {
        return await axios({
            method: "GET",
            url: URL_getAllCategoryActive,
        })
    }
    async getSomeCategory() {
        var url = URL_getCategoryActiveByPage + "page=" + 0 + "size=" + 6;
        return await axios({
            method: "GET",
            url: URL_getCategoryActiveByPage,
        })
    }

}
export default new categoryService();