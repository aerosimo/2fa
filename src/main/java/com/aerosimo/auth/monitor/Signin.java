/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      Signin.java                                                     *
 * Created:   05/10/2021, 01:15                                               *
 * Modified:  05/10/2021, 01:15                                               *
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

package com.aerosimo.auth.monitor;

import com.aerosimo.auth.controller.AuthAPI;
import com.aerosimo.auth.model.SigninMsg;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginServlet", description = "A simple login servlet to validate user credentials", urlPatterns = "/signin")
public class Signin extends HttpServlet {

    static Logger log;

    static {
        log = LogManager.getLogger(Signin.class.getName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        List<SigninMsg> loginList;
        HttpSession sess;
        String inet;
        String pword;
        String uname;
        String email;
        email = null;
        String authCode;
        authCode = null;
        String expiry;
        expiry = null;
        inet = req.getRemoteAddr();
        sess = req.getSession();
        String loginCode;
        loginCode = null;
        String loginDetails;
        loginDetails = null;
        uname = req.getParameter("uname");
        pword = req.getParameter("pword");
        loginList = AuthAPI.signin(uname, pword, inet);
        PrintWriter out = resp.getWriter();
        for (SigninMsg e : loginList) {
            email = e.getEmailAddress();
            authCode = e.getAuthCode();
            expiry = e.getExpiry();
            loginCode = e.getResponseCode();
            loginDetails = e.getResponseDetail();
        }
        out.println("<HTML><HEAD><TITLE>Success!</TITLE></HEAD>" +
                "<BODY><H2>Hello, I worked Successfully</H2>" +
                "<P><H6>My Username is " + uname + "</H6>" +
                "<H6>My email is " + email + "</H6>" +
                "<H6>My Message Code is " + loginCode + "</H6>" +
                "<H6>My message details is " + loginDetails + "</H6>" +
                "<H6>My OTP Code is " + authCode + "and it expires at " + expiry + "</H6></P></BODY></HTML>");
        out.close();
    }
}