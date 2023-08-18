package com.example.application.data.repository;

import com.example.application.data.entity.Persons;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonsRepository extends JpaRepository<Persons, Integer> {

    @Query("select p from Persons p " +
            "where lower(p.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Persons> search(@Param("searchTerm") String searchTerm);

}