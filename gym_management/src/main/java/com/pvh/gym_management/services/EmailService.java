package com.pvh.gym_management.services;

public interface EmailService {
    void sendHtmlEmail(String to, String subject, String htmlContent);
}
