import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';


//                     <a className="nav-link disabled">Disabled</a>

 const NavBar = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Container fluid id="nav-con">
                <Navbar.Brand as={Link} to="/home">Navbar</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link className='active' as={Link} to="/home">Home</Nav.Link>
                    <Nav.Link as={Link} to="/home">Favorite</Nav.Link>
                    <Nav.Link as={Link} to="/home">Profile</Nav.Link>
                    
                </Nav>
                <Nav>
                    <Nav.Link as={Link} to="/login">Logout</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    )
}

export default NavBar;