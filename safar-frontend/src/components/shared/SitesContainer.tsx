import React, { useState, useEffect } from 'react'
import UserFavoriteSitesDto from '../../models/dtos/UserFavoriteSitesDto.tsx';
import Site from '../../models/Site.tsx';
import SiteService from '../../services/SiteService.tsx';
import UserService from '../../services/UserService.tsx';
import SiteCard from './SiteCard.tsx';

const SitesContainer = (props) => {

    const [sites, setSites] = useState<Site[]>([])
    const [userFavoriteSites, setUserFavoriteSites] = useState<UserFavoriteSitesDto>({favoriteSites: []})

    useEffect(() => {
        SiteService.getSites(props.cityName)
        .then(res => {
            console.log(res.data);
            setSites(res.data as Site[]);
        })
        .catch(err => {
            console.log(err);
        })

        UserService.getFavoriteSites()
			.then(res => {
				console.log(res.data);
				setUserFavoriteSites(res.data);
			})
			.catch(err => {
				console.log(err);
			})

    }, [props.cityName])
    
    const onToggleFavorite = (site) => {

        UserService.toggleFavoriteSite(site.id)
            .then(() => {
                UserService.getFavoriteSites()
                    .then(res => {
                        console.log(res.data);
                        setUserFavoriteSites(res.data);
                    })
                    .catch(err => {
                        console.log(err);
                    })
        //         if(userFavoriteSites.favoriteSites.some(fSite => fSite.id === site.id)){
        //             // remove site
        //             let newFavSites = userFavoriteSites.favoriteSites.filter((favSite) => {
        //                 return favSite.id !== site.id;
        //             })
        
        //             // update state
        //             let userFavSites = new UserFavoriteSitesDto(newFavSites);
        //             setUserFavoriteSites(userFavSites);
        //         }
        //         else {
        //             let favorites = userFavoriteSites.favoriteSites;
        //             // add site
        //             let newFavSites = favorites.push(site);
        
        //             // update state
        //             let userFavSites = new UserFavoriteSitesDto(newFavSites);
        //             setUserFavoriteSites(userFavSites);
        //         }
            })
            .catch(err => {
				console.log(err);
			})
        
    }

    return (
        <div className="container card-body overflow-hidden">
            <div className="row gx-5">
            {sites.map((site: Site) => {
                if(userFavoriteSites.favoriteSites.some(fSite => fSite.id === site.id)){
                    return(
                        <div key={site.id} className="col-4">
                            <SiteCard onToggleFavorite={onToggleFavorite} isFavorite={true} site={site}></SiteCard>
                        </div>
                    )
                } else {
                    return(   
                        <div key={site.id} className="col-4">
                            <SiteCard onToggleFavorite={onToggleFavorite} isFavorite={false} site={site}></SiteCard>
                        </div>
                    )
                }
                        
            })}
            </div>
        </div>
    );
}

export default SitesContainer;