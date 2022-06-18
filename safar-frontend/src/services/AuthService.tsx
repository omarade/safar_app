import axios from 'axios';
import UserCred from '../models/dtos/UserCred.tsx';

const AUTH_API_BASE_URL = "http://localhost:8080/authenticate";

class AuthService {
    static async login(userCred: UserCred){
        return await axios.post(AUTH_API_BASE_URL, userCred, {headers: {'Content-Type': 'application/json'}})
    }
}

export default AuthService;