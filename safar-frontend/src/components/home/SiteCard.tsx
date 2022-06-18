import React from 'react'

import '../../styles/SiteCard.css'


const SiteCard = () => {



    return (
        // <div>SiteCard</div>
        <div className="card">
            <img src="https://www.iamsterdam.com/media/locations-ndtrc/museums/rijksmuseum-john-lewis-marshall.jpg?w=977" className="card-img-top" alt="..." />
            
            <div className="container card-body">
                <div className="row">
                    <div className="col">
                        <h5 className="card-title">Site Name</h5>
                        <p className="card-text">Location</p>
                    </div>
                    <div className="col">
                        <i className="icon bi bi-heart"></i>
                    </div>
                </div>
            </div>
        </div>
       
    )
}

export default SiteCard