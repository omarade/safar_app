package nl.springboot.safar.repositories;

import nl.springboot.safar.models.Site;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FakeSiteData {
    private List<Site> sites = new ArrayList<>();

    public FakeSiteData() {
//        private int id;
//        private String name;
//        private String description;
//        private String address;
        Site a = new Site(1, "test1", "desc1", "Breda");
        Site b = new Site(2, "test2", "desc2", "Amsterdam");
        Site c = new Site(3, "test3", "desc3", "Rotterdam");
        Site d = new Site(4, "test4", "desc4", "Eindhoven");

        Collections.addAll(sites, a, b, c, d);
    }

    public List<Site> getAllSites(){
        return sites;
    }

    public Site getSiteBy(int id){
        for (Site site : sites) {
            if (site.getId() == id) {
                return site;
            }
        }
        return null;
    }
}
