package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
	Logger logger=(Logger) LoggerFactory.getLogger(ControllerClass.class);
	ModelMapper mapper=new ModelMapper();
	public @ResponseBody MappingPojo getData(String firstName)  
	{
		MappingPojo mapping=new MappingPojo();
		 mapping=repo.findByfirstName(firstName);
		 return mapping;
	}
	public Model insertData(Model model)
	{
		repo.save(model);
		return model;
	}
	public List<MappingPojo> getAll()
	{ 
		List<Model> list =repo.findAll();
		List<MappingPojo> l1=new ArrayList<MappingPojo>();
		
		for(Model m:list)
		{
			MappingPojo map= mapper.map(m, MappingPojo.class);
			l1.add(map);
		}
		return l1;
		
	}
	public @ResponseBody MappingPojo getModel(int a)
	{
		MappingPojo mapping=new MappingPojo();
		mapping=repo.findModelByid(a);
		return mapping ;
	}
	public List<Model> getModels()
	{
		return repo.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}

	public MappingPojo findByCreatedTime()
	{
		List<Model> result = repo.findAll( Sort.by(Sort.Direction.DESC, LocalDate.now().toString())); 
		Model model=result.get(result.size()-1);
		MappingPojo map=mapper.map(model, MappingPojo.class);
		return map;
	}
	public MappingPojo getModelByAlpha(String firstName)
	{
		 Model model = repo.findAll(Sort.by(Sort.Direction.ASC,"firstName")).get(0);
			MappingPojo map= mapper.map(model, MappingPojo.class);
		 return map;
	}
	public List<MappingPojo> getModelsByAlpha(String firstName)
	{
		List<Model> l1=repo.findAll(Sort.by(Sort.Direction.ASC,"firstName"));
		List<MappingPojo> l2=new ArrayList<MappingPojo>();
		int count=0;
		for(Model m:l1)
		{ 
			if(count<10)
			{
				MappingPojo map= mapper.map(m, MappingPojo.class);
				l2.add(map);
				count++;
			}
			else
			{
				break;
			}
		}
		return l2;
	}
	public Model findModelBydateLesserThan(String date)
	{
		List<Model> l=repo.findBydateLessThan(date);
		return l.get(l.size()-1);
	}
	
	public List<MappingPojo> findTop10ByOrderByfirstNameAsc()
	{
		return repo.findFirst10ByOrderByFirstNameAsc();
	}
	
	public List<MappingPojo> findByFirstnameStartingWith(String A)
	{
		return repo.findByFirstNameStartingWith(A);
	}

	public List<MappingPojo> findFirstByOrderByFirstNameDesc()
	{
		return repo.findFirstByOrderByFirstNameDesc();
	}
	public List<MappingPojo> findFirstByOrderByFirstNameAsc()
	{
		return repo.findFirstByOrderByFirstNameAsc();
	}
	
	
	// customsing by using post not by using get method
}