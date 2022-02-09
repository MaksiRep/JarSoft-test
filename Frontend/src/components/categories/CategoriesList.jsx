import React, {useEffect, useState} from 'react';
import Categories from "./Categories";
import axios from "axios";
import Cookies from "js-cookie";
import {useNavigate} from "react-router";
import classes from "./UI/CategoryWindow.module.css";



function CategoriesList() {


    const navigate = useNavigate();

    const [data, setData] = useState([]);

    useEffect(() => {
        axios
            .get(`http://localhost:8080/categories`, {
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

    const filteredCategories = data.filter(category => {
        return category.name.toLowerCase().includes(value.toLowerCase())
    })

    let counter = 1
    return (
        <ul style={{width: "150px", height:"150px", bottom: "200px"}}>
            <input className={classes.categories} onChange={(event => setValue(event.target.value))}/>
            {filteredCategories.map(category => {
                if (counter <= 15) {
                    counter = counter + 1
                    return <Categories category={category} key={category.id} index={category.id}/>
                }
            })}
        </ul>
    );
}

export default CategoriesList;

