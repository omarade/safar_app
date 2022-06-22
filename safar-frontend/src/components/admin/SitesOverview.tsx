import React, { useEffect, useState } from 'react'
import Table from 'react-bootstrap/Table';

import { Form, Button } from 'react-bootstrap';
import SiteService from '../../services/SiteService.tsx';
import NavBar from '../shared/NavBar.tsx';
import Site from '../../models/Site';
import CreateSite from './CreateSite.tsx';
import '../../styles/Admin.css';



type Props = {}

const SitesOverview = (props: Props) => {
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

	const onCreateSite = (site: Site) => {
		SiteService.createSite(site)
			.then((res) => {
				// setSites([...sites, site])

				SiteService.getSites()
					.then(res => {
						console.log(res.data);
						setSites(res.data as Site[]);
					})
					.catch(err => {
						console.log(err);
					})
			})
			.catch((err) => {
				console.log(err);
			})
	}

	const onDeleteSite = (siteId: number) => {
		SiteService.deleteSite(siteId)
			.then((res) => {
				SiteService.getSites()
					.then(res => {
						console.log(res.data);
						setSites(res.data as Site[]);
					})
					.catch(err => {
						console.log(err);
					})
			})
			.catch((err) => {
				console.log(err);
			})
	}

	return (
		<>
			<NavBar></NavBar>

			<div id="site_overview">
				<CreateSite onCreateSite={onCreateSite}></CreateSite>

				<Table striped bordered hover >
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Address</th>
							<th>Description</th>
							<th>City</th>
							<th>Image</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						
							{sites.map((site: Site) => {
								return(
									<tr key={site.id}>
										<td>{site.id}</td>
										<td>{site.name}</td>
										<td>{site.address}</td>
										<td>{site.description}</td>
										<td>{site.city.name}</td>
										<td><img  className="site_image" src={site.imgPath} /></td>
										<td><Button variant="warning"><i className="bi bi-pencil-square"></i></Button></td>
										<td><Button onClick={() => {onDeleteSite(site.id)}} variant="danger"><i className="bi bi-trash"></i></Button></td>
									</tr>
								)
							})} 
					</tbody>
				</Table>

			</div>
			
		</>
	)
}

export default SitesOverview;