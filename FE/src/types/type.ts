import internal from "stream";

export interface user {
    id: number,
    name: string

}
export interface movie {
    // id:number,
    movieName: string,
    imgUrl: string

}
export interface cardgrid {
    id: string,
    row: number,
    col: number,
    w: number,
    h: number,
    filter: string[]

}
export interface userlogin {
    email: string,
    password: string
}
export interface AuthState {
    email: string,
    isRegistered: boolean,
    loadingState: boolean,
    success: string,
    error: string
}
export interface ProductResponse {
    id: number,
    productName: string,
    description: string,
    imgUrl: string
    maxPrice: number,
    minPrice: number,
    author: AuthorResponse,
    averageRating: number
}
export interface AuthorResponse {
    id: number,
    firstName: string,
    lastName: string,
    description: string,
    publishBook: number,
    quantitySale: number,
    imgUrl: string
}
export interface Filter {
    page: number,
    size: number,
    categoryId: number | null,
    formatId: number | null,
    authorId: number | null,
    finalPrice: number | null,
    firstPrice: number | null
}
export interface ReviewResponse {
    userName: string,
    content: string,
    rating: number,
    dateCreate: string
}
export interface CategoryResponse {
    id: number;
    name: string,
    description: string
}
export interface FormatResponse {
    id: number,
    formatName: string,
    description: string
}
export interface FormatProductResponse extends FormatResponse {
    price: number,
    quantity: number
}
export interface listCategory {
    category: Array<CategoryResponse>
}

export interface AuthContextInterface {
    token: string | null,
    isAuthenticated: boolean,
    countItemCart: number
}
export interface CartAddRequest {
    productId: number,
    formatId: number,
    quantity: number
}
export interface CartUpdateRequest extends CartAddRequest {
    cartId: number
}
export interface CartResponse {
    id: number;
    firstPrice: number;
    finalPrice: number;
    dateCreate: string;
    quantity: number;
    countItem: number;
}
export interface CartItemResponse {
    productId: number;
    formatId: number;
    productName: string;
    formatName: string;
    imgUrl: string;
    quantity: number;
    firstPrice: number;
    finalPrice: number;
}
export interface tokenBodyClaim {
    role: string
}
export interface updateQuantityCart {
    productId: number,
    formatId: number,
    quantity: number
}
export interface PageResponse {
    page: number,
    size: number,
    totalPage: number,
    totalElement: number
}
export interface CategoryPageResponse {
    cartRespones: Array<CategoryResponse>,
    pageResponse: PageResponse | null
}
export interface ProductPageResponse {
    listProduct: Array<ProductResponse>,
    pageResponse: PageResponse | null
}