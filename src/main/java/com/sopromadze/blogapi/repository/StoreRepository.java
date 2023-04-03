package com.sopromadze.blogapi.repository;

import com.sopromadze.blogapi.model.store.Store;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findByNameAndRegionAndCity(String name, String region, String City );
}
