package com.spaceflightnews.repository;

import com.spaceflightnews.entity.Launches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchesRepository extends JpaRepository<Launches, Integer> {
}
