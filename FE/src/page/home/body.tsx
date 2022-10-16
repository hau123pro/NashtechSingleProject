import React, { useState, useEffect, ReactElement } from 'react';
import { user, movie, cardgrid } from "../../types/type"
import testService from '../../service/movieService';
import "../../assets/css/main.css"
// import IsoTopeGrid from "react-isotope";
import Slider from './slide/slide';
import BookBestSelling from './book_best_selling/book_best_selling';
import BookBiography from './book_biography/book_biography';
import FavotiteAuthor from './author_favorite/author_favorite';
import FeatureBook from './feature_book/feature_book';
import FeatureCategories from './feature_category/feature_category';
import './home.css';

const Body: React.FC = () => {
  useEffect(() => {
    let a = document.getElementById('container-menu-desktop')?.classList.remove("container-desktop-height");
  }, [])

  // useEffect (() =>{

  //     },[])
  const [visible, setVisible] = useState<movie[]>([]);
  useEffect(() => {
    const respone = testService.getProduct().then(
      res => {
        setVisible(res.data);
      }
    ).catch(
      err => {
        console.log(err);
      }
    )
  }
    , []);
  const cardsDefault = [
    {
      id: "a",
      row: 1,
      col: 1,
      w: 1,
      h: 1,
      filter: ["test", "chart"]

    }
  ];

  const createMovieShowTime = () => {
    console.log("get data");
    console.log(visible);
    for (var i = 0; i < visible.length; i++) {
      cardsDefault.push(
        {
          id: visible[i].movieName,
          row: 1,
          col: 1,
          w: 1,
          h: 1,
          filter: ["test", "chart"]

        }
      )
    }
    cardsDefault.splice(0, 1);
    // getdata();
    const movie = new Array();
    visible.map(
      item => {
        movie.push(
          <div key={item.movieName} className='col-lg-6 col-md-3 col-sm-4 col-6 item' >
            <div style={{ display: "block" }}>
              <img style={{ width: "100%" }} src={item.imgUrl}></img>
            </div>
            <div>
              {item.movieName}

            </div>
          </div>
        );
      }
    )
    console.log(cardsDefault);
    return movie;
  }


  const filtersDefault = [
    { label: "test", isChecked: true },
    { label: "test1", isChecked: true },
    { label: "chart", isChecked: true },
    { label: "tile", isChecked: true }
  ];
  const [filters, updateFilters] = React.useState(filtersDefault);


  return (
    <>
      <Slider />
      {/* <Product /> */}
      <div className='wrap-product-selling mt-5' >
        <FeatureCategories />
        <BookBestSelling />
        <FeatureBook />
        <BookBiography />
        <FavotiteAuthor />
      </div>

    </>
  )
}
export default Body;