package ru.job4j.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "passports")
@Component
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int seria;

    private int number;

    private String name;

    private LocalDateTime expirationDate;

    public Passport() {
    }

    public Passport(int seria, int number, String name, LocalDateTime expirationDate) {
        this.seria = seria;
        this.number = number;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSeria() {
        return seria;
    }

    public void setSeria(int seria) {
        this.seria = seria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return id == passport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Passport{"
                + "id=" + id
                + ", seria=" + seria
                + ", number=" + number
                + ", name='" + name + '\''
                + ", expirationDate=" + expirationDate
                + '}';
    }
}

