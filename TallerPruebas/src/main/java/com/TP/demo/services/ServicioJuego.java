package com.TP.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.demo.exceptions.ElemIdAlrExistException;
import com.TP.demo.exceptions.ElemNoExiException;
import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.repositories.IRepositorioDelCronometro;
import com.TP.demo.repositories.IRepositorioDelJuego;
import com.TP.demo.repositories.IRepositorioTema;

@Service
public class ServicioJuego implements IServicioJuego {

	@Autowired
	private IRepositorioDelJuego gameRepo;
	@Autowired
	private IRepositorioTema topicRepo;
	
	public IRepositorioDelJuego getGameRepo() {
		return gameRepo;
	}

	public void setGameRepo(IRepositorioDelJuego gameRepo) {
		this.gameRepo = gameRepo;
	}

	@Override
	public boolean addGame(TsscGame game){
		if(game.getNGroups() > 0 && game.getNSprints() > 0) {
			if(game.getTsscTopic() != null) {
				if(topicRepo.existsById(game.getTsscTopic().getId())) {
					game.setTsscTopic(game.getTsscTopic());						
					//game.setTsscTimecontrol(game.getTsscTopic().getTsscTimesControls());
					gameRepo.save(game);
					return true;
				}
				else {
					return false;
				}
			}
			else {
				gameRepo.save(game);
				return true;
			}
		}
		else {
			return false;
		}	
	}

	@Override
	public void deleteGame(Long key) {
		// TODO Auto-generated method stub
		gameRepo.deleteById(key);
	}

	@Override
	public void editGame(Long key, TsscGame game) throws ElemNotExiPreviousException {
		if(this.gameRepo.findById(key).isPresent() && game != null) {
			if(game.getTsscTopic() != null) {
				if(this.gameRepo.findById(game.getTsscTopic().getId()).isPresent()) {
					if(game.getNGroups() > 0 && game.getNSprints() > 0) {
						this.gameRepo.save(game);				
					}
				}
				else {
//					throw new ElementDidNotExistPreviouslyException("El TsscTopic relacionado este TsscGame no existia previamente");					
				}
			}
			else {
				if(game.getNGroups() > 0 && game.getNSprints() > 0) {
					this.gameRepo.save(game);				
				}				
			}
		}
		else {
//			throw new ElementDidNotExistPreviouslyException("El TsscGame no existia previamente");
		}
	}

	@Override
	public TsscGame getGame(Long key) {
		return gameRepo.findById(key).get();
	}

	@Override
	public boolean exists(Long key) {
		return gameRepo.existsById(key);
	}

	@Override
	public Iterable<TsscGame> findAll() {
		return gameRepo.findAll();
	}

	@Override
	public TsscGame findById(Long id) {
		return gameRepo.findById(id).get();
	}
	


	
	
}
