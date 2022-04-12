package nl.springboot.safar.services;

import nl.springboot.safar.models.Site;
import nl.springboot.safar.repositories.FakeSiteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    public final FakeSiteData fakeSiteData;

    public SiteService(FakeSiteData fakeSiteData) {
        this.fakeSiteData = fakeSiteData;
    }

    public List<Site> getAllSites(){
        return fakeSiteData.getAllSites();
    }

    public Site getSiteBy(int id){
        return fakeSiteData.getSiteBy(id);
    }
}
