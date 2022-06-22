import axios from "axios";

const SITE_BASE_URL = "http://localhost:8080/sites";

class SiteService {

    static async getSites(cityName: string){
        console.log("SiteService")
        console.log(cityName)
        if (cityName !== ""){

            return await axios.get(SITE_BASE_URL + "?city=" + cityName) 
        }
        console.log(axios.get(SITE_BASE_URL))
        return await axios.get(SITE_BASE_URL) 
    }

}

export default SiteService;