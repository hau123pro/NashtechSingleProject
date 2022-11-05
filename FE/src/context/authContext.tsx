import { createContext, useReducer } from "react";
import { AuthContextInterface } from "../types/type";
const initialState: AuthContextInterface = {
    isAuthenticated: false,
    token: null,
    countItemCart: 0
};
export const AuthContext = createContext({});
export const authReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case 'Login':
            return {
                ...state,
                token: action.payload,
                isAuthenticated: true
            };
        case "Logout":
            return {
                token: null,
                isAuthenticated: false,
                countItemCart: 0
            }
        case "set-Count":
            return {
                ...state,
                countItemCart: action.payload
            }
        default:
            return state;
    }
}

