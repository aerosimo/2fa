/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      MailCon.java                                                    *
 * Created:   04/10/2021, 13:57                                               *
 * Modified:  04/10/2021, 13:57                                               *
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

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class MailCon {

    static Logger log;

    static {
        log = LogManager.getLogger(MailCon.class.getName());
    }

    public static Session getSession() {

        String emailHost = null;
        String emailPort = null;
        String emailSSL = null;
        Session sess;
        sess = null;
        String usr = null;
        String pword = null;

        try {
            File myFile;
            myFile = new File("/opt/secret/email.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                usr = myReader.nextLine();
                pword = myReader.nextLine();
                emailHost = myReader.nextLine();
                emailPort = myReader.nextLine();
                emailSSL = myReader.nextLine();
            }
            myReader.close();
            log.info("Successful reading email credentials");
        } catch (Exception e) {
            log.error("Error reading email credentials: " + e);
        }

        final String emailUserName;
        emailUserName = usr;
        final String emailPassword;
        emailPassword = pword;
        Properties prop;
        prop = new Properties();
        prop.put("mail.smtp.host", emailHost);
        prop.put("mail.smtp.port", emailPort);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", emailPort);
        prop.put("mail.smtp.socketFactory.class", emailSSL);
        prop.put("mail.smtp.ssl.trust", emailHost);
        prop.put("mail.smtp.starttls.enable", "true");

        try {
            Authenticator auth;
            auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailUserName, emailPassword);
                }
            };
            sess = Session.getInstance(prop, auth);
        } catch (Exception e) {
            log.error("Email Connection Error : " + e);
        }
        log.info("Email session is established successfully");
        return sess;
    }
}