package org.cuff.controllers;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.PropertyNamingStrategy.PropertyNamingStrategyBase;
import org.codehaus.jackson.type.TypeReference;

import org.cuff.dto.*;
import org.cuff.util.Mailer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController @RequestMapping("/rest")
public class SubmissionController {
	 
	@RequestMapping(value="/submit", method=RequestMethod.POST, consumes="application/json", produces="text/plain")
	 public ResponseEntity submitContributorInfo(@FormParam("testInput") String testInput, @FormParam("contributor") String contributor, @FormParam("subject") String subject, @FormParam("physical_appearance") String physicalAppearance, @FormParam("warrants") String warrants, @FormParam("judgments") String judgments, @FormParam("criminal_history") String criminalHistory) throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {
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
		 
		 return ResponseEntity.ok("Got it! " + testInput + contributor);
	 }
	 	 
	 
	 
}
