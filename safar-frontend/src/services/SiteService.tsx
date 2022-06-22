import axios from "axios";
import Site from "../models/Site";

const SITE_BASE_URL = "http://localhost:8080/sites";

class SiteService {

    static async getSites(cityName: string){
        console.log("SiteService")
        console.log(cityName)
        if (cityName !== "" && cityName !== undefined){

            return await axios.get(SITE_BASE_URL + "?city=" + cityName) 
        }
        console.log(axios.get(SITE_BASE_URL))
        return await axios.get(SITE_BASE_URL) 
    }

    static async createSite(site: Site) {
        return await axios.post(SITE_BASE_URL, site);
    }

    static async deleteSite(siteId: number) {
        return await axios.delete(`${SITE_BASE_URL}/${siteId}`)
    }

}

export default SiteService;