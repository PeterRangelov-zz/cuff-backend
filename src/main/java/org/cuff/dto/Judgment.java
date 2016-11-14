package org.cuff.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @XmlRootElement @AllArgsConstructor @NoArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class Judgment {
	private String municipality;
	private String amount;
	private String judgmentNumber;
	private String month;
	private String year;
	
	

}
