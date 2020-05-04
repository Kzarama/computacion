package com.TP.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.demo.model.TsscAdmin;
import com.TP.demo.repositories.IAdminRepository;

@Service
public class ServicioAdministrador implements IServicioAdministrador {
	
	@Autowired
	private IAdminRepository repositorio;
	
	@Override
	public void guardarAdministrador(TsscAdmin admin) {
		repositorio.save(admin);
	}

	@Override
	public TsscAdmin encontrarPorUsuario(String user) {
		return repositorio.findByUser(user).get(0);
	}

}
