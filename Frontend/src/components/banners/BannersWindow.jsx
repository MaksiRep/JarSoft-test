import React from 'react';
import {useNavigate} from "react-router";
import BannersList from "./BannersList";
import classes from './UI/BannerWindow.module.css';

const BannersWindow = () => {
    let navigate = useNavigate();

    function handleClickCreate() {
        navigate("/banners/create_banner");
    }

    function navigateToCategories() {
        navigate("/categories");
    }

    function navigateToBanners() {
        navigate("/banners");
    }

    return (
        <div>
            <div className={classes.btnBar} style={{width: "1800px"}}>
                <div className={classes.square2}></div>
                <h4 className={classes.mainNavigate} onClick={navigateToBanners}>Banners</h4>
                <h4 className={classes.mainNavigate}
                    onClick={navigateToCategories}>Categories</h4>
            </div>

            <div className={classes.listBlock}>
                <div className={classes.square1}></div>
                <h3 style={{bottom: "668px", left: "40px", position: "relative"}}>Banners:</h3>
                <BannersList style={{width: "150px"}}></BannersList>
                <button className={classes.buttonsStyle} onClick={handleClickCreate}>createNewBanner</button>
            </div>

        </div>

    )

};

export default BannersWindow;