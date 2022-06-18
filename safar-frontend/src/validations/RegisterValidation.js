import * as yup from 'yup'

export const RegistrationSchema = yup.object().shape({
    name: yup.string().required('Name is required'),
    username: yup.string().required('Username is required'),
    email: yup.string().email().required('Email is required'),
    password: yup.string().required('Password is required'),
    password_confirmation: yup.string().oneOf([yup.ref('password'), null], 'Password must match').required('Password confirmation is required'),
    address: yup.string()
})
