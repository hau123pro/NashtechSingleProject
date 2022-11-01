import React from 'react';
import { Box, Button, Typography } from '@mui/material';
import { purple } from '@mui/material/colors';

const primary = purple[500]; // #f44336

export default function ErrorNotFound() {
    return (
        <Box
            sx={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                flexDirection: 'column',
                minHeight: '60vh',
            }}
        >
            <Typography variant="h1" style={{ color: 'black' }}>
                404
            </Typography>
            <Typography variant="h6" style={{ color: 'black' }}>
                The page you’re looking for doesn’t exist.
            </Typography>
            <Button variant="contained">Back Home</Button>
        </Box>
    );
}