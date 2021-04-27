package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Model;
import com.example.mapper.MappingPojo;
import com.example.service.ServiceClass;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
@CrossOrigin
@RestController
public class ControllerClass 
{
	@Autowired(required = true)
	ServiceClass service;
	ObjectMapper mapper=new ObjectMapper();
	Logger logger=(Logger) LoggerFactory.getLogger(ControllerClass.class);
	@RequestMapping("/")
    public String index() {
		
		 logger.trace("A TRACE Message");
	        logger.debug("A DEBUG Message");
	        logger.info("An INFO Message");
	        logger.warn("A WARN Message");
	        logger.error("An ERROR Message");
        return "Howdy! Check out the Logs to see the output...";
    }

	
	@GetMapping("/getdetails/{firstName}")
	public MappingPojo getDetails(@PathVariable("firstName") String firstName)
	{
		
		return service.getData(firstName);
	}
	@PostMapping("/saveModel")
	public Model saveModel(@RequestBody Model model)
	{
		service.insertData(model);
		return model;
	}
	@SuppressWarnings("finally")
	@GetMapping("/getdetails")
	public List<Model> getAll()
	{
		try {
		List<Model> l=service.getAll();
		for(Model m1:l)
		{
			Model m=new Model();
			ObjectMapper mapper=new ObjectMapper();
			 String jsonStr =mapper.writeValueAsString(m1);
			 logger.info(jsonStr);
		}
		}
		finally {
			return service.getAll();
		}
	}
	@GetMapping("/getModel/{l}")
	public Model getModel(@PathVariable("l") int l)
	{
		return service.getModel(l);
	}
	@GetMapping("/getLatest/{l}")
	public Model getModellatest(@PathVariable("l") String l)
	{
		List<Model> l1=service.findByCreatedTime(l);
		return l1.get(0);
	}
	@GetMapping("/Alphafirst/{A}")
	public Model getModelAlphafirst(@PathVariable("A") String A)
	{
		List<Model> l1=service.getModelsByAlpha(A);
		return l1.get(0);
	}
	@GetMapping("/AlphafirstTen/{A}")
	public List<Model> getModelAlphafirstTen(@PathVariable("A") String A)
	{
		List<Model> l1=service.getModelsByAlpha(A);
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
}