package com.gaurav.springboot.question;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("question")
	private String question;
	@JsonProperty("option1")
	private String option1;
	@JsonProperty("option2")
	private String option2;
	@JsonProperty("option3")
	private String option3;
	@JsonProperty("option4")
	private String option4;
}
