import React from 'react';
import CategoriesList from "./CategoriesList";
import {useNavigate} from "react-router";
import classes from "./UI/CategoryWindow.module.css"


function CategoryWindow() {

    let navigate = useNavigate();

    function handleClickCreate() {
        navigate("/categories/create_category");
    }

    function navigateToCategories() {
        navigate("/categories");
    }

    function navigateToBanners() {
        navigate("/banners");
    }


    return (
        <div className={classes.root}>
            <div className={classes.btnBar} style={{width: "1800px"}}>
                <div className={classes.square2}></div>
                <h4 className={classes.mainNavigate} onClick={navigateToBanners}>Banners</h4>
                <h4 className={classes.mainNavigate}
                    onClick={navigateToCategories}>Categories</h4>
            </div>
            <div className={classes.listBlock}>
                <div className={classes.square1}></div>
                <h3 style={{bottom: "668px", left: "40px", position: "relative"}}>Categories:</h3>
                <CategoriesList style={{width: "150px"}}></CategoriesList>
                <button className={classes.buttonsStyle} onClick={handleClickCreate}>createNewCategory</button>
            </div>
        </div>

    );
}

export default CategoryWindow;