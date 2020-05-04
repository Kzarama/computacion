package com.TP.demo.services;

import com.TP.demo.model.TsscTimecontrol;

public interface IServicioCronometro {

	public void addCronometer(TsscTimecontrol cronometer);
	
	public void deleteCronometer(Integer key);
	
	public void editCronometer(Integer key, TsscTimecontrol cronometer);
	
	public TsscTimecontrol getCronometer(Integer key);
	
}
