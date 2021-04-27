package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.Model.Model;

@Repository
@Component
public interface repository extends MongoRepository<Model, String> 
{
	
	Model findByfirstName(String firstName);
	// Fetch latest document by created time.
	// Fetch first document after ordering by First name alphabetically
	// Fetch first ten document after ordering by First name alphabetically

		@Query(value = "{id : ?0}")
		Model findModelByid(int id);
}