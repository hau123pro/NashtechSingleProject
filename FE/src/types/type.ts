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
