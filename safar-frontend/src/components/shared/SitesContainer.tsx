import React, { useState, useEffect } from 'react'
import Site from '../../models/Site.tsx';
import SiteService from '../../services/SiteService.tsx';
import SiteCard from './SiteCard.tsx';

const SitesContainer = () => {

    const [sites, setSites] = useState<Site[]>([])

    useEffect(() => {
        SiteService.getSites()
        .then(res => {
            console.log(res.data);
            setSites(res.data as Site[]);
        })
        .catch(err => {
            console.log(err);
        })
    }, [])
    

    return (
        <div className="container card-body overflow-hidden">
            <div className="row gx-5">
            {sites.map((site: Site) => {
                return(
                    <div key={site.id} className="col-4">
                        <SiteCard site={site}></SiteCard>
                    </div>
                )
                    
                
            })}
            </div>
        </div>
    );
}

export default SitesContainer;