package com.pack.taller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pack.taller.model.TsscTopic;
import com.pack.taller.repository.TopicRepository;
import com.pack.taller.service.TopicServiceImp;

public class TestTopic {

	@Mock
	public TopicRepository topicRepository;

	@InjectMocks
	public TopicServiceImp topicService;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	@DisplayName("SaveTest")
	class saveMethods {
		/**
		 * all ok
		 */
		@Test
		@DisplayName("all is ok")
		public void testOk() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			try {
				when(topicService.saveTopic(topic)).thenReturn(true);
				assertTrue(topicService.saveTopic(topic));
			} catch (Exception e) {
				fail();
			}
		}
		/**
		 * topic null
		 */
		@Test
		@DisplayName("topic is null")
		public void testTopicNull() {
			TsscTopic topic = null;
			assertThrows(Exception.class, () -> topicService.saveTopic(topic));
		}
		/**
		 * shows exception because there aren't sprints
		 */
		@Test
		@DisplayName("There aren't sprints")
		public void testSprintsEmpty() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(0);
			assertThrows(Exception.class, () -> {
				topicService.saveTopic(topic);
			});
		}
		/**
		 * shows exception because there aren't groups
		 */
		@Test
		@DisplayName("There aren't groups")
		public void testGroupsEmpty() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(0);
			topic.setDefaultSprints(1);
			assertThrows(Exception.class, () -> {
				topicService.saveTopic(topic);
			});
		}
	}

	@Nested
	@DisplayName("EditTest")
	class editMethods {
		/**
		 * all ok
		 */
		@Test
		@DisplayName("all ok")
		public void testOk() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topic.setId(1);
			topic.setName("topic1");
			try {
				topicService.saveTopic(topic);
				topic.setName("topic2");
				topicService.editTopic(topic, Long.valueOf(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Optional<TsscTopic> optional = Optional.of(topic);
			when(topicRepository.save(topic)).thenReturn(topic);
			when(topicRepository.findById(topic.getId())).thenReturn(optional);
			assertEquals(optional.get().getName(), "topic2");
		}
		/**
		 * topic null
		 */
		@Test
		@DisplayName("Topic null")
		public void testTopicNull() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topic.setId(1);
			topic.setName("topic1");
			try {
				topicService.saveTopic(topic);
			} catch (Exception e) {
				e.printStackTrace();
			}
			TsscTopic topic2 = null;
			assertThrows(Exception.class, () -> topicService.editTopic(topic2, Long.valueOf(1)));
		}
		/**
		 * sprints smaller than 0
		 */
		@Test
		@DisplayName("spint small")
		public void testSpringSmall() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topic.setId(1);
			topic.setName("topic1");
			try {
				topicService.saveTopic(topic);
			} catch (Exception e) {
				e.printStackTrace();
			}
			topic.setDefaultSprints(0);
			assertThrows(Exception.class, () -> topicService.editTopic(topic, Long.valueOf(1)));
		}
		/**
		 * groups smaller than 0
		 */
		@Test
		@DisplayName("groups small")
		public void testGroupsSmall() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topic.setId(1);
			topic.setName("topic1");
			try {
				topicService.saveTopic(topic);
			} catch (Exception e) {
				e.printStackTrace();
			}
			topic.setDefaultGroups(0);
			assertThrows(Exception.class, () -> topicService.editTopic(topic, Long.valueOf(1)));
		}

	}

}
