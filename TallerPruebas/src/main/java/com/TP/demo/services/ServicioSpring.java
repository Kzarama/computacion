package com.TP.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.demo.model.TsscSprint;
import com.TP.demo.repositories.IRepositorioDelJuego;
import com.TP.demo.repositories.IRepositorioSpring;

@Service
public class ServicioSpring implements IServicioSpring {

	private IRepositorioSpring sprintRepo;
	
	@Autowired
	public ServicioSpring(IRepositorioSpring sprintRepo) {
		// TODO Auto-generated constructor stub
		this.sprintRepo = sprintRepo;
	}
	
	@Override
	public void addSprint(TsscSprint game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSprint(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editSprint(Integer key, TsscSprint game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TsscSprint getSprint(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
