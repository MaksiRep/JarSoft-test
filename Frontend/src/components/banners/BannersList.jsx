import React, {useEffect, useState} from 'react';
import axios from "axios";
import Banners from "./Banners";
import Cookies from "js-cookie";
import {useNavigate} from "react-router";
import classes from "./UI/BannerWindow.module.css";


function BannersList() {

    const navigate = useNavigate()

    const [data, setData] = useState([]);

    useEffect(() => {
        axios
            .get(`http://localhost:8080/banners`, {
                headers: {
                    'Authorization': `Bearer ${Cookies.get('token')}`,
                }
            })
            .then(response => {
                setData(response.data)
            }).catch(error => {
            if (error.response.status === 500 || error.response.status === 403)
                navigate("/")
        })
    }, [])


    const [value, setValue] = useState('')

    const filteredBanners = data.filter(banner => {
        return banner.name.toLowerCase().includes(value.toLowerCase())
    })

    let counter = 1

    return (
        <ul style={{width: "150px", height:"150px", bottom: "200px"}}>
            <input className={classes.banners} onChange={(event => setValue(event.target.value))}/>
            {filteredBanners.map(banner => {
                if (counter <= 15) {
                    counter = counter + 1
                    return <Banners banner={banner} key={banner.id} index={banner.id}/>
                }
            })}
        </ul>);
}

export default BannersList;

