import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router";
import axios from "axios";
import BannersWindow from "./BannersWindow";
import {Multiselect} from "multiselect-react-dropdown";
import Cookies from "js-cookie";
import classes from "./UI/AddBanner.module.css";
import {TextArea} from "semantic-ui-react";


const AddBanner = () => {


    let navigate = useNavigate()

    function returnFunc() {
        navigate(`/banners`)
    }

    let [errorText, setErrorText] = useState();
    let [isShowingErrorBlock, setError] = useState(false)

    function handleClickSave() {
        let name = document.querySelector("#name");
        let price = document.querySelector("#price");
        let textField = document.querySelector("#textField");
        let array = []
        value.map(value1 => {
            array.push(value1.name)
        })

        axios
            .post(`http://localhost:8080/banners`, {
                name: name.value, price: price.value, textField: textField.value, categories: array
            }, {
                headers: {
                    'Authorization': `Bearer ${Cookies.get('token')}`,
                }
            }).then(() => {
            returnFunc()
        }).catch(error => {
            setErrorText(error.request.responseText)
            setError(true)
        });
    }

    const [data, setData] = useState([])


    useEffect(() => {
        axios
            .get(`http://localhost:8080/categories`, {
                headers: {
                    'Authorization': `Bearer ${Cookies.get('token')}`,
                }
            })
            .then(response => {
                setData(response.data)
            })
    }, [])

    let [value, setValue] = useState(data)
    const onSelect = (cat) => {
        setValue(cat)
    }

    return (
        <div>
            <BannersWindow></BannersWindow>
            <div>
                <div className={classes.square1}></div>
                <h2 style={{
                    position: "relative",
                    left: "270px",
                    bottom: "1660px",
                    width: "750px"
                }}> Create new banner </h2>
            </div>
            <div style={{width: "100px"}}>
                <h4 className={classes.textStyle}>Name</h4>
                <h4 className={classes.textStyle}>Price</h4>
                <h4 className={classes.textStyle}>Categories</h4>
                <h4 className={classes.textStyle}>TextField</h4>
            </div>

            <button className={classes.buttonsStyle} onClick={handleClickSave}>
                Save
            </button>
            <div className={classes.inputsStyle}><input id="name"
                                                        type='text'
                                                        placeholder='name'
                                                        style={{width: "500px", height: "40px"}}
            />
            </div>
            <div className={classes.inputsStyle}><input id="price"
                                                        type='text'
                                                        placeholder='price'
                                                        style={{width: "500px", height: "40px"}}
            />
            </div>
            <div>
                <Multiselect className={classes.inputsStyle} id="categories" options={data}
                             selectedValues={[]} displayValue="name" onSelect={onSelect}/>
            </div>
            <div className={classes.inputsStyle}><TextArea id="textField"
                                                           type='text'
                                                           placeholder='textField'
                                                           style={{width: "500px", height: "200px"}}
            />
            </div>
            <div style={{bottom: "2020px", left: "250px", position: "relative", color: "red", fontSize: "30px"}}>
                {isShowingErrorBlock && (errorText)}
            </div>
        </div>)


};

export default AddBanner;