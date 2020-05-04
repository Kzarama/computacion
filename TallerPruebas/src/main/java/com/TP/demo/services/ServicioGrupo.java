package com.TP.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.demo.model.TsscGroup;
import com.TP.demo.repositories.IRepositorioDelGrupo;
import com.TP.demo.repositories.IRepositorioDelJuego;

@Service
public class ServicioGrupo implements IServicioGrupo {

	
	private IRepositorioDelGrupo groupRepo;
	
	@Autowired
	public ServicioGrupo(IRepositorioDelGrupo groupRepo) {
		// TODO Auto-generated constructor stub
		this.groupRepo = groupRepo;
	}

	
	@Override
	public void addGroup(TsscGroup game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGroup(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editGroup(Integer key, TsscGroup game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TsscGroup getGroup(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
