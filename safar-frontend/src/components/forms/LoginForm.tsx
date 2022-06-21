import React from 'react'
import { Card, CardContent, CardHeader, Button, Grid, Box } from '@material-ui/core';
import { Form, Formik, Field} from 'formik';
import { TextField } from 'formik-material-ui';
import { Link, useNavigate } from 'react-router-dom';

import AuthService from '../../services/AuthService.tsx';
import UserCred from '../../models/dtos/UserCred.tsx';
import { LoginSchema } from '../../validations/LoginValidation.js';
import '../../styles/Forms.css';


// class LoginForm extends React.Component {
const LoginForm = () => {	

	const navigate = useNavigate();

	const onSubmit = (values) => {
		const userCred = new UserCred(values.username, values.password);
		AuthService.login(userCred)
		.then(res => {
            console.log(res)
			localStorage.setItem('access_token', res.data.access_token)
			localStorage.setItem('refresh_token', res.data.refresh_token)
			navigate("/home");
        })
        .catch((error) => {
            // handle error
            // console.log(error);
        });

	}

	
	return (

		<Card className='form_container' id='login_container' variant="outlined" >
			<CardHeader title='Login'></CardHeader>
			<CardContent id='login_content'>
				<Formik onSubmit={(values, actions) => {
					onSubmit(values)
					actions.setSubmitting(false)
				}} initialValues={{
					username: '',
					password: ''
				}}
				validationSchema= { LoginSchema }
				>
					<Form>
						<Box className='input_container'>
							<Field variant="outlined" fullWidth name="username" component={TextField} label="Username"/>
						</Box>
						<Box className='input_container'>
							<Field type="password" variant="outlined" fullWidth name="password" component={TextField} label="Password"/>
						</Box>

						
						
						<Grid id="btn-container" container spacing={2} justifyContent="space-between">
							<Grid item xs={4}>
								<Button component={Link} to='/register' className='frm_btn' id='register_button' fullWidth color="primary" variant="contained" type="button">Register</Button>
							</Grid>
							<Grid item xs={4}>
								<Button className='frm_btn' id='login_button' fullWidth color="primary" variant="contained" type="submit">Login</Button>
							</Grid>
						</Grid>
					</Form>
				</Formik>
			</CardContent>
		</Card>
	)
	
}

export default LoginForm;