import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { NavLink } from 'react-router-dom';


//                     <a className="nav-link disabled">Disabled</a>

const NavBar = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Container fluid id="nav-con">
                <Navbar.Brand as={NavLink} to="/home">Navbar</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link as={NavLink} to="/home">Home</Nav.Link>
                    <Nav.Link as={NavLink} to="/favorites">Favorite</Nav.Link>
                    <Nav.Link as={NavLink} to="/profile">Profile</Nav.Link>
                    
                </Nav>
                <Nav>
                    <Nav.Link as={NavLink} to="/login">Logout</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    )
}

export default NavBar;