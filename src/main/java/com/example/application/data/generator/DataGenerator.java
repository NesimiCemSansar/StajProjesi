// package com.example.application.data.generator;

// import com.example.application.data.entity.Patient;
// import com.example.application.data.repository.PatientRepository;
// import com.vaadin.exampledata.DataType;
// import com.vaadin.exampledata.ExampleDataGenerator;
// import com.vaadin.flow.spring.annotation.SpringComponent;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Random;
// import java.util.stream.Collectors;
// //import java.util.stream.Stream;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;

// @SpringComponent
// public class DataGenerator {

// @Bean
// public CommandLineRunner loadData(PatientRepository patientRepository) {

// return args -> {
// Logger logger = LoggerFactory.getLogger(getClass());
// if (patientRepository.count() != 0L) {
// logger.info("Using existing database");
// return;
// }
// int seed = 123;

// logger.info("Generating demo data");
// // ExampleDataGenerator<Company> companyGenerator = new
// // ExampleDataGenerator<>(Company.class,
// // LocalDateTime.now());
// // companyGenerator.setData(Company::setName, DataType.COMPANY_NAME);
// // List<Company> companies =
// // companyRepository.saveAll(companyGenerator.create(5, seed));

// // List<Status> statuses = statusRepository
// // .saveAll(Stream.of("Imported lead", "Not contacted", "Contacted",
// "Customer",
// // "Closed (lost)")
// // .map(Status::new).collect(Collectors.toList()));

// logger.info("... generating 50 Contact entities...");
// ExampleDataGenerator<Patient> patientGenerator = new
// ExampleDataGenerator<>(Patient.class,
// LocalDateTime.now());
// patientGenerator.setData(Patient::setFirstName, DataType.FIRST_NAME);
// patientGenerator.setData(Patient::setLastName, DataType.LAST_NAME);
// patientGenerator.setData(Patient::setEmail, DataType.EMAIL);

// Random r = new Random(seed);
// List<Patient> patients = patientGenerator.create(50,
// seed).stream().map(patient -> {
// // contact.setCompany(companies.get(r.nextInt(companies.size())));
// // contact.setStatus(statuses.get(r.nextInt(statuses.size())));
// return patient;
// }).collect(Collectors.toList());

// patientRepository.saveAll(patients);

// logger.info("Generated demo data");
// };
// }

// }
