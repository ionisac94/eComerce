package com.md.demo.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class StatusControllerTest {

	@InjectMocks
	private StatusController statusController;

	@Mock
	private HealthEndpoint healthEndpoint;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(statusController).build();
	}

	@Test
	public void shouldReturnUpStatusWhenHealthCheckIsOK_GET() throws Exception {
		Health health = new Health.Builder().status(Status.UP).build();
		Mockito.when(healthEndpoint.health()).thenReturn(health);
		mockMvc.perform(MockMvcRequestBuilders.get("/status"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.content().json("{\"status\":\"UP\"}"));
	}

	@Test
	public void shouldReturnUpStatusWhenHealthCheckIsOK_POST() throws Exception {
		Health health = new Health.Builder().status(Status.UP).build();
		Mockito.when(healthEndpoint.health()).thenReturn(health);
		mockMvc.perform(MockMvcRequestBuilders.post("/status"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.content().json("{\"status\":\"UP\"}"));
	}

	@Test
	public void shouldReturnDownStatusWhenHealthCheckFailed_GET() throws Exception {
		Health health = new Health.Builder().status(Status.DOWN).build();
		Mockito.when(healthEndpoint.health()).thenReturn(health);
		mockMvc.perform(MockMvcRequestBuilders.get("/status"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.content().json("{\"status\":\"DOWN\"}"));
	}

	@Test
	public void shouldReturnDownStatusWhenHealthCheckFailed_POST() throws Exception {
		Health health = new Health.Builder().status(Status.DOWN).build();
		Mockito.when(healthEndpoint.health()).thenReturn(health);
		mockMvc.perform(MockMvcRequestBuilders.post("/status"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.content().json("{\"status\":\"DOWN\"}"));
	}
}
