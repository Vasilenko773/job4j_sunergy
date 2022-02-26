package ru.job4j.repository;


import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.SqlFragmentAlias;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Passport;

import javax.persistence.SqlResultSetMapping;
import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

    public Passport findByNumber(int number);

    public Passport findBySeriaAndNumber(int seria, int number);

    @Query(value = "select distinct p from Passport p where p.expirationDate < current_date")
    public List<Passport> findAllPassportByExpiredDate();

    @Query(value = "select * from passports p where p.expiration_date between current_date"
           + " and date(current_date + interval '3 month')", nativeQuery = true)
    public List<Passport> findAllByReplaceThreeMonths();
}
