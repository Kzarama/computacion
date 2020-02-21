package com.testNg.spring.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Order {
	
	private String id;
	private String creador;
	private LocalDate fechaCreacion;
	private String descripcion;
	
}
