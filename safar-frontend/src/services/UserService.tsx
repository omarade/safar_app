import axios from "axios";
import UserCreateDto from "../models/dtos/UserCreateDto.tsx";

const USER_BASE_URL = "http://localhost:8080/users";

class UserService {



    getUsers(){
        // console.log(axios.get(USER_BASE_URL))
        return axios.get(USER_BASE_URL) 
    }

    static async register(userCreateDto: UserCreateDto){
        return await axios.post(USER_BASE_URL + "/register", userCreateDto, {headers: {'Content-Type': 'application/json'}})
    }

    
}

export default UserService;