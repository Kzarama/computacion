package com.TP.demo.exceptions;

/**
 * Excepción para cuando el tema con Spring y grupos menores que cero 
 */
public class TSGMC extends Exception{
	
	public TSGMC(String message) {
		super(message);
	}

}
