/*package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController    // to make a class handle HTTP requests and return data directly (usually JSON) as a response, mainly used for building REST APIs.
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@GetMapping
	public String hello(){
		return "Hello World";
	}*/

	/*@GetMapping
	public List<String> hello_list(){
		return List.of("Hello", "World");
	}

} */

/*
@Controller → returns a view/page (HTML)
@RestController → returns data (JSON, String, objects)
*/

//==================================================================================================


package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
