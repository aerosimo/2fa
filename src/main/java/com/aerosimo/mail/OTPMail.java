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
        message = new StringBuilder("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "  <style type=\"text/css\" media=\"screen\">\n" +
                "\n" +
                "    /* Force Hotmail to display emails at full width */\n" +
                "    .ExternalClass {\n" +
                "      display: block !important;\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "\n" +
                "    /* Force Hotmail to display normal line spacing */\n" +
                "    .ExternalClass,\n" +
                "    .ExternalClass p,\n" +
                "    .ExternalClass span,\n" +
                "    .ExternalClass font,\n" +
                "    .ExternalClass td,\n" +
                "    .ExternalClass div {\n" +
                "      line-height: 100%;\n" +
                "    }\n" +
                "\n" +
                "    body,\n" +
                "    p,\n" +
                "    h1,\n" +
                "    h2,\n" +
                "    h3,\n" +
                "    h4,\n" +
                "    h5,\n" +
                "    h6 {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    body,\n" +
                "    p,\n" +
                "    td {\n" +
                "      font-family: Arial, Helvetica, sans-serif;\n" +
                "      font-size: 15px;\n" +
                "      color: #333333;\n" +
                "      line-height: 1.5em;\n" +
                "    }\n" +
                "\n" +
                "    h1 {\n" +
                "      font-size: 24px;\n" +
                "      font-weight: normal;\n" +
                "      line-height: 24px;\n" +
                "    }\n" +
                "\n" +
                "    body,\n" +
                "    p {\n" +
                "      margin-bottom: 0;\n" +
                "      -webkit-text-size-adjust: none;\n" +
                "      -ms-text-size-adjust: none;\n" +
                "    }\n" +
                "\n" +
                "    img {\n" +
                "      outline: none;\n" +
                "      text-decoration: none;\n" +
                "      -ms-interpolation-mode: bicubic;\n" +
                "    }\n" +
                "\n" +
                "    a img {\n" +
                "      border: none;\n" +
                "    }\n" +
                "\n" +
                "    .background {\n" +
                "      background-color: #333333;\n" +
                "    }\n" +
                "\n" +
                "    table.background {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      width: 100% !important;\n" +
                "    }\n" +
                "\n" +
                "    .block-img {\n" +
                "      display: block;\n" +
                "      line-height: 0;\n" +
                "    }\n" +
                "\n" +
                "    a {\n" +
                "      color: white;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    a,\n" +
                "    a:link {\n" +
                "      color: #2A5DB0;\n" +
                "      text-decoration: underline;\n" +
                "    }\n" +
                "\n" +
                "    table td {\n" +
                "      border-collapse: collapse;\n" +
                "    }\n" +
                "\n" +
                "    td {\n" +
                "      vertical-align: top;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "\n" +
                "    .wrap {\n" +
                "      width: 600px;\n" +
                "    }\n" +
                "\n" +
                "    .wrap-cell {\n" +
                "      padding-top: 30px;\n" +
                "      padding-bottom: 30px;\n" +
                "    }\n" +
                "\n" +
                "    .header-cell,\n" +
                "    .body-cell,\n" +
                "    .footer-cell {\n" +
                "      padding-left: 20px;\n" +
                "      padding-right: 20px;\n" +
                "    }\n" +
                "\n" +
                "    .header-cell {\n" +
                "      background-color: #eeeeee;\n" +
                "      font-size: 24px;\n" +
                "      color: #ffffff;\n" +
                "    }\n" +
                "\n" +
                "    .body-cell {\n" +
                "      background-color: #ffffff;\n" +
                "      padding-top: 30px;\n" +
                "      padding-bottom: 34px;\n" +
                "    }\n" +
                "\n" +
                "    .footer-cell {\n" +
                "      background-color: #eeeeee;\n" +
                "      text-align: center;\n" +
                "      font-size: 13px;\n" +
                "      padding-top: 30px;\n" +
                "      padding-bottom: 30px;\n" +
                "    }\n" +
                "\n" +
                "    .card {\n" +
                "      width: 400px;\n" +
                "      margin: 0 auto;\n" +
                "    }\n" +
                "\n" +
                "    .data-heading {\n" +
                "      text-align: right;\n" +
                "      padding: 10px;\n" +
                "      background-color: #ffffff;\n" +
                "      font-weight: bold;\n" +
                "    }\n" +
                "\n" +
                "    .data-value {\n" +
                "      text-align: left;\n" +
                "      padding: 10px;\n" +
                "      background-color: #ffffff;\n" +
                "    }\n" +
                "\n" +
                "    .force-full-width {\n" +
                "      width: 100% !important;\n" +
                "    }\n" +
                "\n" +
                "  </style>\n" +
                "  <style type=\"text/css\" media=\"only screen and (max-width: 600px)\">\n" +
                "    @media only screen and (max-width: 600px) {\n" +
                "      body[class*=\"background\"],\n" +
                "      table[class*=\"background\"],\n" +
                "      td[class*=\"background\"] {\n" +
                "        background: #eeeeee !important;\n" +
                "      }\n" +
                "\n" +
                "      table[class=\"card\"] {\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "\n" +
                "      td[class=\"data-heading\"],\n" +
                "      td[class=\"data-value\"] {\n" +
                "        display: block !important;\n" +
                "      }\n" +
                "\n" +
                "      td[class=\"data-heading\"] {\n" +
                "        text-align: left !important;\n" +
                "        padding: 10px 10px 0;\n" +
                "      }\n" +
                "\n" +
                "      table[class=\"wrap\"] {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      td[class=\"wrap-cell\"] {\n" +
                "        padding-top: 0 !important;\n" +
                "        padding-bottom: 0 !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\" bgcolor=\"\" class=\"background\">\n" +
                "  <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" class=\"background\">\n" +
                "    <tr>\n" +
                "      <td align=\"center\" valign=\"top\" width=\"100%\" class=\"background\">\n" +
                "        <center>\n" +
                "          <table cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"wrap\">\n" +
                "            <tr>\n" +
                "              <td valign=\"top\" class=\"wrap-cell\" style=\"padding-top:30px; padding-bottom:30px;\">\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"force-full-width\">\n" +
                "                  <tr>\n" +
                "                    <td valign=\"top\" class=\"body-cell\">\n" +
                "\n" +
                "                      <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#ffffff\">\n" +
                "                        <tr>\n" +
                "                          <td valign=\"top\" style=\"padding-bottom:15px; background-color:#ffffff;\">\n" +
                "                            <h1>Aerosimo Two Factor Login</h1>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td valign=\"top\" style=\"padding-bottom:20px; background-color:#ffffff;\">\n" +
                "                            <b>Hey ");
        message.append(userName);
        message.append(", </b><br><br>\n" +
                "                            We're really excited that you've joined thousands of other customers who are enjoying this service!<br><br> You're just one click away from login in to your account. Please use your OTP code below to complete signing to your account.\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td>\n" +
                "                            <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#ffffff\">\n" +
                "                              <tr>\n" +
                "                                <td style=\"width:200px;background:#008000;\">\n" +
                "                                  <div>\n" +
                "                                  <p style=\"background-color:#008000;color:#ffffff;display:inline-block;font-family:sans-serif;font-size:18px;line-height:40px;text-align:center;text-decoration:none;width:200px;-webkit-text-size-adjust:none;\"><strong>");
        message.append(authCode);
        message.append("</strong></p>\n" +
                "                                  </div>\n" +
                "                                </td>\n" +
                "                                <td width=\"360\" style=\"background-color:#ffffff; font-size:0; line-height:0;\">&nbsp;</td>\n" +
                "                              </tr>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td style=\"padding-top:20px;background-color:#ffffff;\">\n" +
                "                            Thank you so much for compliance,<br>\n" +
                "                            <br>Aerosimo Support Team\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </table>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                  <tr>\n" +
                "                    <td valign=\"top\" class=\"footer-cell\">\n" +
                "                      &#9742; &nbsp;+44 012 34567 899<br>\t&#9993;&nbsp;support@aerosimo.com<br>\n" +
                "                     &copy; Aerosimo Ltd. <script>document.write(new Date().getFullYear());</script><br>\n" +
                "                      <span style=\"font-size:10px;\">This is a confidential email and may also be privileged. If you are not the intended recipient, please inform us immediately.</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </center>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>");
        return SendEmail.sendMail(emailAddress,"Authentication Login Code",message.toString(),"");
    }
}