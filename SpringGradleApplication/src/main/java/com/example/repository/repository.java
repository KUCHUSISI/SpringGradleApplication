package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.Model.Model;
import com.example.mapper.MappingPojo;

@Repository
@Component
public interface repository extends MongoRepository<Model, String> 
{
	
	MappingPojo findByfirstName(String firstName);
	// Fetch latest document by created time.
	// Fetch first document after ordering by First name alphabetically
	// Fetch first ten document after ordering by First name alphabetically

		@Query(value = "{id : ?0}")
		MappingPojo findModelByid(int id);
		public List<Model> findBydateLessThan(String date);
//		public List<Model> findByOrderByfirstNameAsc();
		public List<Model> findFirst10ByOrderByLastnameAsc();
//		public List<Model> findAllByTop10ByOrderByfirstNameAsc();
}