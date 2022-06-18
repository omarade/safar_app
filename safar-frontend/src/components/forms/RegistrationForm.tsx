import React from 'react'
import { Card, CardContent, CardHeader, Button, Grid } from '@material-ui/core';
import { Form, Formik, FormikConfig, FormikValues } from 'formik';
import { Box } from '@material-ui/core';
import { Field } from 'formik';
import { TextField } from 'formik-material-ui';
import { Link, useNavigate } from 'react-router-dom';
import { withNavigation } from 'react-navigation'

import UserService from '../../services/UserService.tsx'
import UserCreateDto from '../../models/dtos/UserCreateDto.tsx';
import {RegistrationSchema} from '../../validations/RegisterValidation.js'


// class RegistrationForm extends React.Component {
const RegistrationForm = () => {
	// State = {}
	const navigate = useNavigate();
	
	const onSubmit = (values) => {
		const userCreateDto = new UserCreateDto(values.name, values.username, values.email, values.password, values.address);
		console.log(userCreateDto)
		UserService.register(userCreateDto)
		.then(res => {
            console.log(res)
			localStorage.setItem('access_token', res.data.access_token)
			localStorage.setItem('refresh_token', res.data.refresh_token)
			// this.navigate("/login");
			//window.history.pushState({}, "", "/login")
			// window.location.href = "/login"
			navigate('/login');
        })
        .catch((error) => {
            // handle error
            console.log(error);
        });
	}

	// render() {
		return (
			<Card className='form_container' id='register_container' variant="outlined" >
				<CardHeader title='Register'></CardHeader>
				<CardContent id='register_content'>
					<Formik onSubmit={(values, actions) => {
						onSubmit(values)
						actions.setSubmitting(false)
					}} initialValues={{
						name: '',
						username: '',
						email: '',
						password: '',
						password_confirmation: '',
						address: ''
					}}
					validationSchema={RegistrationSchema}
					>
						<Form>
							<Box className='input_container'>
								<Field variant="outlined" fullWidth name="name" component={TextField} label="Fullname"/>
							</Box>
							<Box className='input_container'>
								<Field variant="outlined" fullWidth name="username" component={TextField} label="Username"/>
							</Box>
							<Box className='input_container'>
								<Field variant="outlined" fullWidth name="email" component={TextField} label="Email"/>
							</Box>
							<Box className='input_container'>
								<Field variant="outlined" type="password" fullWidth name="password" component={TextField} label="Password"/>
							</Box>
							<Box className='input_container'>
								<Field variant="outlined" type="password" fullWidth name="password_confirmation" component={TextField} label="Confirm Password"/>
							</Box>
							<Box className='input_container'>
								<Field variant="outlined" fullWidth name="address" component={TextField} label="Address"/>
							</Box>

							<Grid id="btn-container" container spacing={2} justifyContent="space-between">
								<Grid item xs={4}>
									<Button component={Link} to='/login' className='frm_btn' id='login_button' fullWidth color="primary" variant="contained" type="button">Login</Button>
								</Grid>
								<Grid item xs={4}>
									<Button className='frm_btn' id='register_button' fullWidth color="primary" variant="contained" type="submit">Register</Button>
								</Grid>
							</Grid>
						</Form>
					</Formik>
				</CardContent>
			</Card>
		)
	// }
}

export default RegistrationForm;