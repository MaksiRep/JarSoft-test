import React, {useState} from 'react';
import axios from "axios";
import CategoryWindow from "./CategoryWindow";
import {useNavigate} from "react-router";
import Cookies from 'js-cookie';
import classes from "./UI/AddCategory.module.css"

function AddCategory() {

    let navigate = useNavigate()

    function returnFunc() {
        navigate(`/categories`)
    }

    let [errorText, setErrorText] = useState();
    let [isShowingErrorBlock, setError] = useState(false)

    function handleClickSave() {
        let name = document.querySelector("#name");
        let requestId = document.querySelector("#request");
        axios
            .post(`http://localhost:8080/categories`, {
                name: name.value,
                requestId: requestId.value
            }, {
                headers: {
                    'Authorization': `Bearer ${Cookies.get('token')}`,
                }
            }).then(() => {
                returnFunc()
            }
        )
            .catch(error => {
                setErrorText(error.request.responseText)
                setError(true)
                console.error(error.request.responseText);
            });
    }


    return (
        <div className={classes.root}>
            <CategoryWindow></CategoryWindow>
            <div className={classes.square1}></div>

            <h2 style={{
                position: "relative",
                left: "270px",
                bottom: "1660px",
                width: "750px"
            }}> Create new Category</h2>

            <div style={{width: "100px"}}>
                <h4 className={classes.textStyle}>Name</h4>
                <h4 className={classes.textStyle}>RequestID</h4>
            </div>

            <div className={classes.inputsStyle}><input id="name"
                                                        type='text'
                                                        placeholder='name'
                                                        style={{width: "500px", height: "40px"}}
            />
            </div>
            <div className={classes.inputsStyle}><input id="request"
                                                        type='text'
                                                        placeholder='requestId'
                                                        style={{width: "500px", height: "40px"}}
            />
            </div>

            <button className={classes.buttonsStyle} onClick={handleClickSave}>Save</button>

            <div style={{bottom: "1590px", left: "270px", position: "relative", color: "red", fontSize: "30px"}}>
                {isShowingErrorBlock && (errorText)}
            </div>
        </div>
    );
};

export default AddCategory;