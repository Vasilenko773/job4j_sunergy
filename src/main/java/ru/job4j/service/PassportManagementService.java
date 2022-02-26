package ru.job4j.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.Passport;
import ru.job4j.repository.PassportRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassportManagementService {

    @Autowired
    PassportRepository repository;

    public boolean validator(Passport passport) {
        Passport exp = repository.findBySeriaAndNumber(passport.getSeria(), passport.getNumber());
        if (exp == null) {
            return true;
        }
        return exp.getSeria() != passport.getSeria() || exp.getNumber() != passport.getNumber();
    }

    public List<Passport> findAllPassport() {
        return (List<Passport>) repository.findAll();
    }

    public boolean savePassport(Passport passport) {
        if (validator(passport)) {
            repository.save(passport);
            return true;
        }
        return false;
    }

    public Passport findByIdPassport(int id) {
        return repository.findById(id).orElse(null);
    }

    public boolean updatePassport(int id, Passport passport) {
        Passport exp = findByIdPassport(id);
        if (exp != null && validator(passport)) {
            passport.setId(exp.getId());
            repository.save(passport);
            return true;
        }
        return false;
    }

    public void deletePassport(int id) {
        Passport exp = findByIdPassport(id);
        if (exp != null) {
            repository.delete(exp);
        }
    }

    public Passport findPassportByNumber(int number) {
        return repository.findByNumber(number);
    }

    public List<Passport> findAllPassportByExpiredPeriod() {
        return repository.findAllPassportByExpiredDate();
    /*    List<Passport> all = findAllPassport();
        return all.stream().
                filter(i -> i.getExpirationDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());*/
    }

    public List<Passport> findAllPassportByReplaceInThreeMonths() {
        return repository.findAllByReplaceThreeMonths();
       /* return findAllPassport().stream().
                filter(i -> i.getExpirationDate().isAfter(LocalDateTime.now())
                        && i.getExpirationDate().isBefore(LocalDateTime.now().plusMonths(3))).
                collect(Collectors.toList());*/
    }
}
