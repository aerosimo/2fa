/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      PasswordUpdateMailTest.java                                     *
 * Created:   14/10/2021, 13:30                                               *
 * Modified:  14/10/2021, 13:30                                               *
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

package com.aerosimo.mail;

import com.aerosimo.util.Log;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUpdateMailTest {

    @BeforeEach
    void setUp() {
        Log.info("Starting Password Update Email Test");
    }

    @AfterEach
    void tearDown() {
        Log.info("Password Update Email Test complete");
    }


    @Test
    @DisplayName("Unit Testing PasswordUpdateMail")
    void sendPasswordMail() {
        String actual;
        String expected;
        actual = PasswordUpdateMail.sendPasswordMail("Support","support@aerosimo.com");
        assertNotNull(actual, "Checking that the email response is not null");
        expected = "Email Sent Successfully";
        Assertions.assertEquals(expected, actual, "This should match a the success message from the mail api");
    }
}