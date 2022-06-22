import React, { useState, useEffect } from 'react'
import UserFavoriteSitesDto from '../models/dtos/UserFavoriteSitesDto';
import Site from '../models/Site.tsx';
import UserService from '../services/UserService.tsx';
import NavBar from './shared/NavBar.tsx';
import SiteCard from './shared/SiteCard.tsx';

const FavoriteSites = (props) => {

	const [userFavoriteSites, setUserFavoriteSites] = useState<UserFavoriteSitesDto>({favoriteSites: []})

	useEffect(() => {
		UserService.getFavoriteSites()
			.then(res => {
				console.log(res.data);
				setUserFavoriteSites(res.data as UserFavoriteSitesDto);
			})
			.catch(err => {
				console.log(err);
			})

	}, [])

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
            })
            .catch(err => {
				console.log(err);
			})
        
    }
	

	return (
		<>
			<NavBar></NavBar>

			<div className="container card-body overflow-hidden">
				<div className="row gx-5">
					{userFavoriteSites.favoriteSites.map((site: Site) => {
						return(
							<div key={site.id} className="col-4">
								<SiteCard onToggleFavorite={onToggleFavorite} isFavorite={true}  site={site}></SiteCard>
							</div>
						)
					})}
				</div>
			</div>
		</>
	);
}

export default FavoriteSites;