package com.TP.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.TP.demo.model.TsscAdmin;
import com.TP.demo.model.TsscGame;
import com.TP.demo.repositories.IRepositorioDelCronometro;
import com.TP.demo.repositories.IRepositorioDelJuego;
import com.TP.demo.services.IServicioCronometro;
import com.TP.demo.services.ServicioAdministrador;
import com.TP.demo.services.ServicioJuego;

@SpringBootApplication
@ComponentScan(basePackages = {"com.TP.demo"})
public class TPApplication {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(TPApplication.class, args);
		
		ServicioJuego m = context.getBean(ServicioJuego.class);
		
		TsscGame g1 = new TsscGame();
		TsscGame g2 = new TsscGame();
		TsscGame g3 = new TsscGame();
		
		g1.setName("Hello1");
		g2.setName("Halo2");
		g3.setName("Halloooo3");
		
		m.getGameRepo().save(g1);
		m.getGameRepo().save(g2);
		m.getGameRepo().save(g3);
		
		System.out.println(g1.getId());
		System.out.println(g2.getId());
		System.out.println(g3.getId());		
		
		ServicioAdministrador admin = context.getBean(ServicioAdministrador.class);
		
		TsscAdmin admin1 = new TsscAdmin();
		admin1.setUser("Cristian");
		admin1.setPassword("{noop}1234");
		admin1.setSuperAdmin("S");
		admin.guardarAdministrador(admin1);
		TsscAdmin admin2 = new TsscAdmin();
		admin2.setUser("Tamayo");
		admin2.setPassword("{noop}1234");
		admin2.setSuperAdmin("A");
		admin.guardarAdministrador(admin2);
		
	}
	
//	@Bean        
//	public CommandLineRunner runner(){          
//		return args -> {
//			
//		};
//	}
	
	
}
