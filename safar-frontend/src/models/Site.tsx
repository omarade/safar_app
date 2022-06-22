import City from "./City";

class Site {

// city: null

    constructor (public id: number, public name: string, public address: string, public imgPath: string, public description: string, public city: City) {

    }

}

export default Site;