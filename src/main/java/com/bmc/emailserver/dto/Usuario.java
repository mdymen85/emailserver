package com.bmc.emailserver.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Usuario {
	
    private String usuario;
    private String senha;
}