package com.bootcampmanagement.server.services;

import com.bootcampmanagement.server.basic.service.BasicCrudService;
import com.bootcampmanagement.server.entities.Site;
import com.bootcampmanagement.server.repositories.SiteRepository;
import org.springframework.stereotype.Service;

@Service
public class SiteService extends BasicCrudService<Site, Integer>{
    
    private SiteRepository siteRepo;

    public SiteService(SiteRepository siteRepo) {
        super(siteRepo);
        this.siteRepo = siteRepo;
    }
    
    @Override
    public String dataNotFoundMessage() {
        return "Site data not found";
    }
    
    @Override
    public String dataAlreadyExistMessage() {
        return " Site data already exist";
    }
    
}