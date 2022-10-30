import React, { useState, useEffect, ReactElement } from 'react';
import { userlogin } from "../types/type"
import axios from 'axios';
const URL_getAllCategoryActive = 'http://localhost:8080/api/v1/category/active/all';

class categoryService {


    async getActiveAllCategory() {
        return await axios({
            method: "GET",
            url: URL_getAllCategoryActive,
        })
    }

}
export default new categoryService();