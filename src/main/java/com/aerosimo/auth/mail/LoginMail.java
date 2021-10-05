/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      LoginMail.java                                                  *
 * Created:   05/10/2021, 23:14                                               *
 * Modified:  05/10/2021, 23:14                                               *
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

package com.aerosimo.auth.mail;

import com.aerosimo.util.SendEmail;

public class LoginMail {


    public static String sendOTPMail(String userName, String emailAddress, String authCode) {
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
                "                   <td height=\"60\" valign=\"top\" class=\"header-cell\">\n" +
                "                      <img width=\"100%\" height=\"150\" src=\"data:image/png;base64,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\" alt=\"logo\">\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                  <tr>\n" +
                "                    <td valign=\"top\" class=\"body-cell\">\n" +
                "\n" +
                "                      <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#ffffff\">\n" +
                "                        <tr>\n" +
                "                          <td valign=\"top\" style=\"padding-bottom:15px; background-color:#ffffff;\">\n" +
                "                            <h1>Aerosimo MultiFactor Login</h1>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td valign=\"top\" style=\"padding-bottom:20px; background-color:#ffffff;\">\n" +
                "                            <b>Hey ");
        message.append(userName);
        message.append(", welcome back!</b><br><br>\n" +
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
        SendEmail.sendMail(emailAddress, "Welcome Back to Ominet", message.toString(),null);
        return "Success";
    }
}