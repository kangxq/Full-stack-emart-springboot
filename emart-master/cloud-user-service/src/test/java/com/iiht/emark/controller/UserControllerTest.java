package com.iiht.emark.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.iiht.emart.UserApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 *
	 * user not exist : insert
	 */
	@Test
	public void registTest01() throws Exception {
		String content="{\"userName\":\"111222\",\"password\":\"111222\",\"basicSelect\":\"0\",\"emalid\":\"kxq@111.com\", \"mobileNumber\":\"123456789\"}";
		MvcResult mvcResult = mockMvc.perform(post("/emart/register").contentType(MediaType.APPLICATION_JSON)
				.content(content)).andReturn();
		mvcResult.getResponse().setCharacterEncoding("UTF-8");
		int status = mvcResult.getResponse().getStatus();
		String responseString = mvcResult.getResponse().getContentAsString();
		System.out.println(responseString);
		Assert.assertEquals(200, status);
	}

	/**
	 *　registTest01 after execution
	 * user exist : throw Exception
	 */
	@Test
	@Transactional
	public void registTest02() {

		try {
			String content="{\"userName\":\"111222\",\"password\":\"111222\",\"basicSelect\":\"0\",\"emalid\":\"kxq@111.com\", \"mobileNumber\":\"123456789\"}";
			mockMvc.perform(post("/emart/register").contentType(MediaType.APPLICATION_JSON)
					.content(content)).andReturn();
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("user has exist!", e.getCause().getMessage());
		}
	}
}
