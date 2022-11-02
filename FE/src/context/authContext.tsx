import { createContext, useReducer } from "react";
import { AuthContextInterface } from "../types/type";
const initialState: AuthContextInterface = {
    isAuthenticated: false,
    token: null
};
export const AuthContext = createContext({});
export const authReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case 'Login':
            return {
                token: action.payload,
                isAuthenticated: true
            };
        case "Logout":
            return {
                token: null,
                isAuthenticated: false
            }
        default:
            return state;
    }
}

