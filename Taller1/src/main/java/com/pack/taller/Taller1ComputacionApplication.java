package com.pack.taller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.pack.taller.model.TsscAdmin;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;
import com.pack.taller.service.AdminService;
import com.pack.taller.service.GameService;
import com.pack.taller.service.GameServiceImp;
import com.pack.taller.service.TopicServiceImp;

@SpringBootApplication
public class Taller1ComputacionApplication {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Taller1ComputacionApplication.class, args);
		AdminService ad = c.getBean(AdminService.class);
		
		TsscAdmin ad1 = new TsscAdmin();
		ad1.setUser("1");
		ad1.setPassword("{noop}1");
		ad1.setSuperAdmin("superadmin");
		ad.saveAdmin(ad1);
		TsscAdmin ad2 = new TsscAdmin();
		ad2.setUser("2");
		ad2.setPassword("{noop}2");
		ad2.setSuperAdmin("admin");
		ad.saveAdmin(ad2);
		
		TopicServiceImp gs = c.getBean(TopicServiceImp.class);
		TsscTopic t1 = new TsscTopic();
		t1.setName("tema1");
		t1.setDescription("descripcion del tema 1");
		t1.setDefaultGroups(1);
		t1.setDefaultSprints(1);
		t1.setGroupPrefix("grupo");
		try {
			gs.saveTopic(t1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GameServiceImp gameservice = c.getBean(GameServiceImp.class);
		TsscGame game = new TsscGame();
		try {
			gameservice.saveGame(game);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
