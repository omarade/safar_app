import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { NavLink } from 'react-router-dom';
import AuthService from '../../services/AuthService.tsx';


//                     <a className="nav-link disabled">Disabled</a>

const NavBar = () => {

    const logout = () => {
        AuthService.logout();
    }

    return (
        <Navbar bg="dark" variant="dark">
            <Container fluid id="nav-con">
                <Navbar.Brand as={NavLink} to="/home">Navbar</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link as={NavLink} to="/home">Home</Nav.Link>
                    <Nav.Link as={NavLink} to="/favorites">Favorite</Nav.Link>
                    
                    {
                        AuthService.isAdmin() && (
                            <Nav.Link as={NavLink} to="/admin">Admin</Nav.Link>
                        )
                    }
                    
                </Nav>
                <Nav>
                    <Nav.Link onClick={logout} as={NavLink} to="/login">Logout</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    )
}

export default NavBar;