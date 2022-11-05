
import { Grid } from "@mui/material";
import { useEffect, useState, useReducer, useContext } from "react";
import { Col, Container, Row } from "react-bootstrap";
import CategoryIcon from '@mui/icons-material/Category';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
import PeopleAltIcon from '@mui/icons-material/PeopleAlt';
import MenuBookIcon from '@mui/icons-material/MenuBook';
import { Link } from "react-router-dom";
const NavbarAdmin: React.FC = () => {
    const [active, setActive] = useState<number>();
    const hanldeClickNav = (page: number) => {
        setActive(page);
    }
    useEffect(() => {
        console.log(active);
    }, [active])
    return (
        <>
            <Grid item xs={2} >
                <div className="drawer-paper">
                    <div style={{ height: '4rem' }} className="pt-4 pb-2 name-admin color-white">
                        Welcome: Hau
                    </div>
                    <hr className="mx-3 color-white" ></hr>
                    <Link to={'/admin/category'}>
                        <div className={`color-white mx-3 button-nav px-1  ${active == 1 ? `bg-color-blue` : null} `}
                            onClick={() => {
                                hanldeClickNav(1);
                            }}>
                            <CategoryIcon className="ms-2 me-1" />
                            <div className="ps-2"> Category</div>
                        </div>
                    </Link>
                    <Link to={'/admin/category'}>
                        <div className={`color-white mx-3 button-nav px-1  ${active == 2 ? `bg-color-blue` : null} `}
                            onClick={() => {
                                hanldeClickNav(2);
                            }}>
                            <AutoStoriesIcon className="ms-2 me-1" />
                            <div className="ps-2"> Product</div>
                        </div>
                    </Link>
                    <div className={`color-white mx-3 button-nav px-1  ${active == 3 ? `bg-color-blue` : null} `}
                        onClick={() => {
                            hanldeClickNav(3);
                        }}>
                        <PeopleAltIcon className="ms-2 me-1" />
                        <div className="ps-2"> User</div>
                    </div>
                </div>

            </Grid>
        </>
    )
}
export default NavbarAdmin; 