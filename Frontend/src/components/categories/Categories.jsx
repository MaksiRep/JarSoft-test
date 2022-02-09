import React from 'react';
import {useNavigate} from "react-router";
import classes from "./UI/CategoryWindow.module.css";


const Categories = ({category, index}) => {

    let navigate = useNavigate();

    function updateCategory() {
        navigate(`/categories/update_category/${category.id}/${category.name}/${category.requestId}`)
    }

    return (
        <li style={{width: "150px"}} onClick={() => updateCategory(index)} className={classes.categories}>
            {category.name}
        </li>
    );
};


export default Categories;