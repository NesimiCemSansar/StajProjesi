package com.example.application.data.service;

import com.example.application.data.entity.Persons;
import com.example.application.data.repository.PersonsRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonsService {
    private final PersonsRepository personsRepository;

    public PersonsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public List<Persons> findAllPersons(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return personsRepository.findAll();
        } else {
            return personsRepository.search(stringFilter);
        }
    }

    public long countPersons() {
        return personsRepository.count();
    }

    public void deletePersons(Persons persons) {
        personsRepository.delete(persons);
    }

    public void savePersons(Persons persons) {
        if (persons == null) {
            System.err.println("Person is null. Are you sure you have connected your form to the application?");
            return;
        }
        personsRepository.save(persons);
    }

    public Persons findById(int id) {
        return personsRepository.findById(id).orElse(null);
    }
}
