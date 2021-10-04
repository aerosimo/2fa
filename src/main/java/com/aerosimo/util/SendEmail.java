/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      SendEmail.java                                                  *
 * Created:   04/10/2021, 14:09                                               *
 * Modified:  04/10/2021, 14:09                                               *
 *                                                                            *
 * Copyright (c)  2021.  Aerosimo Ltd                                         *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,            *
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES            *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                   *
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT                 *
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,               *
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING               *
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE                 *
 * OR OTHER DEALINGS IN THE SOFTWARE.                                         *
 *                                                                            *
 ******************************************************************************/

package com.aerosimo.util;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Date;

public class SendEmail {
    static Logger log;

    static {
        log = LogManager.getLogger(SendEmail.class.getName());
    }

    public static String sendMail(String emailAddress, String emailSubject, String emailMessage, String emailFiles) {
        String response;
        response = "CODE: 0 --- DETAILS: Email Sent Successfully";
        // Check for multiple files attached
        String[] attachFiles;
        attachFiles = emailFiles.split(",");
        // Validate email address is correct
        if (EmailValidator.getInstance().isValid(emailAddress) && emailFiles.isEmpty()) {
            // Validate email address is not empty
            if (emailAddress.isEmpty()) {
                response = "CODE: EM1002AER --- DETAILS: Email Address fails validation because it can not be empty";
                log.error("CODE: EM1002AER --- DETAILS: Email Address fails validation because it can not be empty, SendEmail.sendMail");
            } // Validate email subject is not empty
            else if (emailSubject.isEmpty()) {
                response = "CODE: EM1003AER --- DETAILS: Email Subject fails validation because it can not be empty";
                log.error("CODE: EM1003AER --- DETAILS: Email Subject fails validation because it can not be empty, SendEmail.sendMail");
            } // Validate email message is not empty
            else if (emailMessage.isEmpty()) {
                response = "CODE: EM1004AER --- DETAILS: Email Message fails validation because it can not be empty";
                log.error("CODE: EM1004AER --- DETAILS: Email Message fails validation because it can not be empty, SendEmail.sendMail");
            } // When all passes send mail as seen below
            else {
                try {
                    Message msg;
                    msg = new MimeMessage(MailCon.getSession());
                    msg.setFrom(new InternetAddress("Aerosimo Support <support@aerosimo.com>"));
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
                    msg.setSubject(emailSubject);
                    msg.setSentDate(new Date());
                    msg.setContent(emailMessage, "text/html");
                    Transport.send(msg);
                } catch (MessagingException err) {
                    response = "CODE: EM1007AER --- DETAILS: A communication failure occurred during the delivery of this message.";
                    log.error("CODE: EM1007AER --- DETAILS: A communication failure occurred during the delivery of this message. Please try to resend the message later. " + err + " SendEmail.sendMail");
                }
            }
        } else if (EmailValidator.getInstance().isValid(emailAddress) && !emailFiles.isEmpty()) {
            if (emailAddress.isEmpty()) {
                response = "CODE: EM1002AER --- DETAILS: Email Address fails validation because it can not be empty";
                log.error("CODE: EM1002AER --- DETAILS: Email Address fails validation because it can not be empty, SendEmail.sendMail");
            } // Validate email subject is not empty
            else if (emailSubject.isEmpty()) {
                response = "CODE: EM1003AER --- DETAILS: Email Subject fails validation because it can not be empty";
                log.error("CODE: EM1003AER --- DETAILS: Email Subject fails validation because it can not be empty, SendEmail.sendMail");
            } // Validate email message is not empty
            else if (emailMessage.isEmpty()) {
                response = "CODE: EM1004AER --- DETAILS: Email Message fails validation because it can not be empty";
                log.error("CODE: EM1004AER --- DETAILS: Email Message fails validation because it can not be empty, SendEmail.sendMail");
            } else if (emailFiles.isEmpty()) {
                response = "CODE: EM1005AER --- DETAILS: Email Attachment fails validation because it can not be empty";
                log.error("CODE: EM1005AER --- DETAILS: Email Attachment fails validation because it can not be empty, SendEmail.sendMail");
            } // When all passes send mail as seen below
            else {
                try {
                    Message msg;
                    msg = new MimeMessage(MailCon.getSession());
                    msg.setFrom(new InternetAddress("Aerosimo Support <support@aerosimo.com>"));
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
                    msg.setSubject(emailSubject);
                    msg.setSentDate(new Date());
                    MimeBodyPart messageBodyPart;
                    messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setContent(emailMessage, "text/html");
                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    for (String filePath : attachFiles) {
                        MimeBodyPart attachPart;
                        attachPart = new MimeBodyPart();
                        try {
                            attachPart.attachFile(filePath);
                        } catch (IOException ex) {
                            response = "CODE: EM1006AER --- DETAILS: Attachment Error: Could not find the file to be attached";
                            log.error("CODE: EM1006AER --- DETAILS: Attachment Error: Could not find the file to be attached. " + ex + " SendEmail.sendAttachment");
                        }
                        multipart.addBodyPart(attachPart);
                    }
                    msg.setContent(multipart);
                    Transport.send(msg);
                } catch (MessagingException err) {
                    response = "CODE: EM1007AER --- DETAILS: A communication failure occurred during the delivery of this message.";
                    log.error("CODE: EM1007AER --- DETAILS: A communication failure occurred during the delivery of this message. Please try to resend the message later. " + err + " SendEmail.sendMail");
                }
            }
        }
        return response;
    }
}