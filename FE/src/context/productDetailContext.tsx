import { createContext, useReducer } from "react";
import { AuthContextInterface, AuthorResponse, CategoryResponse, FormatProductResponse, ProductResponse, ReviewResponse } from "../types/type";
export interface ProductDetailState {
    product: ProductResponse | null,
    loadingProduct: boolean,
    loadingCategory: boolean,
    loadingformat: boolean,
    loadingAuthor: boolean,
    loadingAdd: boolean,
    loading: boolean,
    category: Array<CategoryResponse>,
    format: Array<FormatProductResponse>,
    author: AuthorResponse | null,
    review: Array<ReviewResponse>
}
export interface productDetailContext {
    dispatchDetail: any,
    detailState: ProductDetailState
}
const initState: ProductDetailState = {
    product: null,
    loadingProduct: false,
    loadingCategory: false,
    loadingformat: false,
    loadingAuthor: false,
    loadingAdd: false,
    loading: false,
    category: [],
    format: [],
    author: null,
    review: []
}
export const ProductDetailContext = createContext<productDetailContext>({
    dispatchDetail: null,
    detailState: initState
});
export const productDetailReducer = (state = initState, action: any) => {
    switch (action.type) {
        case 'set-product':
            return {
                ...state,
                product: action.payload,
                loadingProduct: true,
            };
        case 'set-category':
            return {
                ...state,
                category: action.payload,
                loadingCategory: true
            };
        case 'set-format':
            return {
                ...state,
                format: action.payload,
                loadingFormat: true
            };
        case 'set-author':
            return {
                ...state,
                author: action.payload,
                loadingAuthor: true
            };
        case 'set-review':
            return {
                ...state,
                review: action.payload,
            };
        case 'set-loadingAdd':
            return {
                ...state,
                loadingAdd: action.payload,
            };
        case 'set-loading':
            return {
                ...state,
                loading: action.payload,
            };
        default:
            throw new Error();
    }
}