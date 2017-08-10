package com.potluck.group1.repository;

import com.potluck.group1.models.PotluckGuest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PotluckRepo extends CrudRepository<PotluckGuest, Long>{


List<PotluckGuest> findByDishTitleContaining(String s);

    }


