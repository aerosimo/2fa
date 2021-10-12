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

import com.aerosimo.util.DBCon;
import com.aerosimo.util.GeneratePassword;
import com.aerosimo.util.Log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class AuthAPI {

    public static String signup(String username, String emailAddress, String mfa) {
        CallableStatement stmt;
        stmt = null;
        Connection con;
        con = DBCon.getConnection();
        String response;
        response = "";
        String sql;
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
                Log.info("Account Registration is successful for " + username + " thi the password " + password + " All this information will be emailed to this address " + emailAddress);
            } else {
                Log.error("Signup fails because CODE: " + stmt.getString(5) + " DETAILS: " + stmt.getString(6));
            }
        } catch (SQLException err) {
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
}