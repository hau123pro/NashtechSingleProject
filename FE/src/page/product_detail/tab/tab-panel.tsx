import * as React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import { Row, Col } from 'react-bootstrap';
import TabDetail from './tab_product_detail/tab_product_detail';
import { makeStyles } from "@material-ui/core/styles";
import TabReview from './tab_review/tab_review';
import { ProductDetailContext, productDetailContext, ProductDetailState } from '../../../context/productDetailContext';
import { useEffect, useState, useContext } from "react";

const useStyles = makeStyles({
    tabs: {

        "& .MuiTabs-indicator": {
            backgroundColor: "orange",
            height: 1,
        },
        "& .MuiTab-root.Mui-selected": {
            color: 'black'
        }


    }
})
interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}


function TabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box sx={{ p: 3 }}>
                    <div>{children}</div>
                </Box>
            )}
        </div>
    );
}

function a11yProps(index: number) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

export default function BasicTabs() {
    const { detailState, dispatchDetail }: productDetailContext = useContext(ProductDetailContext);
    const classes = useStyles();

    const styles = {
        tab: {
            padding: '5px',
            width: '160px',
            height: '72px',
            marginRight: "40px"
        },
        tabItemContainer: {
            background: 'none'
        }
    }
    const [value, setValue] = React.useState(0);

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    return (

        <Box sx={{ width: '100%' }}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs
                    value={value}
                    onChange={handleChange}
                    aria-label="basic tabs example"
                    TabIndicatorProps={{ style: { background: 'black' } }}
                    className={classes.tabs}
                    centered>
                    <Tab style={styles.tab} label="Description" {...a11yProps(0)} />
                    <Tab style={styles.tab} label="Product Details" {...a11yProps(1)} />
                    <Tab style={styles.tab} label="Reviews" {...a11yProps(2)} />
                </Tabs>
            </Box>
            <TabPanel value={value} index={0}>
                <Row className='d-flex-center'>
                    <Col xs={8}>
                        <div>
                            {detailState.product?.description}
                        </div>
                    </Col>
                </Row>

            </TabPanel>
            <TabPanel value={value} index={1} >
                <TabDetail />
            </TabPanel>
            <TabPanel value={value} index={2} >
                <TabReview />
            </TabPanel>

        </Box>
    );
}
