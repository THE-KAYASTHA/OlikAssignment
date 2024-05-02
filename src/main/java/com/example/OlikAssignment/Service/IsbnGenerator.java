package com.example.OlikAssignment.Service;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class IsbnGenerator {


        // Generate a random 13-digit ISBN
        public String generateIsbn() {
            StringBuilder isbnBuilder = new StringBuilder("978"); // Common prefix for 13-digit ISBNs
            isbnBuilder.append(generateRandomDigits(9)); // Generate 9 random digits
            isbnBuilder.append(calculateCheckDigit(isbnBuilder.toString())); // Calculate and append the check digit
            return isbnBuilder.toString();
        }

        // Generate n random digits
        private String generateRandomDigits(int n) {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                sb.append(random.nextInt(10)); // Append a random digit (0-9)
            }
            return sb.toString();
        }

        // Calculate the check digit for a given ISBN (without the check digit)
        private int calculateCheckDigit(String isbnWithoutCheckDigit) {
            int sum = 0;
            for (int i = 0; i < isbnWithoutCheckDigit.length(); i++) {
                int digit = Character.getNumericValue(isbnWithoutCheckDigit.charAt(i));
                sum += (i % 2 == 0) ? digit : digit * 3;
            }
            int remainder = sum % 10;
            return (10 - remainder) % 10;
        }
    }

