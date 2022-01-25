package com.bmc.emailserver.application.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Usuario {
	
    private String usuario;
    private String senha;
}