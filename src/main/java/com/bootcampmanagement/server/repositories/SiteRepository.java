package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SiteRepository extends JpaRepository<Site, Integer>{
    //Total Site
    @Query(value="SELECT count(*) FROM site",nativeQuery = true)
    int totalSite();
}
