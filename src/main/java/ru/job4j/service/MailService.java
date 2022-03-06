package ru.job4j.service;

import org.springframework.stereotype.Service;

@Service
public class MailService {

    public String getMessage() {
        return "Отправка почты";
    }
}
