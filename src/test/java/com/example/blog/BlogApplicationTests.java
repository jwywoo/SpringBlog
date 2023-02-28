package com.example.blog;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

// SpringBootTest setting up the environment for the test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogApplicationTests {
	// @Autowired -> it seems like it is a user
	@Autowired
	TestRestTemplate restTemplate;

	// @Test test case -> user behavior : user is requesting to see something
	@Test
	void shouldReturnAPostWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/posts/99", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isNotNull();
	}

	// @Test test case2 -> user behavior : user is trying to access something but doesn't exist
	@Test
	void shouldNotReturnPostUnknown() {
		ResponseEntity<String> response = restTemplate.getForEntity("/posts/111111", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}
}

// @JsonTest tells spring to create Json Test class
//@JsonTest
//class BlogApplicationTests {
//
//	/*@Test
//	public void myFirstTest() {
//		assertThat(1).isEqualTo(42);
//	}*/
//
//	// @AutoWired tells spring to create an object of the code
//	// JacksonTester does serialization of deserialization of JSON objects
//	@Autowired
//	private JacksonTester<Post> json;
//
//	// Serialization test
//	@Test
//	public void postSerializationTest() throws IOException {
//		// Given Inputs
//		Post post = new Post(1L);
//		// Whether the given inputs were serialized
//		assertThat(json.write(post)).isStrictlyEqualToJson("expected.json");
//		assertThat(json.write(post)).hasJsonPathNumberValue("@.id");
//		assertThat(json.write(post)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
//
//	}
//
//	@Test
//	public void postDeserializationTest() throws IOException {
//		String expected = """
//    			{
//    				"id":1
//    			}
//				""";
//		assertThat(json.parse(expected)).isEqualTo(new Post(2L));
//		assertThat(json.parseObject(expected).id()).isEqualTo(2);
//	}
//}


//		assertThat(json.write(post)).hasJsonPathStringValue("@.content");
//		assertThat(json.write(post)).extractingJsonPathStringValue("@.content").isEqualTo("aaa");