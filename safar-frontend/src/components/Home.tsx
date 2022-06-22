import React ,{ useState } from 'react'
import FilterSites from './FilterSites.tsx';
import NavBar from './shared/NavBar.tsx';
import SitesContainer from './shared/SitesContainer.tsx';

const Home = () => {

    const [city, setCity] = useState("");

    const onSitesFilter = (cityName: string) => {
        setCity(cityName);
    }

    return (
        <>
            <NavBar></NavBar>
            <FilterSites onSitesFilter={(cityName) => {onSitesFilter(cityName)}}></FilterSites>
            <SitesContainer cityName={city}></SitesContainer>
        </>
    )
}

export default Home;