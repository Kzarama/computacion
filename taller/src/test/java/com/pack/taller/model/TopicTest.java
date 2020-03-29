package com.pack.taller.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pack.taller.boot.TallerApplication;

import co.edu.icesi.fi.ci.junit5exercise.Junit5AdvancedApplication;
import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TallerApplication.class)
public class TopicTest {
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
}
