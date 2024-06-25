package com.graduationProject.medicory.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%&_";
    private static  final int LENGTH = 12;
    private static final String ALL_CHARACTERS = UPPER + LOWER + DIGITS + SPECIAL;
    private final JavaMailSender mailSender;

    private static SecureRandom random = new SecureRandom();

    public  String generatePassword() {
        StringBuilder sb = new StringBuilder(LENGTH);

        // at least one of each type
        sb.append(getRandomChar(UPPER));
        sb.append(getRandomChar(LOWER));
        sb.append(getRandomChar(DIGITS));
        sb.append(getRandomChar(SPECIAL));

        for (int i = 4; i < LENGTH; i++) {
            sb.append(getRandomChar(ALL_CHARACTERS));
        }
        String shuffledPassword = shuffleString(sb.toString());
        return shuffledPassword;
    }

    private static char getRandomChar(String characterString) {
        int randomIndex = random.nextInt(characterString.length());
        return characterString.charAt(randomIndex);
    }

    private static String shuffleString(String inputString) {
        char[] characters = inputString.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
    public void sendPasswordEmail(String receiver,String password) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Your account password ");
        email.setText("Your password is : "+password);
        email.setTo(receiver);
        mailSender.send(email);
    }
}
