package com.codeshuttle.uber;

import com.codeshuttle.uber.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    void contextLoads() {
        emailSenderService.sendEmail(
                "kelahih426@datingso.com",
                "Hey",
                "Hello");
    }

    @Test
    void sendEmailMultiple(){
        String[] emails = {
                "kelahih426@datingso.com",
                "vemixi1118@bulmp3.com",
                "pekafo2436@bulmp3.com"
        };
        emailSenderService.sendEmail(
                emails,
                "Namaste",
                "Jai Jinendra");
    }
}
