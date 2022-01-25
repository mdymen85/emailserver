package com.bmc.emailserver.application.dto;

import java.util.Set;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class MessageDTO {

	private String from;
	private Set<String> to;
	private String subject;
	private String body;
	
	
}
