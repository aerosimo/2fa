/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      SigninMsg.java                                                  *
 * Created:   14/10/2021, 20:46                                               *
 * Modified:  14/10/2021, 20:46                                               *
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

package com.aerosimo.bean;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "SigninResponse")
@XmlType(propOrder = {"emailAddress", "mfaStatus", "responseCode", "responseDetail"})
public class SigninMsg {
    private String emailAddress;
    private String mfaStatus;
    private String responseCode;
    private String responseDetail;

    public SigninMsg() {
    }

    public SigninMsg(String emailAddress, String mfaStatus, String responseCode, String responseDetail) {
        this.emailAddress = emailAddress;
        this.mfaStatus = mfaStatus;
        this.responseCode = responseCode;
        this.responseDetail = responseDetail;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMfaStatus() {
        return mfaStatus;
    }

    public void setMfaStatus(String mfaStatus) {
        this.mfaStatus = mfaStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDetail() {
        return responseDetail;
    }

    public void setResponseDetail(String responseDetail) {
        this.responseDetail = responseDetail;
    }

    @Override
    public String toString() {
        return "SigninMsg{" +
                "emailAddress='" + emailAddress + '\'' +
                ", mfaStatus='" + mfaStatus + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseDetail='" + responseDetail + '\'' +
                '}';
    }
}