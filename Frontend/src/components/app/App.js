import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import AddCategory from "../categories/AddCategory";
import UpdateCategory from "../categories/UpdateCategory";
import AddBanner from "../banners/AddBanner";
import BannersWindow from "../banners/BannersWindow";
import UpdateBanner from "../banners/UpdateBanner";
import Auth from "../authorization/Auth";
import CategoryWindow from "../categories/CategoryWindow";


const App = () => {

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path ="/" element = {<Auth/>}/>
                    <Route path="/banners" element={<BannersWindow/>}/>
                    <Route path="/categories" element ={<CategoryWindow/>}/>
                    <Route path="/categories/create_category" element={<AddCategory/>}/>
                    <Route path="/categories/update_category/:id/:name/:requestId" element={<UpdateCategory/>}/>
                    <Route path="/banners/create_banner" element={<AddBanner/>}/>"
                    <Route path="/banners/update_banner/:id/:name/:price/:textField/:categories" element={<UpdateBanner/>}/>"
                </Routes>
            </BrowserRouter>
        </div>
    );

};

export default App



