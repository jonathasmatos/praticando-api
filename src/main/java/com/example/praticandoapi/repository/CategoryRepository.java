package com.example.praticandoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.praticandoapi.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	

}
