import React from 'react'
import { useEffect, useState, useContext } from "react";
import { Grid, Paper, Avatar, TextField, Button, Typography, Link, Alert, CircularProgress } from '@mui/material';
import LockIcon from '@mui/icons-material/Lock';
import { userlogin, AuthState, AuthContextInterface } from "../../types/type";
import loginService from '../../service/loginService';
import "./login.css"
import { useNavigate } from "react-router-dom";
import { AuthContext } from '../../context/authContext';
interface Context {
    dispatchUser?: any,
    user?: AuthContextInterface
}

const Login: React.FC = () => {
    const navigation = useNavigate();
    const { dispatch }: any = useContext(AuthContext);
    const [authSate, setLoading] = useState<AuthState>(
        {
            email: "",
            isRegistered: false,
            loadingState: false,
            success: "",
            error: ""
        }
    );
    const [data, setData] = useState<userlogin>(
        {
            email: "",
            password: ""
        }
    );
    useEffect(() => {
        let a = document.getElementById('container-menu-desktop')?.classList.add("container-desktop-height");
    }, [])
    const paperStyle = { padding: 20, height: '70vh', width: 400, margin: "20px auto" }
    const avatarStyle = { backgroundColor: '#1bbd7e' }
    const btnstyle = { margin: '8px 0' }
    useEffect(() => {
        console.log(data);
    }, [data])
    const handleInput = (e: React.ChangeEvent<HTMLInputElement>) => {
        const name = e.target.name;
        const newValue = e.target.value;
        setData({ ...data, [name]: newValue });
    };
    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setLoading({ ...authSate, loadingState: true });
        loginService.login(data).then(
            response => {
                localStorage.setItem("token", response.data.accesToken);
                navigation("/");
                dispatch({
                    type: "Login",
                    payload: response.data.accesToken
                });
            }
        ).catch(
            error => {
                setLoading({ ...authSate, error: "Invalid email or password" })
            }
        )
    }
    return (
        <Grid>
            <Paper elevation={10} style={paperStyle} className="d_flex_center">
                <Avatar style={avatarStyle}><LockIcon /></Avatar>
                <h2>Sign In</h2>
                <form className='input' onSubmit={handleSubmit}>
                    <TextField
                        className='input_item'
                        label='Username'
                        name="email"
                        placeholder='Enter username'
                        variant="outlined"
                        fullWidth required
                        onChange={handleInput} />
                    <TextField
                        className='input_item'
                        label='Password'
                        name="password"
                        placeholder='Enter password'
                        type='password'
                        variant="outlined"
                        fullWidth required
                        onChange={handleInput} />
                    <Button type='submit' color='primary' variant="contained" style={btnstyle} fullWidth>
                        {authSate.loadingState == false ? `Sign in` : <CircularProgress color="inherit" />}
                    </Button>
                </form>
                {authSate.error && <Alert severity="error">{authSate.error}</Alert>}
                <Typography >
                    <Link href="#" >
                        Forgot password ?
                    </Link>
                </Typography>

                <Typography > Do you have an account ?
                    <Button>
                        Sign Up
                    </Button>
                </Typography>
            </Paper>
        </Grid>
    )
}

export default Login;