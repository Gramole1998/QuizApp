package com.gaurav.springboot.question;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
	
	public QuestionWrapper(int id, String question, String option1, String option2, String option3, String option4) {
		super();
		this.id = id;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
	}
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
