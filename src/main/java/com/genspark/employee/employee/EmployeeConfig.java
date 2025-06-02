package com.genspark.employee.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            Employee abc = new Employee(
                    "Abc",
                    "abc@company.com",
                    LocalDate.of(1990, Month.JANUARY, 1)
            );

            Employee qwe = new Employee(
                    "Qwe",
                    "qwe@company.com",
                    LocalDate.of(1995, Month.JANUARY, 1)
            );

            employeeRepository.saveAll(
                    List.of(abc, qwe)
            );
        };
    }
}
