import React, { useState } from 'react'
import { Form, Button } from 'react-bootstrap';
import "../styles/FilterSites.css"



const FilterSites = (props) => {

	const [city, setCity] = useState("");

	const onClick =() => {
		console.log("************************************")
		console.log(city)
		props.onSitesFilter(city)
	}

	return (
		<Form id="filter_sites">
			<Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
				<Form.Label>City Name</Form.Label>
				<Form.Control onChange={(e) => {setCity(e.target.value)}} value={city} type="text" placeholder="Eg. Amsterdam" />
			</Form.Group>
			<Button onClick={onClick} variant="primary">Search</Button>
		</Form>
	)
}

export default FilterSites;
