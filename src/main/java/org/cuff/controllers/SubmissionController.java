package org.cuff.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.PropertyNamingStrategy.PropertyNamingStrategyBase;
import org.codehaus.jackson.type.TypeReference;

import org.cuff.dto.*;
import org.cuff.util.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SubmissionController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	@RequestMapping(value="/submit", method=RequestMethod.POST, produces="text/plain")
	@CrossOrigin(origins = {"http://localhost:3000", "https://cuff-ui-staging.herokuapp.com"})
	 public ResponseEntity<String> submitContributorInfo(
			 @RequestParam(value="contributor") String contributor, @RequestParam(value="subject") String subject, @RequestParam(value="physical_appearance") String physicalAppearance, @RequestParam(value="warrants") String warrants, @RequestParam(value="judgments") String judgments, @RequestParam(value="criminal_history") String criminalHistory)
					 throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {
		
		
		 ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategyBase.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		 Contributor c = mapper.readValue(contributor, Contributor.class);
		 Subject s = mapper.readValue(subject, Subject.class);
		 PhysicalAppearance pa = mapper.readValue(physicalAppearance, PhysicalAppearance.class);
		 ArrayList<Warrant> w = mapper.readValue(warrants, new TypeReference<List<Warrant>>(){});
		 ArrayList<Judgment> j = mapper.readValue(judgments, new TypeReference<List<Judgment>>(){});
		 ArrayList<CriminalHistory> ch = mapper.readValue(criminalHistory, new TypeReference<List<CriminalHistory>>(){});
		 
		 System.out.println("Contributor: " + c);
		 System.out.println("Subject: " + s);
		 System.out.println("Physical appearance: " + pa);
		 System.out.println("Warrants: " + w);
		 System.out.println("Judgments: " + j);
		 System.out.println("Criminal history: " + ch);
		 
		 System.out.println(pa.getHeight()/12 + " feet, " +pa.getHeight()%12+" inches");
		 
		 Mailer mailer = new Mailer();
		 mailer.emailSubmission(c, s, pa, w, j, ch);
		 
		 return new ResponseEntity<String>("Got it! " + contributor, HttpStatus.OK);
		 
	 }
	 	 
	 
	 
}
