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

import com.aerosimo.util.SendEmail;

public class TempPasswordMail {
    public static String sendPasswordMail(String generatedPassword, String emailAddress) {
        StringBuilder message;
        message = new StringBuilder("<html>\n" +
                "   <head>\n" +
                "      <meta charset=\"utf-8\">\n" +
                "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "      <link rel=\"stylesheet\" href=\"https://use.typekit.net/dwl0yuy.css\">\n" +
                "      <style type=\"text/css\">\n" +
                "         div, p, a, li, td {\n" +
                "         -webkit-text-size-adjust: none;\n" +
                "         font-family: objektiv-mk3, sans-serif;\n" +
                "         }\n" +
                "         body {\n" +
                "         font-family: objektiv-mk3, sans-serif;\n" +
                "         letter-spacing: -0.5px;\n" +
                "         }\n" +
                "         table {\n" +
                "         mso-table-lspace: 0pt !important;\n" +
                "         mso-table-rspace: 0pt !important;\n" +
                "         }\n" +
                "         img {\n" +
                "         display: block;\n" +
                "         }\n" +
                "         a {\n" +
                "         text-decoration: none;\n" +
                "         font-family: objektiv-mk3, sans-serif;\n" +
                "         color: inherit !important;\n" +
                "         }\n" +
                "         p {\n" +
                "         margin: 0px;\n" +
                "         padding: 0px;\n" +
                "         font-family: inherit;\n" +
                "         }\n" +
                "         .tpl-content {\n" +
                "         padding: 0px !important;\n" +
                "         }\n" +
                "         .width_100 {\n" +
                "         width: 100%;\n" +
                "         }\n" +
                "         img {\n" +
                "         max-width: 100%;\n" +
                "         height: auto;\n" +
                "         }\n" +
                "         @media only screen and (max-width: 820px) {\n" +
                "         .width_100 {\n" +
                "         width: 100%;\n" +
                "         }\n" +
                "         }\n" +
                "         @media only screen and (max-width: 720px) {\n" +
                "         .img-center img {\n" +
                "         margin: 0 auto !important;\n" +
                "         }\n" +
                "         .img-right img {\n" +
                "         float: none !important;\n" +
                "         text-align: right;\n" +
                "         text-align: -webkit-right;\n" +
                "         }\n" +
                "         .img-left img {\n" +
                "         float: none !important;\n" +
                "         text-align: left;\n" +
                "         text-align: -webkit-left;\n" +
                "         }\n" +
                "         .erase {\n" +
                "         display: none;\n" +
                "         height: 0px;\n" +
                "         }\n" +
                "         .text-center {\n" +
                "         float: none !important;\n" +
                "         text-align: center;\n" +
                "         text-align: -webkit-center;\n" +
                "         }\n" +
                "         .text-left {\n" +
                "         float: none !important;\n" +
                "         text-align: left;\n" +
                "         text-align: -webkit-left;\n" +
                "         }\n" +
                "         .text-right {\n" +
                "         float: none !important;\n" +
                "         text-align: right;\n" +
                "         text-align: -webkit-right;\n" +
                "         }\n" +
                "         .padding-top {\n" +
                "         padding-top: 10px;\n" +
                "         }\n" +
                "         .padding-top-60 {\n" +
                "         padding-top: 60px !important;\n" +
                "         height: auto;\n" +
                "         display: block;\n" +
                "         }\n" +
                "         .padding-bottom-60 {\n" +
                "         padding-bottom: 60px !important;\n" +
                "         height: auto;\n" +
                "         display: block;\n" +
                "         }\n" +
                "         }\n" +
                "         @media only screen and (max-width: 420px) {\n" +
                "         .width_100percent {\n" +
                "         width: 100% !important;\n" +
                "         max-width: 100%;\n" +
                "         margin: 0 auto !important;\n" +
                "         height: auto!important;\n" +
                "         }\n" +
                "         .width_90percent {\n" +
                "         width: 90% !important;\n" +
                "         max-width: 90%;\n" +
                "         margin: 0 auto !important;\n" +
                "         height: auto!important;\n" +
                "         }\n" +
                "         .display-block {\n" +
                "         display: block !important;\n" +
                "         height: auto !important;\n" +
                "         margin: 0 auto !important;\n" +
                "         width: 100% !important;\n" +
                "         padding-left: 0px !important;\n" +
                "         padding-right: 0px !important;\n" +
                "         }\n" +
                "         .full-width-img img {\n" +
                "         width: 100%;\n" +
                "         height: auto;\n" +
                "         }\n" +
                "         .logo-icon img {\n" +
                "         max-width: 75%;\n" +
                "         height: auto;\n" +
                "         }\n" +
                "         .padding {\n" +
                "         padding: 10px 0px;\n" +
                "         }\n" +
                "         br {\n" +
                "         display: none;\n" +
                "         }\n" +
                "         }\n" +
                "      </style>\n" +
                "   </head>\n" +
                "   <body style=\"margin:0px; padding:0px;\">\n" +
                "      <table class=\"width_100\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"800\" style=\"border-collapse:collapse;\">\n" +
                "         <tbody>\n" +
                "            <tr>\n" +
                "               <td width=\"100%\" height=\"20\" style=\"line-height:1px;\"></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "               <td width=\"100%\" height=\"20\" style=\"line-height:1px;\"></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "               <td width=\"100%\" align=\"center\" valign=\"middle\">\n" +
                "                  <table class=\"width_90percent\" align=\"center\" border=\"0\" cellpadding=\"10\" width=\"500\" cellspacing=\"10\" style=\"border-collapse: collapse; max-width:90%; -webkit-border-radius: 10px; border-radius: 10px;\" bgcolor=\"#ffffff\">\n" +
                "                     <tbody>\n" +
                "                        <td width=\"100%\" align=\"center\" valign=\"middle\" style=\"margin:0px; padding:10px; font-size:25px; color:#000000; objektiv-mk3, sans-serif; font-weight:bold; line-height:24px; padding-top: 25px;\"><b>Aerosimo 2FA!</b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                           <td class=\"erase\" width=\"100%\" height=\"20\" style=\"line-height:1px;\"></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                           <td width=\"100%\" align=\"center\" valign=\"middle\" style=\"margin:0px; padding:0px; font-size:14px; color:#111111; font-family: objektiv-mk3, sans-serif; font-weight:normal; line-height:24px;\"><b><br>We are happy you sign up to Aerosimo. <br>This is to verify  your email. <br>Find your temporary password below, <br>you are almost there!</td>\n" +
                "                           <br>\n" +
                "                        </tr>\n" +
                "                     </tbody>\n" +
                "                  </table>\n" +
                "               </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "               <td class=\"display-block padding\" width=\"100%\" height=\"8\" style=\"line-height:1px;\"></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "               <td class=\"display-block padding\" width=\"100%\" height=\"8\" style=\"line-height:1px;\"></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "               <td width=\"100%\" align=\"center\" valign=\"middle\">\n" +
                "                  <table class=\"width_90percent\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"255\" style=\"border-collapse:collapse; max-width:100%; -webkit-border-radius:4px; border-radius:4px;\" bgcolor=\"#da4235\">\n" +
                "                     <tbody>\n" +
                "                        <tr>\n" +
                "                           <td width=\"100%\" align=\"center\" style=\"max-width:255px; margin:0px; padding: 8px 20px; font-size:14px; color:#FFFFFF; font-family:objektiv-mk3, sans-serif; font-weight:Bold;\">\n" +
                "                              <a href=\"https://rayonbagage.com/\" target=\"_blank\" style=\"width:100%; color:#FFFFFF; font-family: objektiv-mk3, sans-serif; text-decoration:none; display:block;\">");
        message.append(generatedPassword);
        message.append("</a>\n" +
                "                           </td>\n" +
                "                        </tr>\n" +
                "                  </table>\n" +
                "               </td>\n" +
                "            </tr>\n" +
                "         </tbody>\n" +
                "      </table>\n" +
                "      </td>\n" +
                "      </tr>\n" +
                "      </tbody>\n" +
                "      </table>\n" +
                "      </td>\n" +
                "      </tr>\n" +
                "      </tbody>\n" +
                "      </table>");
        return SendEmail.sendMail(emailAddress,"Authentication Temporary Password",message.toString(),null);
    }
}