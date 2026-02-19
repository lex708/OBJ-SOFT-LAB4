package edu.oosd.restservices.RestApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private static final String template_home = "Hello! You are at %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping ("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(defaultValue = "World") String name) {
        Greeting greeting = new Greeting(
                counter.incrementAndGet(),
                String.format(template, name),
                Map.of("self", "/greeting?name=" +name, "home", "/"));
        return new ResponseEntity<>(greeting, HttpStatus.OK);


    }

@GetMapping ("/")
public ResponseEntity<Greeting> home() {
    Greeting greeting = new Greeting(
            counter.incrementAndGet(),
            String.format(template_home,"home page"),
            Map.of("self", "/", "greeting" ,"/greeting"));
    return new ResponseEntity<>(greeting, HttpStatus.OK);


}
}
