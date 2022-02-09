import React, {useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router";
import Cookies from 'js-cookie';

const Auth = () => {

    let navigate = useNavigate()

    function returnFunc() {
        navigate(`/banners`)
    }

    function executeBasicAuthenticationService() {
       return  axios
            .post(`http://localhost:8080/login`, {
                name : login,
                password : password
            }).then(response => {
                const id = response.data.access_token;
                Cookies.set('token', id, {expires: 1});
                returnFunc();
           })
    }


    let [login, setLogin] = useState('');
    let [password, setPassword] = useState('');


    return (
        <div>
            <input type="text"
                   placeholder="login"
                   onChange = {(log) => setLogin(log.target.value)}/>
            <input type="password"
                    placeholder="password"
                    onChange={(pas) => setPassword(pas.target.value)}/>
            <button onClick={executeBasicAuthenticationService}>
                Login
            </button>
        </div>
    );
};

export default Auth;