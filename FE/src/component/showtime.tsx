import React,{useState,useEffect, ReactElement} from 'react';
import {user,movie}  from "../types/type"
import '../assets/bootstrap/css/bootstrap.min.css';
import testService from '../service/movieService';

const Hello: React.FC<user>= (props:user)  =>{
    
    // useEffect (() =>{
        
    //     },[])
    const [visible, setVisible] = useState<movie[]>([]);

    useEffect (()=>{
        const fetchData = async () => {
            const respone=await testService.getProduct();
            setVisible(respone.data); 
            // let obj=new Array('haha','hihi');
            // setVisible(obj);
          }
          fetchData();
        }
    ,[]);
    
    const createMovieShowTime= () =>{
        console.log("get data");
        // getdata();
        
        const movie =new Array();
        visible.map(
            item =>{
                movie.push( <li key={item.movieName} className="list-inline-item"><a className="social-icon text-xs-center" target="_blank" href="#"><img src={item.imgUrl}></img><br/>{item.movieName}</a></li>);
            }
        )
        return movie;
    }
    
    return (
        <>
        <div>hello {props.name}</div>
        <ul className="list-inline">
            {createMovieShowTime()}
        </ul>
        </>
    )
}
export default Hello;