package com.bootcampmanagement.server.controllers;

import com.bootcampmanagement.server.basic.EntityNameMessage;
import com.bootcampmanagement.server.basic.models.ResponseData;
import com.bootcampmanagement.server.basic.models.ResponseListData;
import com.bootcampmanagement.server.basic.models.ResponseMessage;
import com.bootcampmanagement.server.entities.Site;
import com.bootcampmanagement.server.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bootcamp-management/site")
public class SiteController implements EntityNameMessage{
    
    @Autowired
    SiteService siteService;
    
    @GetMapping("")
    public ResponseListData getAllSite() {
        return new ResponseListData(siteService.findAll());
    }
    
    @GetMapping("{id}")
    public ResponseData getSiteById(@PathVariable Integer id) {
        return new ResponseData(siteService.findById(id));
    }
    
    @PostMapping("")
    public ResponseMessage addSite(@RequestBody Site site) {
        return new ResponseMessage(siteService.create(site), getEntityName() + " created");
    }
    
    @PutMapping("{id}")
    public ResponseMessage updateSite(@RequestBody Site site, @PathVariable Integer id) {
        site.setId(id);
        return new ResponseMessage(siteService.update(site, id), getEntityName() + " updated");
    }
    
    @DeleteMapping("{id}")
    public ResponseMessage deleteSite(@PathVariable Integer id) {
        return new ResponseMessage(siteService.delete(id), getEntityName() + " deleted");
    }
    
    @Override
    public String getEntityName() {
        return "Site";
    }   
}
