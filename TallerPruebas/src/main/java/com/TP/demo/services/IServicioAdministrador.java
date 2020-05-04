package com.TP.demo.services;

import com.TP.demo.model.TsscAdmin;

public interface IServicioAdministrador {
	
	public void guardarAdministrador(TsscAdmin admin);
	public TsscAdmin encontrarPorUsuario(String user);
	
}
