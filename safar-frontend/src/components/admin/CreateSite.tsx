import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import City from '../../models/City';
import SiteDto from '../../models/dtos/SiteDto.tsx';
import CityService from '../../services/CityService.tsx'

type Props = {onCreateSite: Function}



const CreateSite = (props: Props) => {
    const [show, setShow] = useState(false);
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [cityId, setCityId] = useState(0);
    const [image, setImage] = useState("");
    const [description, setDescription] = useState("");
    const [cities, setCities] = useState<City[]>([]);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        CityService.getCities()
            .then((res) => {
                console.log("RES " + res)
                setCities(res.data)
            })
            .catch((err) => {
                console.log(err);
            })
    }, [])

    const onCreateSite = () => {
        // let city = cities.find((c: City) => {return c.name === cityName});

        let site = new SiteDto(name, address, image, description, cityId);

        props.onCreateSite(site);

        handleClose();
    }

    return (
        <>
            <Button id='btn_add_site' variant="success" onClick={handleShow}>
                Add New Site
            </Button>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                <Modal.Title>New Site</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Name</Form.Label>
                        <Form.Control
                            type="text"
                            autoFocus
                            name="name"
                            onChange={(e) => {setName(e.target.value)}} 
                            value={name}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Address</Form.Label>
                        <Form.Control
                            type="text"
                            autoFocus
                            name="address"
                            onChange={(e) => {setAddress(e.target.value)}} 
                            value={address}
                        />
                        
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>City</Form.Label>
                        <Form.Select aria-label="Default select example" 
                            onChange={(e) => {setCityId(e.target.value)}} 
                            value={cityId}>
                            {/* <option>Select a City</option> */}
                            {cities.map((city: City) => {
								return(
                                    <option key={city.id} value={city.id}>{city.name}</option>
                                )
                            })}
                        </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Image Path</Form.Label>
                        <Form.Control
                            type="text"
                            autoFocus
                            onChange={(e) => {setImage(e.target.value)}} 
                            value={image}
                        />
                    </Form.Group>
                    <Form.Group
                        className="mb-3"
                        controlId="exampleForm.ControlTextarea1"
                        >
                        <Form.Label>Description</Form.Label>
                        <Form.Control as="textarea" rows={3} 
                            onChange={(e) => {setDescription(e.target.value)}} 
                            value={description}/>
                    </Form.Group>
                </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button type="submit" variant="primary" onClick={onCreateSite}>
                        Add
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default CreateSite;