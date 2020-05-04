package com.TP.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.demo.model.TsscTimecontrol;
import com.TP.demo.repositories.IRepositorioDelCronometro;

@Service
public class ServicioCronometro implements IServicioCronometro {
	
	private IRepositorioDelCronometro cronometerRepo;
	
	@Autowired
	public ServicioCronometro(IRepositorioDelCronometro cronometerRepo) {
		// TODO Auto-generated constructor stub
		this.cronometerRepo = cronometerRepo;
	}

	@Override
	public void addCronometer(TsscTimecontrol cronometer) {
		// TODO Auto-generated method stub
		System.out.println(this.cronometerRepo);
		
	}

	@Override
	public void deleteCronometer(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCronometer(Integer key, TsscTimecontrol cronometer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TsscTimecontrol getCronometer(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
