import React, { useState, useEffect, ReactElement } from 'react';
import { userlogin } from "../types/type"
import axios from 'axios';
const URL_Login = 'http://localhost:8080/api/v1/auth/login';

class loginService {


    async login(data: userlogin) {
        return await axios({
            method: "POST",
            data: data,
            url: URL_Login,
            headers: {
                "Content-Type": "application/json"
            }

        })
    }

}
export default new loginService();