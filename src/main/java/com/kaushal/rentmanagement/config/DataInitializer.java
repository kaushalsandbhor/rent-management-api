package com.kaushal.rentmanagement.config;

import com.kaushal.rentmanagement.entity.Flat;
import com.kaushal.rentmanagement.repository.FlatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadFlats(FlatRepository flatRepository) {

        return args -> {

            if (flatRepository.count() > 0) {
                return;
            }

            String[] flats = {
                    "101C","102C","103C","104C","105C","106C","107C","108C","109C","110C","111C",
                    "101D","102D","103D","104D","105D","106D","107D","108D","109D","110D","111D"
            };

            for (String number : flats) {

                Flat flat = new Flat();

                flat.setFlatNumber(number);

                flatRepository.save(flat);
            }

        };
    }
}