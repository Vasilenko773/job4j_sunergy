package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ru.job4j.model.Passport;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${api.url}")
    private String parentUrl;

    @Autowired
    private RestTemplate rest;

    @PostMapping("/save")
    public void save(@RequestBody Passport passport) {
        String url = parentUrl + "save";
        rest.postForObject(url, passport, Passport.class);
    }

    @GetMapping("/find")
    public List<Passport> findAll() {
        String url = parentUrl + "find";
        List<Passport> rsl = rest.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
        return rsl;
    }

    @GetMapping("/findBy/{number}")
    public Passport findByNumber(@PathVariable int number) {
        String url = parentUrl + "findBy?number={number}";
        return rest.getForObject(url, Passport.class, number);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findAllByExpiredPeriod() {
        String url = parentUrl + "unavaliabe";
        List<Passport> rsl = rest.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
        return rsl;
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findAllByReplaceInThreeMonths() {
        String url = parentUrl + "find-replaceable";
        List<Passport> rsl = rest.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
        return rsl;
    }

    @PutMapping("/update/{id}")
    public void updateTemplate(@PathVariable int id, @RequestBody Passport passport) {
        String url = parentUrl + "update?id={id}";
        rest.put(url, passport, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTemolate(@PathVariable int id) {
        String url = parentUrl + "delete?id={id}";
        rest.delete(url, id);
    }
}
