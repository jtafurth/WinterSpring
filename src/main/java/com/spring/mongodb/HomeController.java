package com.spring.mongodb;

import java.io.Reader;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.mongodb.request.Request;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	ApplicationContext Context = new GenericXmlApplicationContext("MongoConfig.xml");
	
	private MongoOperations mongOps;
	
	private int ID;
	
	@PostConstruct
	public void init(){
		MongoOperations mongOps = (MongoOperations) Context.getBean("mongoTemplate");
		this.mongOps = mongOps;
		logger.info("MongoDB Initilization Completed");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getGET(Reader rawData){	
		return "home";
	}

// Source code to handle GET request on /parameter, should create a new collection and do nothing afterwards
	@RequestMapping(value = "/{Parameter}", method = RequestMethod.GET)
	public String getParameter(@PathVariable(value="Parameter") String Parameter){
		
		Request Request = new Request(Integer.toString(ID), "GET", "HeaderFixed", "BodyFixed");;
		
		if(!Parameter.isEmpty()){
			try{
				mongOps.createCollection(Parameter);
				ID = 0;
				logger.info("Collection created on parameter request");
				return "CollectionCreated";
			}
			catch(Exception Ex){
				logger.info("Collection already exists");
				mongOps.save(Request);
				logger.info(Integer.toString(ID));
				ID++;
				return "Success";
			}
		}	
		return "home";
	}
	
// Source code to handle GET request on ?inspect=1
	@RequestMapping(value = "/{Parameter}", method = RequestMethod.GET, params = {"inspect"})
	public String inspectURL(Model ViewModel, @PathVariable(value="Parameter") String Parameter, @RequestParam(value="inspect") String inspect){
			String[] CharRequest;
			CharRequest = new String[ID];
			logger.info(Long.toString(ID));
			int j = 0;
			try{
				for(int i=ID-10;i<=ID;i++){
					Request savedRequest = mongOps.findOne(new Query(Criteria.where("documentID").is(Integer.toString(i))), Request.class);
					CharRequest[j] = savedRequest.toString();
					j++;
				}
			}
			catch(NullPointerException Ex){
				for(int i=0;i<ID;i++){
					Request savedRequest = mongOps.findOne(new Query(Criteria.where("documentID").is(Integer.toString(i))), Request.class);
					CharRequest[i] = savedRequest.toString();
				}
			}
			ViewModel.addAttribute("CharRequest", CharRequest);
			logger.info("Rendering of request completed");
			return "Requested";
	}
}

