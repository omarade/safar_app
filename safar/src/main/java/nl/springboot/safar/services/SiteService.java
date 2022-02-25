package nl.springboot.safar.services;

import nl.springboot.safar.models.Site;
import nl.springboot.safar.repositories.FakeSiteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    @Autowired
    public FakeSiteData fakeSiteData;

    public List<Site> getAllSites(){
        return fakeSiteData.getAllSites();
    }
}
