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
    categoryId: number,
    formatId: number,
    authorId: number,
    finalPrice: number,
    firstPrice: number
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
    bonusPrice: number,
    description: string
}
export interface listCategory {
    category: Array<CategoryResponse>
}