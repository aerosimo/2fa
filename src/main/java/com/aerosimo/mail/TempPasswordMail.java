/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      TempPasswordMail.java                                           *
 * Created:   13/10/2021, 14:41                                               *
 * Modified:  13/10/2021, 14:41                                               *
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
import com.aerosimo.util.SendEmail;

public class TempPasswordMail {
    public static String sendPasswordMail(String generatedPassword, String emailAddress) {
        StringBuilder message;
        String response;
        message = new StringBuilder("<div class=\"\">\n" +
                "<div class=\"aHl\"></div>\n" +
                "<div id=\":evp\" tabindex=\"-1\"></div>\n" +
                "<div id=\":ex3\" class=\"ii gt\">\n" +
                "<div id=\":ex2\" class=\"a3s aXjCH msg-2027985233961450485\">\n" +
                "   <div style=\"height:100%;margin:0;background-color:#f2f4f6;color:#51545e;font-family:Lato,Tahoma,sans-serif;width:100%!important\">\n" +
                "      <table style=\"width:100%;margin:0;padding:10px 0 0 0;background-color:#f2f4f6\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "         <tbody>\n" +
                "            <tr>\n" +
                "               <td style=\"word-break:break-word;font-family:Lato,Tahoma,sans-serif;font-size:16px\" align=\"center\">\n" +
                "                  <table style=\"width:100%;margin:0;padding:0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                     <tbody>\n" +
                "                        <tr>\n" +
                "                           <td style=\"word-break:break-word;font-family:Lato,Tahoma,sans-serif;font-size:16px;width:100%;margin:0;padding:0\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                              <table class=\"m_-2027985233961450485email-body_inner\" style=\"width:570px;margin:0 auto;padding:0;background-color:#fff\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                                 <tbody>\n" +
                "                                    <tr>\n" +
                "                                       <td style=\"word-break:break-word;font-family:Lato,Tahoma,sans-serif;font-size:16px;padding:20px 45px\">\n" +
                "                                          <div>\n" +
                "                                             <h1 style=\"margin-top:0;color:#333;font-size:24px;font-weight:bold;text-align:center\">\n" +
                "                                                Thanks for signing up for Aerosimo Two Factor Authentication Account!\n" +
                "                                             </h1>\n" +
                "                                             <p style=\"color:#51545e;margin:0.4em 0 1.1875em;font-size:16px;line-height:1.625\">\n" +
                "                                                We're happy you're here. This is to verify your email by sending your temporary password as seen below:\n" +
                "                                             </p>\n" +
                "                                             <p style=\"color:blue;font-size:20px;text-align:center\">");
        message.append(generatedPassword);
        message.append("</p>\n" +
                "                                             <p style=\"color:#51545e;margin:0.4em 0 1.1875em;font-size:16px;line-height:1.625\">Use the above password to reset your password:</p>\n" +
                "                                          </div>\n" +
                "                                       </td>\n" +
                "                                    </tr>\n" +
                "                                 </tbody>\n" +
                "                              </table>\n" +
                "                           </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                     </tbody>\n" +
                "                  </table>\n" +
                "               </td>\n" +
                "            </tr>\n" +
                "         </tbody>\n" +
                "      </table>\n" +
                "   </div>\n" +
                "</div>");
        response = SendEmail.sendMail(emailAddress,"Authentication Temporary Password",message.toString(),null);
        Log.info("Email response is: " + response);
        return response;
    }
}