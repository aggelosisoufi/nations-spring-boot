package com.isoufi.angelos.nationservice.repository.mariadb;

import com.isoufi.angelos.nationservice.entity.mariadb.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}