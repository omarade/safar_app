import axios from "axios";
import Site from "../models/City";

const CITY_BASE_URL = "http://localhost:8080/cities";

class CityService {

    static async getCities(){
        return await axios.get(CITY_BASE_URL) 
    }
}

export default CityService;