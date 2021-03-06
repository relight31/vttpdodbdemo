package vttp.dodbdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import vttp.dodbdemo.controllers.FriendlistController;
import vttp.dodbdemo.repository.FriendlistRepo;

// remember to autoconfigure mockmvc if using
@AutoConfigureMockMvc
@SpringBootTest
class DodbdemoApplicationTests {

	@Autowired
	FriendlistController controller;

	@Autowired
	FriendlistRepo repo;

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void createBeans() {
		assertNotNull(controller);
		assertNotNull(repo);
	}

	// a bit pointless to test this cos you are just testing sql
	@Test
	void getRecordsTest() {
		assertTrue(repo.getAllFriends().size() > 0);
	}

	// more impt to test logic
	@Test
	void addRecordsTest() throws Exception {
		// construct payload
		String payload = "email=test%40email.com&name=barney&phone=12345678&status=friend&dob=2022-04-06&passphrase=barney";

		// construct http request
		RequestBuilder req = MockMvcRequestBuilders.post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(payload);

		// make invocation
		MvcResult resp = mockMvc.perform(req).andReturn();

		// assert record made
		assertTrue(repo.checkEmailExists("test@email.com"));

		// delete test record
		assertTrue(repo.deleteFriendRecord("test@email.com"));
	}

	// @BeforeEach to setup some mock data before each test
	// @AfterEach to clean up mock data after each test

	// fail("failure message here") used after return statement if test is supposed
	// to fail but ends up succeeding instead eg try catch block

}
