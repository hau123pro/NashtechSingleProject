
import { useEffect, useState } from "react";
import { Container, Row, Col } from 'react-bootstrap';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails'
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import TextField from '@mui/material/TextField';
import IconButton from '@mui/material/IconButton';
import Button from '@mui/material/Button';

const Order: React.FC = () => {
    const [expanded, setExpanded] = useState('panel1');

    const handleChange =
        (panel: string) => (event: React.SyntheticEvent, isExpanded: boolean) => {
            setExpanded(isExpanded ? panel : 'panel1');
        };
    useEffect(() => {
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");
    }, [])
    return (
        <>

        </>
    )
}
export default Order;
