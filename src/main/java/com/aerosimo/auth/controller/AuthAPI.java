/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      AuthAPI.java                                                    *
 * Created:   05/10/2021, 00:50                                               *
 * Modified:  05/10/2021, 00:50                                               *
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

package com.aerosimo.auth.controller;

import com.aerosimo.auth.model.SigninMsg;
import com.aerosimo.util.DBCon;
import com.aerosimo.util.GenerateOTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AuthAPI {
    static Logger log;

    static {
        log = LogManager.getLogger(AuthAPI.class.getName());
    }
    public static List<SigninMsg> signin(String username, String password, String ipaddress) {
        List<SigninMsg> loginList;
        loginList = new ArrayList<>();
        CallableStatement stmt;
        stmt = null;
        String sql;
        sql = "{call auth_pkg.signin(?,?,?,?,?,?,?,?,?)}";
        Connection con;
        con = DBCon.getConnection();
        String authCode;
        authCode = GenerateOTP.getOTP();
        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, ipaddress);
            stmt.setString(4, authCode);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.registerOutParameter(7, Types.VARCHAR);
            stmt.registerOutParameter(8, Types.VARCHAR);
            stmt.registerOutParameter(9, Types.VARCHAR);
            stmt.execute();
            loginList.add(new SigninMsg(stmt.getString(5), stmt.getString(6), stmt.getString(7), stmt.getString(8), stmt.getString(9)));
        } catch (SQLException err) {
            log.error("Login attempt failed with the following details at AuthAPI.signin: DETAILS: " + err);
        } finally {
            try {
                assert stmt != null;
                stmt.close();
            } catch (SQLException err) {
                log.error("Login attempt failed with the following details at AuthAPI.signin: DETAILS: " + err);
            }
        }
        return loginList;
    }
}