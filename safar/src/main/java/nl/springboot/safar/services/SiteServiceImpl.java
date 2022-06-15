package nl.springboot.safar.services;

import lombok.RequiredArgsConstructor;
import nl.springboot.safar.models.Site;
import nl.springboot.safar.repositories.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;

    public List<Site> findAll(){
        return siteRepository.findAll();
    }

    public Optional<Site> findById(Integer id){
        return siteRepository.findById(id);
    }

    public Site create(Site site){
       return siteRepository.save(site);
    }
}
