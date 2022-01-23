package com.bmc.emailserver.dto;

import java.util.Set;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class MessageDTO {

	private String from;
	private Set<String> recipients;
	private String subject;
	private String text;
	
	
}
