import React from 'react';
import {useNavigate} from "react-router";
import classes from "./UI/BannerWindow.module.css";


const Banners = ({banner, index}) => {

    let navigate = useNavigate();

    function updateBanner() {
        const array = []
        banner.categories.map(category => array.push(category.name))
        navigate(`/banners/update_banner/${banner.id}/${banner.name}/${banner.price}/${banner.textField}/${array}`)
    }

    return (
        <li style={{width: "150px"}} onClick={() => updateBanner(index)} className={classes.banners}>
            {banner.name}
        </li>
    );
};


export default Banners;