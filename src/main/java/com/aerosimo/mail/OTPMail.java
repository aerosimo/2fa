/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      OTPMail.java                                                    *
 * Created:   13/10/2021, 14:09                                               *
 * Modified:  13/10/2021, 14:09                                               *
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

import com.aerosimo.util.SendEmail;

public class OTPMail {
    public static String sendOTP(String userName, String authCode, String emailAddress) {
        StringBuilder message;
        message = new StringBuilder("<body style=\"margin:0;padding:0\">\n" +
                "   <div style=\"width:100%;min-width:320px;font-family:Helvetica,Arial;font-weight:bold;font-size:24px;background-color:#efefeb\">\n" +
                "      <div style=\"margin:0 auto;width:100%;max-width:598px;text-align:center;background-color:#242e30\">\n" +
                "         <div style=\"font-size:0;background-color:#242e30\">\n" +
                "            <div style=\"width:15%;display:inline-block;vertical-align:top\">\n" +
                "               <img style=\"width:100%;min-height:auto\" src=\"http://www.uberconference.com/static/img2/emails/announcement_l.gif\">\n" +
                "            </div>\n" +
                "            <div style=\"display:inline-block;width:70%;vertical-align:top;color:#f7f7f7;text-align:center\">\n" +
                "               <div style=\"font-size:42px;font-weight:bold;padding-bottom:12px;padding-top:25px\">");
        message.append(userName);
        message.append("</div>\n" +
                "               <hr style=\"width:80%;border:1px solid #2f6577\">\n" +
                "               <div style=\"font-size:24px;font-weight:normal;padding-top:12px;padding-bottom:6px\">One-Time Passcode (OTP)</div>\n" +
                "               <table style=\"margin:auto\">\n" +
                "                  <tbody>\n" +
                "                     <tr>\n" +
                "                        <td style=\"color:#afafaf;font-size:11px;padding:6px\">Your OTP is available below, please use this to validate your login</td>\n" +
                "                     </tr>\n" +
                "                  </tbody>\n" +
                "               </table>\n" +
                "               <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                  <tbody>\n" +
                "                     <tr>\n" +
                "                        <td width=\"100%\" style=\"padding-top:32px;text-align:center\" align=\"center\">\n" +
                "                           <table width=\"220px\" cellspacing=\"0\" cellpadding=\"0\" style=\"display:inline-block\">\n" +
                "                              <tbody>\n" +
                "                                 <tr>\n" +
                "                                    <td align=\"center\" width=\"220px\" height=\"70\" bgcolor=\"#28b777\" style=\"background:#28b777;border-radius:28px;color:#ffffff;display:block\">\n" +
                "                                       <h1 style=\"color:#ffffff;font-size:20px;font-family:Arial,sans-serif;text-decoration:none;line-height:42px;width:100%;display:inline-block\">");
        message.append(authCode);
        message.append("</h1>\n" +
                "                                    </td>\n" +
                "                                 </tr>\n" +
                "                              </tbody>\n" +
                "                           </table>\n" +
                "                        </td>\n" +
                "                     </tr>\n" +
                "                  </tbody>\n" +
                "               </table>\n" +
                "            </div>\n" +
                "            <div style=\"width:15%;display:inline-block;vertical-align:top\">\n" +
                "               <img style=\"width:100%;min-height:auto\" src=\"http://www.uberconference.com/static/img2/emails/announcement_r.gif\">\n" +
                "            </div>\n" +
                "         </div>\n" +
                "         <div style=\"text-align:center\">\n" +
                "            <img style=\"width:100%;min-height:auto;vertical-align:bottom\" src=\"http://www.uberconference.com/static/img2/emails/announcement_b.gif\">\n" +
                "         </div>\n" +
                "      </div>\n" +
                "   </div>");
        return SendEmail.sendMail(emailAddress,"Authentication Login Code",message.toString(),"");
    }
}