package dencrypter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Dencrypter {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("angel"));
    }

}
