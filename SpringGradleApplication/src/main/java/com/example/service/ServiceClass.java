package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Model.Model;
import com.example.controller.ControllerClass;
import com.example.mapper.MappingPojo;
import com.example.repository.repository;

import ch.qos.logback.classic.Logger;

@Service
@ComponentScan("com.example.repository")
public class ServiceClass 
{
	@Autowired(required = true)
	repository repo;
	@Autowired
	MongoTemplate mongo;

	Logger logger=(Logger) LoggerFactory.getLogger(ControllerClass.class);

	public @ResponseBody MappingPojo getData(String firstName)  
	{
		MappingPojo mapping=new MappingPojo();
		 mapping=repo.findByfirstName(firstName);
		 return mapping;
	}
	public void insertData(Model model)
	{
		repo.save(model);
	}
	public List<Model> getAll()
	{ 
		return repo.findAll();
	}
	public MappingPojo getModel(int a)
	{
		MappingPojo mapping=new MappingPojo();
		mapping=repo.findModelByid(a);
		return mapping ;
	}
	
	public List<Model> getModels()
	{
		return repo.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}

	public Model findByCreatedTime()
	{
		List<Model> result =  repo.findAll( Sort.by(Sort.Direction.DESC, LocalDate.now().toString())); 
		return result.get(result.size()-1);
	}
	public Model getModelByAlpha(String firstName)
	{
		 return repo.findAll(Sort.by(Sort.Direction.ASC,"firstName")).get(0);
	}
	public List<Model> getModelsByAlpha(String firstName)
	{
		List<Model> l1=repo.findAll(Sort.by(Sort.Direction.ASC,"firstName"));
		List<Model> l2=new ArrayList<Model>();
		int count=0;
		for(Model m:l1)
		{ 
			if(count<10)
			{
				l2.add(m);
				count++;
			}
			else
			{
				break;
			}
		}
		return l2;
	}
	// customsing by using post not by using get method
}