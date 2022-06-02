package com.example.starwars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class StarwarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarwarsApplication.class, args);
    }


    /**
     * Handles the search request
     * @param q is the search query entered
     * @returns the retrieved content
     * @throws JsonProcessingException
     */
    @GetMapping("/persons")
    @ResponseBody
    public Character searchPerson (@RequestParam String q) throws JsonProcessingException {

        ResponseEntity<String> response = getRequest("people/?search=" + q);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        return setCharacter(root);
    }

    /**
     * Handles a direct lookup based on ID
     * @param id is passed to retrieve the corresponding record
     * @returns the passed value
     * @throws JsonProcessingException and an 404 error in case of null record
     */
    @GetMapping("/persons/{id}")
    public Character getPerson (@PathVariable String id) throws JsonProcessingException {

        ResponseEntity<String> response = getRequest("people/" + id);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        return setCharacter(root);
    }

    /**
     * This method handles the API requests to utilize SWAPI
     *
     * @param query: URL required to add to the base URL to handle the request
     * @return the retrieved object from the passed API (SWAPI in this case).
     */
    private ResponseEntity<String> getRequest(String query) {
        String baseUrl = "https://swapi.dev/api/";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(baseUrl + query, String.class);
    }

    /**
     * Sets the Character object to return to the user.
     * @param root is the JSON object required to be translated to the requested format.
     * @returns the data.
     */
    private Character setCharacter(JsonNode root) {
        Character character = new Character();

        character.setId(root.path("id").asLong()); // Retrieve from passed parameter (not normally contained in JSON)
        character.setName(root.path("name").asText());
        character.setBirthYear(root.path("birth_year").asText());
        character.setGender(root.path("gender").asText());
        character.setHeight(root.path("height").asInt());
        character.setWeight(root.path("mass").asInt());

        return character;
    }
}