package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.URLModel;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface URLDao extends JpaRepository<URLModel, UUID> {

    @Query("SELECT obj FROM URLModel obj WHERE obj.longUrl:= ")
    Optional<URLModel> findByLongUrl(String longUrl);
}
