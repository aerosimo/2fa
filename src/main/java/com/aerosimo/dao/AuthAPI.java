/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      AuthAPI.java                                                    *
 * Created:   11/10/2021, 23:18                                               *
 * Modified:  11/10/2021, 23:18                                               *
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

package com.aerosimo.dao;

import com.aerosimo.mail.OTPMail;
import com.aerosimo.mail.PasswordUpdateMail;
import com.aerosimo.mail.TempPasswordMail;
import com.aerosimo.util.DBCon;
import com.aerosimo.util.GenerateOTP;
import com.aerosimo.util.GeneratePassword;
import com.aerosimo.util.Log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class AuthAPI {

    static CallableStatement stmt;
    static Connection con;
    static String sql;
    static String response;

    public static String signup(String username, String emailAddress, String mfa) {
        stmt = null;
        con = DBCon.getConnection();
        response = "";
        String password;
        sql = "{call auth_pkg.signup(?,?,?,?,?,?)}";
        password = GeneratePassword.getPassword();
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, emailAddress);
            stmt.setString(4, mfa);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.execute();
            if (Objects.equals(stmt.getString(6), "Success")) {
                response = stmt.getString(6);
                TempPasswordMail.sendPasswordMail(password,emailAddress);
            }
        } catch (SQLException err) {
            response = "Registration attempt failed at AuthAPI.signup";
            Log.warn("Registration attempt failed with the following details at AuthAPI.signup: DETAILS: " + err);
        } finally {
            try {
                assert stmt != null;
                stmt.close();
            } catch (SQLException err) {
                Log.fatal("Registration attempt failed with the following details at AuthAPI.signup: DETAILS: " + err);
            }
        }
        return response;
    }

    public static String tempass(String username, String tempword, String pword) {
        stmt = null;
        con = DBCon.getConnection();
        sql = "{call auth_pkg.setPassword(?,?,?,?,?,?,?)}";
        try{
            stmt = con.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, tempword);
            stmt.setString(3, pword);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.execute();
            if (Objects.equals(stmt.getString(6), "Success")) {
                response = stmt.getString(6);
                PasswordUpdateMail.sendPasswordMail(username,stmt.getString(4));
            }
        } catch (SQLException err) {
            response = "Temporary Password attempt failed at AuthAPI.tempass";
            Log.warn("Temporary Password attempt failed with the following details at AuthAPI.tempass: DETAILS: " + err);
        } finally {
            try {
                assert stmt != null;
                stmt.close();
            } catch (SQLException err) {
                Log.fatal("Temporary Password attempt failed with the following details at AuthAPI.tempass: DETAILS: " + err);
            }
        }
        return response;
    }

    public static String signout (String username, String ipaddress){
        response = "";
        stmt = null;
        sql = "{call auth_pkg.signout(?,?)}";
        con = DBCon.getConnection();
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, ipaddress);
            stmt.execute();
            response = "Success";
        } catch (SQLException err) {
            response = "Signout attempt failed at AuthAPI.signout";
            Log.warn("Logout attempt failed with the following details at AuthAPI.signout: DETAILS: " + err);
        } finally {
            try {
                assert stmt != null;
                stmt.close();
            } catch (SQLException err) {
                Log.error("Logout attempt failed with the following details at AuthAPI.signout: DETAILS: " + err);
            }
        }
        return response;
    }

    public static String signin(String username, String password, String ipaddress) {
        response = "";
        String otp, email;
        stmt = null;
        sql = "{call auth_pkg.signin(?,?,?,?,?,?,?)}";
        con = DBCon.getConnection();
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, ipaddress);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.registerOutParameter(7, Types.VARCHAR);
            stmt.execute();
            if (Objects.equals(stmt.getString(5),"Y")){
                response = "MFA";
                otp = GenerateOTP.getOTP();
                email = stmt.getString(4);
                sql = "{call auth_pkg.setOTP(?,?,?,?)}";
                try {
                    stmt = con.prepareCall(sql);
                    stmt.setString(1, username);
                    stmt.setString(2, otp);
                    stmt.registerOutParameter(3, Types.VARCHAR);
                    stmt.registerOutParameter(4, Types.VARCHAR);
                    stmt.execute();
                    OTPMail.sendOTP(username, otp, email);
                } catch (SQLException err){
                    Log.error("Login attempt failed with the following details at AuthAPI.signin: DETAILS: " + err);
                }
            } else if (Objects.equals(stmt.getString(5),"N")){
                response = "Success";
            } else {
                response = "Wrong Username Or Password!";
            }
        } catch (SQLException err) {
            Log.error("Login attempt failed with the following details at AuthAPI.signin: DETAILS: " + err);
        } finally {
            try {
                assert stmt != null;
                stmt.close();
            } catch (SQLException err) {
                Log.error("Login attempt failed with the following details at AuthAPI.signin: DETAILS: " + err);
            }
        }
        return response;
    }

    public static String checkOTP (String authcode){
        stmt = null;
        con = DBCon.getConnection();
        response = "";
        sql = "{call auth_pkg.checkOTP(?,?,?)}";
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1, authcode);
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.execute();
            response = stmt.getString(3);
        } catch (SQLException err) {
            response = "Login attempt failed at AuthAPI.checkOTP";
            Log.warn("Login attempt failed with the following details at AuthAPI.checkOTP: DETAILS: " + err);
        } finally {
            try {
                assert stmt != null;
                stmt.close();
            } catch (SQLException err) {
                Log.fatal("Login attempt failed with the following details at AuthAPI.checkOTP: DETAILS: " + err);
            }
        }
        return response;
    }
}