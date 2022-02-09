import React, {useEffect, useState} from 'react';
import CategoryWindow from "./CategoryWindow";
import {useNavigate, useParams} from "react-router";
import axios from "axios";
import Cookies from "js-cookie";
import classes from "./UI/UpdateCategory.module.css"

function UpdateCategory() {

    let [errorText, setErrorText] = useState();
    let [isShowingErrorBlock, setError] = useState(false)

    let navigate = useNavigate();
    let params = useParams();

    useEffect(() => {
        document.getElementById("name").value = params.name
        document.getElementById("request").value = params.requestId
        setError(false)
    }, [params.id]);


    function returnFunc() {
        navigate(`/categories`)
    }

    function handleClickDelete() {
        axios
            .delete(`http://localhost:8080/categories/${params.id}`, {
                headers: {
                    'Authorization': `Bearer ${Cookies.get('token')}`,
                }
            })
            .then(() => {
                returnFunc()
            })
            .catch(error => {
                setErrorText(error.request.responseText)
                setError(true)
            });
    }

    function handleClickSave() {
        let name = document.querySelector("#name");
        let requestId = document.querySelector("#request");
        axios
            .put(`http://localhost:8080/categories`, {
                id: params.id, name: name.value, requestId: requestId.value
            }, {
                headers: {
                    'Authorization': `Bearer ${Cookies.get('token')}`,
                }
            }).then(() => {
            returnFunc()
        })
            .catch(error => {
                setErrorText(error.request.responseText)
                setError(true)
            });
    }

    return (<div>
            <CategoryWindow></CategoryWindow>
            <div>
                <div className={classes.square1}></div>
                <h2 style={{
                    position: "relative",
                    left: "270px",
                    bottom: "1660px",
                    width: "750px"
                }}> {params.name} ID: {params.id}</h2>

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

                <button className={classes.buttonsStyle} onClick={handleClickSave}>
                    Save
                </button>
                <button className={classes.buttonsStyle} onClick={handleClickDelete}
                        style={{left: "863px"}}>
                    Delete
                </button>

                <div style={{bottom: "1590px", left: "270px", position: "relative", color: "red", fontSize: "30px"}}>
                    {isShowingErrorBlock && (errorText)}
                </div>
            </div>
        </div>

    )
};


export default UpdateCategory;