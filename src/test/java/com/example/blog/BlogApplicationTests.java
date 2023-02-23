package com.example.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

// @JsonTest tells spring to create Json Test class
@JsonTest
class BlogApplicationTests {

	/*@Test
	public void myFirstTest() {
		assertThat(1).isEqualTo(42);
	}*/

	// @AutoWired tells spring to create an object of the code
	// JacksonTester does serialization of deserialization of JSON objects
	@Autowired
	private JacksonTester<Post> json;

	// Serialization test
	@Test
	public void postSerializationTest() throws IOException {
		// Given Inputs
		Post post = new Post(1L);
		// Whether the given inputs were serialized
		assertThat(json.write(post)).isStrictlyEqualToJson("expected.json");
		assertThat(json.write(post)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(post)).extractingJsonPathNumberValue("@.id").isEqualTo(1);


	}

}


//		assertThat(json.write(post)).hasJsonPathStringValue("@.content");
//		assertThat(json.write(post)).extractingJsonPathStringValue("@.content").isEqualTo("aaa");