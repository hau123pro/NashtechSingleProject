import React, { useState, useEffect, ReactElement } from 'react';
import { user } from "../types/type"
import axios from 'axios';
const URL1 = 'http://localhost:8080/api/getMovie';

class testService {


    getProduct() {
        return axios.get(URL1);
    }

}
export default new testService();