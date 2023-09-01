package com.springboot.mini.data.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.springboot.mini.data.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    @Override
    ArrayList<Review> findAll();
}
