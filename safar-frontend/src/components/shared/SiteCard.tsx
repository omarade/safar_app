import React from 'react'
import UserService from '../../services/UserService.tsx'

import '../../styles/SiteCard.css'


const SiteCard = (props) => {
    const onToggleFavorite = () => {
        console.log("toggle favorite");
        console.log(props.site)
        props.onToggleFavorite(props.site)
    }


    return (
        // <div>SiteCard</div>
        <div className="card site-card">
            <img src={props.site.imgPath} className="card-img-top site-img" alt="..." />
            
            <div className="container card-body">
                <div className="row">
                    <div className="col-10">
                        <h5 className="card-title">{props.site.name}</h5>
                        <p className="card-text">{props.site.address}</p>
                    </div>
                    <div className="col-2">
                        {props.isFavorite}
                        <i onClick={() => { onToggleFavorite() }} className={`icon bi ${props.isFavorite ? "bi-heart-fill" : "bi-heart"}`}></i>
                    </div>
                </div>
            </div>
        </div>
        //className={`banner ${active ? "active" : ""}`}
       
    )
}

export default SiteCard