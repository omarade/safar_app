import axios from "axios";

const USER_BASE_URL = "http://localhost:8080/users";

class UserService {
    getUsers(){
       return axios.get(USER_BASE_URL) 
    }
}

export default new UserService();