import React from 'react'

import '../../styles/SiteCard.css'


const SiteCard = (props) => {




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
                        <i className="icon bi bi-heart"></i>
                    </div>
                </div>
            </div>
        </div>
       
    )
}

export default SiteCard