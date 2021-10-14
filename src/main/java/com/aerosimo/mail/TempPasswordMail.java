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
        message = new StringBuilder("<!doctype html>\n" +
                "<html>\n" +
                "   <head>\n" +
                "      <meta charset=\"UTF-8\">\n" +
                "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "      <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "      <style type=\"text/css\">\n" +
                "         html,  body {\n" +
                "         margin: 0 !important;\n" +
                "         padding: 0 !important;\n" +
                "         height: 100% !important;\n" +
                "         width: 100% !important;\n" +
                "         }\n" +
                "         * {\n" +
                "         -ms-text-size-adjust: 100%;\n" +
                "         -webkit-text-size-adjust: 100%;\n" +
                "         }\n" +
                "         .ExternalClass {\n" +
                "         width: 100%;\n" +
                "         }\n" +
                "         div[style*=\"margin: 16px 0\"] {\n" +
                "         margin: 0 !important;\n" +
                "         }\n" +
                "         table,  td {\n" +
                "         mso-table-lspace: 0pt !important;\n" +
                "         mso-table-rspace: 0pt !important;\n" +
                "         }\n" +
                "         table {\n" +
                "         border-spacing: 0 !important;\n" +
                "         border-collapse: collapse !important;\n" +
                "         table-layout: fixed !important;\n" +
                "         margin: 0 auto !important;\n" +
                "         }\n" +
                "         table table table {\n" +
                "         table-layout: auto;\n" +
                "         }\n" +
                "         img {\n" +
                "         -ms-interpolation-mode: bicubic;\n" +
                "         }\n" +
                "         .yshortcuts a {\n" +
                "         border-bottom: none !important;\n" +
                "         }\n" +
                "         a[x-apple-data-detectors] {\n" +
                "         color: inherit !important;\n" +
                "         }\n" +
                "         @media (max-width: 800px){\n" +
                "         }\n" +
                "      </style>\n" +
                "      <style type=\"text/css\">\n" +
                "         .button-td,\n" +
                "         .button-a {\n" +
                "         transition: all 100ms ease-in;\n" +
                "         }\n" +
                "         .button-td:hover,\n" +
                "         .button-a:hover {\n" +
                "         background: #555555 !important;\n" +
                "         border-color: #555555 !important;\n" +
                "         }\n" +
                "         @media screen and (max-width : 480px ){\n" +
                "         .email-container {\n" +
                "         width: 100% !important;\n" +
                "         }\n" +
                "         .fluid,\n" +
                "         .fluid-centered {\n" +
                "         max-width: 100% !important;\n" +
                "         height: auto !important;\n" +
                "         margin-left: auto !important;\n" +
                "         margin-right: auto !important;\n" +
                "         }\n" +
                "         .fluid-centered {\n" +
                "         margin-left: auto !important;\n" +
                "         margin-right: auto !important;\n" +
                "         }\n" +
                "         .stack-column,\n" +
                "         .stack-column-center {\n" +
                "         display: block !important;\n" +
                "         width: 100% !important;\n" +
                "         max-width: 100% !important;\n" +
                "         direction: ltr !important;\n" +
                "         }\n" +
                "         .stack-column-center {\n" +
                "         text-align: center !important;\n" +
                "         }\n" +
                "         .center-on-narrow {\n" +
                "         text-align: center !important;\n" +
                "         display: block !important;\n" +
                "         margin-left: auto !important;\n" +
                "         margin-right: auto !important;\n" +
                "         float: none !important;\n" +
                "         }\n" +
                "         table.center-on-narrow {\n" +
                "         display: inline-block !important;\n" +
                "         }\n" +
                "         }\n" +
                "      </style>\n" +
                "   </head>\n" +
                "   <body bgcolor=\"#e0e0e0\" width=\"100%\" style=\"margin: 0;\" yahoo=\"yahoo\">\n" +
                "      <table bgcolor=\"#e0e0e0\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" height=\"100%\" width=\"100%\" style=\"border-collapse:collapse;\">\n" +
                "         <tr>\n" +
                "            <td>\n" +
                "               <center style=\"width: 100%;\">\n" +
                "                  <table align=\"center\" width=\"600\" class=\"email-container\">\n" +
                "                     <img src=\"http://inodeink.com/iknow/wp-content/uploads/sites/2/2019/05/AdobeStock_96460260-e1557941197369.jpeg\" width=\"600\" height=\"292\" alt=\"Welcome Aboard\" border=\"0\">\n" +
                "                     <tr>\n" +
                "                        <td style=\"padding: 0px 0; text-align: center\"></td>\n" +
                "                     </tr>\n" +
                "                  </table>\n" +
                "                  <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#ffffff\" width=\"600\" class=\"email-container\">\n" +
                "                     <tr>\n" +
                "                        <td style=\"padding: 20px; text-align: left; font-family: georgia; font-size: 15px; mso-height-rule: exactly; line-height: 20px; color: #555555;\">\n" +
                "                           <p>Welcome to Aerosimo</p>\n" +
                "                           <p>We're really excited that you've joined thousands of other customers who are enjoying this service!<br><br> You're just one click away from login in to your account. <br>Please use the generated temporary password below to complete signing to your account.</p>\n" +
                "                           <p><strong>");
        message.append(generatedPassword);
        message.append("</strong></p>\n" +
                "                           <p>Thank you so much for compliance, </p>\n" +
                "                           <p>Aerosimo Support Team</p>\n" +
                "                        </td>\n" +
                "                     </tr>\n" +
                "                     <tr>\n" +
                "                        <td background=\"https://inodeink.com/old/wp-content/uploads/sites/1/2018/04/Bottom-Bar.jpg\" background_size=\"cover\" align=\"center\" valign=\"top\" style=\"padding: 10px;\">\n" +
                "                           <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                "                              <tr>\n" +
                "                                 <td style=\"padding: 10px; text-align: center\">\n" +
                "                                    &#9742; &nbsp;+44 012 34567 899<br>\t&#9993;&nbsp;support@aerosimo.com<br>\n" +
                "                                    &copy; Aerosimo Ltd. <script>document.write(new Date().getFullYear());</script><br>\n" +
                "                                    <span style=\"font-size:10px;\">This is a confidential email and may also be privileged. If you are not the intended recipient, please inform us immediately.</span>\n" +
                "                                 </td>\n" +
                "                              </tr>\n" +
                "                           </table>\n" +
                "                     <tr></td>\n" +
                "                     </tr>\n" +
                "                  </table>\n" +
                "            </td>\n" +
                "         </tr>\n" +
                "      </table>\n" +
                "      </td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "      <td dir=\"rtl\" align=\"center\" valign=\"top\" width=\"100%\" style=\"padding: 10px;\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "      </tr>\n" +
                "      </table></td>\n" +
                "      </tr>\n" +
                "      </table>\n" +
                "      </center></td>\n" +
                "      </tr>\n" +
                "      </table>\n" +
                "   </body>\n" +
                "</html>");
        response = SendEmail.sendMail(emailAddress,"Authentication Temporary Password",message.toString(),"");
        Log.info("Email response is: " + response);
        return response;
    }
}