package ru.job4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.service.PassportManagementService;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    PassportManagementService service;

    @GetMapping("/find")
    public List<Passport> findAll() {
        return service.findAllPassport();
    }

    @GetMapping("/findBy")
    public Passport findPassportByNumber(@RequestParam int number) {
        return service.findPassportByNumber(number);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findAllPassportByExpiredPeriod() {
        return service.findAllPassportByExpiredPeriod();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findAllPassportByReplaceInThreeMonths() {
        return service.findAllPassportByReplaceInThreeMonths();
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePassport(@RequestBody Passport passport) {
            if (service.savePassport(passport)) {
                return ResponseEntity.ok("Операция выполненна успешно");
            }
        return ResponseEntity.badRequest().body("Паспорт с такими данынми существует");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePassport(@RequestParam int id, @RequestBody Passport passport) {
        if (service.updatePassport(id, passport)) {
            return ResponseEntity.ok("Операция выполненна успешно");
        }
        return ResponseEntity.badRequest().body("Паспорт с такими данынми существует");
    }

    @DeleteMapping("/delete")
    public void deletePassport(@RequestParam int id) {
        service.deletePassport(id);
    }
}
