package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.SortByCountOperation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.Model.Model;
import com.example.mapper.MappingPojo;
import com.example.repository.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Sorts;

@Service
@ComponentScan("com.example.repository")
public class ServiceClass
{
	@Autowired(required = true)
	repository repo;
	ModelMapper mapper;
	public MappingPojo getData(String firstName)
	{
		Model m=new Model();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		MappingPojo mapping=mapper.map(m, MappingPojo.class);
		return mapping;
		
		//		return repo.findByfirstName(firstName);
	}
	public void insertData(Model model)
	{
		repo.save(model);
	}
	public List<Model> getAll()
	{
		List<Model> l=new ArrayList<Model>();
		List<Model> l1=repo.findAll();
		for(Model m:l1)
		{
			l.add(m);
		}
		return l;
	}
	public Model getModel(int a)
	{
		return repo.findModelByid(a);
	}
	public List<Model> getModels()
	{
		return repo.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}

	public List<Model> findByCreatedTime(String date)
	{
		List<Model> result =  repo.findAll( Sort.by(Sort.Direction.DESC, "date")); 
		return result;
	}

	public List<Model> getModelsByAlpha(String firstName)
	{
		return repo.findAll(Sort.by(Sort.Direction.ASC,"firstName"));
	}
}

