import axios from 'axios';
import jwt from 'jwt-decode'
import UserCred from '../models/dtos/UserCred.tsx';


const AUTH_API_BASE_URL = "http://localhost:8080/authenticate";

class AuthService {
    static async login(userCred: UserCred){
        console.log(userCred)
        return await axios.post(AUTH_API_BASE_URL, userCred, {headers: {'Content-Type': 'application/json'}})
    }

    static logout() {
        localStorage.clear();
    }
    
    static decodeToken() {
        let decodedToken;
        let token = localStorage.getItem("access_token");
        if(token !== null) {
            decodedToken = jwt(token);
        }
        
        return decodedToken;
    }

    static getUsername () {
        let decodeToken = this.decodeToken()
        return decodeToken.sub;
    }

    static isAdmin () {
        let decodeToken = this.decodeToken()
        if (decodeToken.role[0] === "ROLE_ADMIN") {
            return true;
        }
        return false;
    }
}

export default AuthService;