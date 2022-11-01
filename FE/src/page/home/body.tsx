import React, { useState, useEffect, ReactElement } from 'react';
import { user, movie, cardgrid } from "../../types/type"
import testService from '../../service/movieService';
import "../../assets/css/main.css"
// import IsoTopeGrid from "react-isotope";
import Slider from './slide/slide';
// import BookBestSelling from './book_best_selling/book_best_selling';
import BookBiography from './book_biography/book_biography';
import FavotiteAuthor from './author_favorite/author_favorite';
import FeatureBook from './feature_book/feature_book';
import FeatureCategories from './feature_category/feature_category';
import './home.css';
import BookFeature from './book_feature/book_feature';

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



  return (
    <>
      <Slider />
      {/* <Product /> */}
      <div className='wrap-product-selling mt-5' >
        <FeatureCategories />
        <BookFeature />
        {/* <FeatureBook /> */}
        <BookBiography />
        {/* <FavotiteAuthor /> */}
      </div>

    </>
  )
}
export default Body;