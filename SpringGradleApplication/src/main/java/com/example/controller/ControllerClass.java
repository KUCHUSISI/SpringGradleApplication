package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
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

import ch.qos.logback.classic.Logger;
@CrossOrigin
@RestController
public class ControllerClass 
{
	@Autowired(required = true)
	ServiceClass service;
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
		return service.insertData(model);
	}
	
	@GetMapping("/getdetails")
	public List<MappingPojo> getAll()
	{
			return service.getAll();
	}
	
	@GetMapping("/getModel/{l}")
	public MappingPojo getModel(@PathVariable("l") int l)
	{
		return service.getModel(l);
	}
	
	// need to create pojo mapping
	@GetMapping("/getLatest")
	public MappingPojo getModellatest()
	{
		return service.findByCreatedTime();
	}
	
	@GetMapping("/Alphafirst/{A}")
	public MappingPojo getModelAlphafirst(@PathVariable("A") String A)
	{
		return service.getModelByAlpha(A);
	}
	
	@GetMapping("/AlphafirstTen/{A}")
	public List<MappingPojo> getModelAlphafirstTen(@PathVariable("A") String A)
	{
		return service.getModelsByAlpha(A);
	}
	//Check with mongo template 
	// mongo db limit and skip 
}