package com.TP.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscStory;
import com.TP.demo.repositories.IRepositorioDelGrupo;
import com.TP.demo.repositories.IRepositorioDelJuego;
import com.TP.demo.repositories.IRespositorioHistoria;

@Service
public class ServicioHistoria implements IServicioHistoria{

	private IRespositorioHistoria storyRepo;
	private IRepositorioDelJuego gameRepo;
	
	@Autowired
	public ServicioHistoria(IRespositorioHistoria storyRepo, IRepositorioDelJuego gameRepo) {
		// TODO Auto-generated constructor stub
		this.storyRepo = storyRepo;
		this.gameRepo = gameRepo;
	}
	
	@Override
	public boolean addStory(TsscStory story) {
		if(story.getTsscGame() != null) {
			if(this.gameRepo.existsById(story.getTsscGame().getId())) {
				if(story.getBusinessValue().intValue() > 0 && story.getInitialSprint().intValue() > 0 && story.getPriority().intValue() > 0) {
					this.storyRepo.save(story);
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public void deleteStory(Long key) {
		// TODO Auto-generated method stub
		storyRepo.deleteById(key);
	}

	@Override
	public void editStory(Long key, TsscStory story) throws ElemNotExiPreviousException {
		if(this.storyRepo.findById(key).isPresent() && story != null) {
			System.out.println("Hola1");
			if(story.getTsscGame() != null) {
				System.out.println("Hola2");
				if(gameRepo.existsById(story.getTsscGame().getId())) {
					System.out.println("Hola3");
					if(story.getBusinessValue().intValue() > 0 && story.getInitialSprint().intValue() > 0 && story.getPriority().intValue() > 0) {
						this.storyRepo.save(story);			
					}						
				}
				else {
//					throw new ElementDidNotExistPreviouslyException("El TsscGame asociado no existia previamente");
				}
			}
			else {
//				throw new ElementDidNotExistPreviouslyException("No hay asociado ningun juego");
			}
		}
		else {
//			throw new ElementDidNotExistPreviouslyException("El TsscStory no existia previamente");
		}
	}

	@Override
	public TsscStory getStory(Long key) {
		return this.storyRepo.findById(key).get();
	}
	
	@Override
	public boolean exists(Long key) {
		return this.storyRepo.existsById(key);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return storyRepo.findAll();
	}

	@Override
	public TsscStory findById(Long id) {
		return storyRepo.findById(id).get();
	}

	
	
	
}
