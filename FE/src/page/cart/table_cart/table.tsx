import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import TablePagination from "@mui/material/TablePagination";
import ClearIcon from '@mui/icons-material/Clear';
import TextField from '@mui/material/TextField';
import RemoveIcon from '@mui/icons-material/Remove';
import IconButton from '@mui/material/IconButton';
import AddIcon from '@mui/icons-material/Add';

function createData(
    imgUrl: string,
    name: string,
    price: number,
    quantity: number,
    total: number
) {
    return { imgUrl, name, price, quantity, total };
}

const rows = [
    createData('https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg', 'Frozen yoghurt', 159, 6.0, 24),
    createData('https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg', 'Frozen yoghurt', 160, 6.0, 24),
    createData('https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg', 'Frozen yoghurt', 161, 6.0, 24),
    createData('https://demo4.madrasthemes.com/bookworm/wp-content/uploads/2020/08/22-120x183.jpg', 'Frozen yoghurt', 162, 6.0, 24),

];

export default function CustomizedTables() {
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(2);

    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    return (
        <>
            <TableContainer component={Paper} sx={{ maxHeight: "40rem" }}>
                <Table sx={{ width: '100%' }} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Product</TableCell>
                            <TableCell align="left">Price</TableCell>
                            <TableCell align="left">Quantity</TableCell>
                            <TableCell align="left">Total</TableCell>
                            <TableCell align="left"></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody >
                        {rows
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row) => (
                                <TableRow key={row.name} className='pt-3 pb-5' style={{ height: '13rem' }}>
                                    <TableCell component="th" scope="row">
                                        <div className='d-flex align-item-center'>
                                            <img style={{ maxWidth: '30%' }} src={row.imgUrl} alt="" />
                                            <div className='ms-3'>
                                                Book 1
                                            </div>
                                        </div>
                                    </TableCell>
                                    <TableCell align="left">{row.price}</TableCell>
                                    <TableCell align="left">
                                        <TextField
                                            id="standard-name"
                                            defaultValue={1}
                                            InputProps={{
                                                startAdornment:
                                                    <IconButton sx={{ borderRadius: 0 }} edge="start">
                                                        <RemoveIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                    </IconButton>,
                                                endAdornment:
                                                    <IconButton sx={{ borderRadius: 0 }} edge="end">
                                                        <AddIcon sx={{ stroke: "#ffffff", strokeWidth: 1 }} />
                                                    </IconButton>
                                            }}

                                            style={{ width: "6.5rem" }}
                                        />
                                    </TableCell>
                                    <TableCell align="left">{row.total}</TableCell>
                                    <TableCell align="left">
                                        <div>
                                            <IconButton sx={{ "&:hover": { backgroundColor: "white", color: 'red' } }}>
                                                <ClearIcon sx={{ fontSize: '28px' }} />
                                            </IconButton>
                                        </div>
                                    </TableCell>
                                </TableRow>
                            ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[2, 4, 6]}
                component="div"
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </>
    );
}
