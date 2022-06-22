import axios from "axios";
import UserCreateDto from "../models/dtos/UserCreateDto.tsx";
import AuthService from "./AuthService.tsx";

const USER_BASE_URL = "http://localhost:8080/users";

class UserService {



    getUsers(){
        // console.log(axios.get(USER_BASE_URL))
        return axios.get(USER_BASE_URL) 
    }

    static getFavoriteSites(){
        // console.log(axios.get(USER_BASE_URL))
        let username = AuthService.getUsername()
        return axios.get(`${USER_BASE_URL}/${username}/favorites`);

    }

    static async register(userCreateDto: UserCreateDto){
        return await axios.post(USER_BASE_URL + "/register", userCreateDto, {headers: {'Content-Type': 'application/json'}})
    }

    static async toggleFavoriteSite(siteId: number){
        let username = AuthService.getUsername()
        return await axios.post(`${USER_BASE_URL}/${username}/favorites/${siteId}`)
    }
    
}

export default UserService;