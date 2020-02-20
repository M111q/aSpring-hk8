package com.github.m111q.homework8.repository;

import com.github.m111q.homework8.model.CityTempInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<CityTempInfo, Long> {
}
