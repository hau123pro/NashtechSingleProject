import { Button, Grid } from "@mui/material";
import { useEffect, useState, useReducer, useContext } from "react";
import { Col, Container, Row } from "react-bootstrap";
import './admin-category.css'
import MenuBookIcon from '@mui/icons-material/MenuBook';
import NavbarAdmin from "../navbarAdmin/nav-bar";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import IconButton from '@mui/material/IconButton';
import TablePagination from "@mui/material/TablePagination";
import ClearIcon from '@mui/icons-material/Clear';
import Paper from '@mui/material/Paper';
import { TextField } from "@material-ui/core";
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';

const CategoryAdmin: React.FC = () => {
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(3);
    const [status, serStatus] = useState<string>('ACTIVE')
    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };
    useEffect(() => {
        console.log(status);
    }, [status])
    const handleChangeStatus = (event: any) => {
        console.log(event.currentTarget.value);
        if (event.currentTarget.value == "ACTIVE")
            serStatus("INACTIVE");
        else {
            serStatus("ACTIVE");
        }
    };
    const handleChangeRowsPerPage = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };
    // useEffect(() => {
    //     console.log(active);
    // }, [active])
    return (
        <>
            <div className="pt-4 ps-3 pb-5">
                <Grid container spacing={2}>
                    <NavbarAdmin />
                    <Grid item xs={8}>
                        <div style={{ width: "100%", display: 'flex', justifyContent: 'space-between' }}>
                            <div className="pt-3 px-5" style={{ display: 'flex', alignItems: 'center' }}>
                                <MenuBookIcon className="icon-home" />
                                <div className="ms-2 icon-home" style={{ fontSize: '14px', marginBottom: '2px' }}>/</div>
                                <div className="ms-2 icon-home" style={{ fontSize: '14px', marginBottom: '2px' }}>Category</div>
                            </div>

                        </div>
                        <TableContainer component={Paper} sx={{ maxHeight: "40rem" }} className="ms-5 mt-4">
                            <Table sx={{ width: '100%' }} aria-label="customized table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell style={{ width: "10%" }}>CategoryId</TableCell>
                                        <TableCell align="left" style={{ width: "30%" }}>CategoryName</TableCell>
                                        <TableCell align="left" style={{ width: "42%" }}>Description</TableCell>
                                        <TableCell className="text-center">Status</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody >
                                    {/* {state.cartItem
                                        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                        .map((item: CartItemResponse) => ( */}
                                    <TableRow className='pt-3 pb-5' style={{ height: '5rem' }}>
                                        <TableCell component="th" scope="row" className="text-center">
                                            1
                                        </TableCell>
                                        <TableCell align="left">Romance</TableCell>
                                        <TableCell align="left">Something delicious</TableCell>
                                        <TableCell align="center">
                                            <Button variant="contained" className={status == 'ACTIVE' ? `button-active` : `button-inactive`} value={status} onClick={handleChangeStatus}>
                                                {status}
                                            </Button>
                                        </TableCell>
                                    </TableRow>
                                    {/* ))} */}
                                </TableBody>
                                <TableBody >
                                    {/* {state.cartItem
                                        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                        .map((item: CartItemResponse) => ( */}
                                    <TableRow className='pt-3 pb-5' style={{ height: '5rem' }}>
                                        <TableCell component="th" scope="row" className="text-center">
                                            1
                                        </TableCell>
                                        <TableCell align="left">Romance</TableCell>
                                        <TableCell align="left">Something delicious</TableCell>
                                        <TableCell align="center">
                                            <Button variant="contained"
                                                className={status == 'ACTIVE' ? `button-active` : `button-inactive`}
                                                value={status}
                                                onClick={handleChangeStatus}
                                            >
                                                {status}
                                            </Button>
                                        </TableCell>
                                    </TableRow>
                                    {/* ))} */}
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <TablePagination
                            rowsPerPageOptions={[3, 5, 7]}
                            component="div"
                            count={0}
                            rowsPerPage={rowsPerPage}
                            page={page}
                            onPageChange={handleChangePage}
                            onRowsPerPageChange={handleChangeRowsPerPage}
                        />
                    </Grid>

                </Grid>
            </div>
        </>
    )
}
export default CategoryAdmin;