package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.URLModel;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface URLDao extends JpaRepository<URLModel, UUID> {

    @Query("SELECT obj FROM URLModel obj WHERE obj.longUrl = :longUrl")
    Optional<URLModel> findByLongUrl(@Param("longUrl") String longUrl);

    @Query("SELECT obj FROM URLModel obj WHERE obj.shortUrl = :shortUrl")
    Optional<URLModel> findByShortUrl(@Param("shortUrl")String shortUrl);
}
