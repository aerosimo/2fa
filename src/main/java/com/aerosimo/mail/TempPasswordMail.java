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
        message = new StringBuilder("<body style=\"-moz-box-sizing: border-box; -ms-text-size-adjust: 100%; -webkit-box-sizing: border-box; -webkit-text-size-adjust: 100%; box-sizing: border-box; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 22px; margin: 0; min-width: 100%; padding: 0; text-align: left; width: 100% !important\"><style type=\"text/css\">\n" +
                "body {\n" +
                "width: 100% !important; min-width: 100%; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; margin: 0; padding: 0; -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "width: 100%;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "line-height: 100%;\n" +
                "}\n" +
                "#backgroundTable {\n" +
                "margin: 0; padding: 0; width: 100% !important; line-height: 100% !important;\n" +
                "}\n" +
                "img {\n" +
                "outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; width: auto; max-width: 100%; clear: both; display: block;\n" +
                "}\n" +
                "body {\n" +
                "color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-weight: normal; padding: 0; margin: 0; text-align: left; line-height: 1.3;\n" +
                "}\n" +
                "body {\n" +
                "font-size: 16px; line-height: 1.3;\n" +
                "}\n" +
                "a:hover {\n" +
                "color: #1f54ed;\n" +
                "}\n" +
                "a:active {\n" +
                "color: #1f54ed;\n" +
                "}\n" +
                "a:visited {\n" +
                "color: #4E78F1;\n" +
                "}\n" +
                "h1 a:visited {\n" +
                "color: #4E78F1;\n" +
                "}\n" +
                "h2 a:visited {\n" +
                "color: #4E78F1;\n" +
                "}\n" +
                "h3 a:visited {\n" +
                "color: #4E78F1;\n" +
                "}\n" +
                "h4 a:visited {\n" +
                "color: #4E78F1;\n" +
                "}\n" +
                "h5 a:visited {\n" +
                "color: #4E78F1;\n" +
                "}\n" +
                "h6 a:visited {\n" +
                "color: #4E78F1;\n" +
                "}\n" +
                "table.button:hover table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button:active table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button table tr td a:visited {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.tiny:hover table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.tiny:active table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.tiny table tr td a:visited {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.small:hover table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.small:active table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.small table tr td a:visited {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.large:hover table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.large:active table tr td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.large table tr td a:visited {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button:hover table td {\n" +
                "background: #1f54ed; color: #FFFFFF;\n" +
                "}\n" +
                "table.button:visited table td {\n" +
                "background: #1f54ed; color: #FFFFFF;\n" +
                "}\n" +
                "table.button:active table td {\n" +
                "background: #1f54ed; color: #FFFFFF;\n" +
                "}\n" +
                "table.button:hover table a {\n" +
                "border: 0 solid #1f54ed;\n" +
                "}\n" +
                "table.button:visited table a {\n" +
                "border: 0 solid #1f54ed;\n" +
                "}\n" +
                "table.button:active table a {\n" +
                "border: 0 solid #1f54ed;\n" +
                "}\n" +
                "table.button.secondary:hover table td {\n" +
                "background: #fefefe; color: #FFFFFF;\n" +
                "}\n" +
                "table.button.secondary:hover table a {\n" +
                "border: 0 solid #fefefe;\n" +
                "}\n" +
                "table.button.secondary:hover table td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.secondary:active table td a {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.secondary table td a:visited {\n" +
                "color: #FFFFFF;\n" +
                "}\n" +
                "table.button.success:hover table td {\n" +
                "background: #009482;\n" +
                "}\n" +
                "table.button.success:hover table a {\n" +
                "border: 0 solid #009482;\n" +
                "}\n" +
                "table.button.alert:hover table td {\n" +
                "background: #ff6131;\n" +
                "}\n" +
                "table.button.alert:hover table a {\n" +
                "border: 0 solid #ff6131;\n" +
                "}\n" +
                "table.button.warning:hover table td {\n" +
                "background: #fcae1a;\n" +
                "}\n" +
                "table.button.warning:hover table a {\n" +
                "border: 0px solid #fcae1a;\n" +
                "}\n" +
                ".thumbnail:hover {\n" +
                "box-shadow: 0 0 6px 1px rgba(78,120,241,0.5);\n" +
                "}\n" +
                ".thumbnail:focus {\n" +
                "box-shadow: 0 0 6px 1px rgba(78,120,241,0.5);\n" +
                "}\n" +
                "body.outlook p {\n" +
                "display: inline !important;\n" +
                "}\n" +
                "body {\n" +
                "font-weight: normal; font-size: 16px; line-height: 22px;\n" +
                "}\n" +
                "@media only screen and (max-width: 596px) {\n" +
                "  .small-float-center {\n" +
                "    margin: 0 auto !important; float: none !important; text-align: center !important;\n" +
                "  }\n" +
                "  .small-text-center {\n" +
                "    text-align: center !important;\n" +
                "  }\n" +
                "  .small-text-left {\n" +
                "    text-align: left !important;\n" +
                "  }\n" +
                "  .small-text-right {\n" +
                "    text-align: right !important;\n" +
                "  }\n" +
                "  .hide-for-large {\n" +
                "    display: block !important; width: auto !important; overflow: visible !important; max-height: none !important; font-size: inherit !important; line-height: inherit !important;\n" +
                "  }\n" +
                "  table.body table.container .hide-for-large {\n" +
                "    display: table !important; width: 100% !important;\n" +
                "  }\n" +
                "  table.body table.container .row.hide-for-large {\n" +
                "    display: table !important; width: 100% !important;\n" +
                "  }\n" +
                "  table.body table.container .callout-inner.hide-for-large {\n" +
                "    display: table-cell !important; width: 100% !important;\n" +
                "  }\n" +
                "  table.body table.container .show-for-large {\n" +
                "    display: none !important; width: 0; mso-hide: all; overflow: hidden;\n" +
                "  }\n" +
                "  table.body img {\n" +
                "    width: auto; height: auto;\n" +
                "  }\n" +
                "  table.body center {\n" +
                "    min-width: 0 !important;\n" +
                "  }\n" +
                "  table.body .container {\n" +
                "    width: 95% !important;\n" +
                "  }\n" +
                "  table.body .columns {\n" +
                "    height: auto !important; -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; padding-left: 16px !important; padding-right: 16px !important;\n" +
                "  }\n" +
                "  table.body .column {\n" +
                "    height: auto !important; -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; padding-left: 16px !important; padding-right: 16px !important;\n" +
                "  }\n" +
                "  table.body .columns .column {\n" +
                "    padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  table.body .columns .columns {\n" +
                "    padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  table.body .column .column {\n" +
                "    padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  table.body .column .columns {\n" +
                "    padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  table.body .collapse .columns {\n" +
                "    padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  table.body .collapse .column {\n" +
                "    padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  td.small-1 {\n" +
                "    display: inline-block !important; width: 8.333333% !important;\n" +
                "  }\n" +
                "  th.small-1 {\n" +
                "    display: inline-block !important; width: 8.333333% !important;\n" +
                "  }\n" +
                "  td.small-2 {\n" +
                "    display: inline-block !important; width: 16.666666% !important;\n" +
                "  }\n" +
                "  th.small-2 {\n" +
                "    display: inline-block !important; width: 16.666666% !important;\n" +
                "  }\n" +
                "  td.small-3 {\n" +
                "    display: inline-block !important; width: 25% !important;\n" +
                "  }\n" +
                "  th.small-3 {\n" +
                "    display: inline-block !important; width: 25% !important;\n" +
                "  }\n" +
                "  td.small-4 {\n" +
                "    display: inline-block !important; width: 33.333333% !important;\n" +
                "  }\n" +
                "  th.small-4 {\n" +
                "    display: inline-block !important; width: 33.333333% !important;\n" +
                "  }\n" +
                "  td.small-5 {\n" +
                "    display: inline-block !important; width: 41.666666% !important;\n" +
                "  }\n" +
                "  th.small-5 {\n" +
                "    display: inline-block !important; width: 41.666666% !important;\n" +
                "  }\n" +
                "  td.small-6 {\n" +
                "    display: inline-block !important; width: 50% !important;\n" +
                "  }\n" +
                "  th.small-6 {\n" +
                "    display: inline-block !important; width: 50% !important;\n" +
                "  }\n" +
                "  td.small-7 {\n" +
                "    display: inline-block !important; width: 58.333333% !important;\n" +
                "  }\n" +
                "  th.small-7 {\n" +
                "    display: inline-block !important; width: 58.333333% !important;\n" +
                "  }\n" +
                "  td.small-8 {\n" +
                "    display: inline-block !important; width: 66.666666% !important;\n" +
                "  }\n" +
                "  th.small-8 {\n" +
                "    display: inline-block !important; width: 66.666666% !important;\n" +
                "  }\n" +
                "  td.small-9 {\n" +
                "    display: inline-block !important; width: 75% !important;\n" +
                "  }\n" +
                "  th.small-9 {\n" +
                "    display: inline-block !important; width: 75% !important;\n" +
                "  }\n" +
                "  td.small-10 {\n" +
                "    display: inline-block !important; width: 83.333333% !important;\n" +
                "  }\n" +
                "  th.small-10 {\n" +
                "    display: inline-block !important; width: 83.333333% !important;\n" +
                "  }\n" +
                "  td.small-11 {\n" +
                "    display: inline-block !important; width: 91.666666% !important;\n" +
                "  }\n" +
                "  th.small-11 {\n" +
                "    display: inline-block !important; width: 91.666666% !important;\n" +
                "  }\n" +
                "  td.small-12 {\n" +
                "    display: inline-block !important; width: 100% !important;\n" +
                "  }\n" +
                "  th.small-12 {\n" +
                "    display: inline-block !important; width: 100% !important;\n" +
                "  }\n" +
                "  .columns td.small-12 {\n" +
                "    display: block !important; width: 100% !important;\n" +
                "  }\n" +
                "  .column td.small-12 {\n" +
                "    display: block !important; width: 100% !important;\n" +
                "  }\n" +
                "  .columns th.small-12 {\n" +
                "    display: block !important; width: 100% !important;\n" +
                "  }\n" +
                "  .column th.small-12 {\n" +
                "    display: block !important; width: 100% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-1 {\n" +
                "    margin-left: 8.333333% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-1 {\n" +
                "    margin-left: 8.333333% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-2 {\n" +
                "    margin-left: 16.666666% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-2 {\n" +
                "    margin-left: 16.666666% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-3 {\n" +
                "    margin-left: 25% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-3 {\n" +
                "    margin-left: 25% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-4 {\n" +
                "    margin-left: 33.333333% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-4 {\n" +
                "    margin-left: 33.333333% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-5 {\n" +
                "    margin-left: 41.666666% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-5 {\n" +
                "    margin-left: 41.666666% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-6 {\n" +
                "    margin-left: 50% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-6 {\n" +
                "    margin-left: 50% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-7 {\n" +
                "    margin-left: 58.333333% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-7 {\n" +
                "    margin-left: 58.333333% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-8 {\n" +
                "    margin-left: 66.666666% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-8 {\n" +
                "    margin-left: 66.666666% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-9 {\n" +
                "    margin-left: 75% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-9 {\n" +
                "    margin-left: 75% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-10 {\n" +
                "    margin-left: 83.333333% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-10 {\n" +
                "    margin-left: 83.333333% !important;\n" +
                "  }\n" +
                "  table.body td.small-offset-11 {\n" +
                "    margin-left: 91.666666% !important;\n" +
                "  }\n" +
                "  table.body th.small-offset-11 {\n" +
                "    margin-left: 91.666666% !important;\n" +
                "  }\n" +
                "  table.body table.columns td.expander {\n" +
                "    display: none !important;\n" +
                "  }\n" +
                "  table.body table.columns th.expander {\n" +
                "    display: none !important;\n" +
                "  }\n" +
                "  table.body .right-text-pad {\n" +
                "    padding-left: 10px !important;\n" +
                "  }\n" +
                "  table.body .text-pad-right {\n" +
                "    padding-left: 10px !important;\n" +
                "  }\n" +
                "  table.body .left-text-pad {\n" +
                "    padding-right: 10px !important;\n" +
                "  }\n" +
                "  table.body .text-pad-left {\n" +
                "    padding-right: 10px !important;\n" +
                "  }\n" +
                "  table.menu {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "  table.menu td {\n" +
                "    width: auto !important; display: inline-block !important;\n" +
                "  }\n" +
                "  table.menu th {\n" +
                "    width: auto !important; display: inline-block !important;\n" +
                "  }\n" +
                "  table.menu.vertical td {\n" +
                "    display: block !important;\n" +
                "  }\n" +
                "  table.menu.vertical th {\n" +
                "    display: block !important;\n" +
                "  }\n" +
                "  table.menu.small-vertical td {\n" +
                "    display: block !important;\n" +
                "  }\n" +
                "  table.menu.small-vertical th {\n" +
                "    display: block !important;\n" +
                "  }\n" +
                "  table.menu[align=\"center\"] {\n" +
                "    width: auto !important;\n" +
                "  }\n" +
                "  table.button.small-expand {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "  table.button.small-expanded {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "  table.button.small-expand table {\n" +
                "    width: 100%;\n" +
                "  }\n" +
                "  table.button.small-expanded table {\n" +
                "    width: 100%;\n" +
                "  }\n" +
                "  table.button.small-expand table a {\n" +
                "    text-align: center !important; width: 100% !important; padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  table.button.small-expanded table a {\n" +
                "    text-align: center !important; width: 100% !important; padding-left: 0 !important; padding-right: 0 !important;\n" +
                "  }\n" +
                "  table.button.small-expand center {\n" +
                "    min-width: 0;\n" +
                "  }\n" +
                "  table.button.small-expanded center {\n" +
                "    min-width: 0;\n" +
                "  }\n" +
                "  table.body .container {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "}\n" +
                "@media only screen and (min-width: 732px) {\n" +
                "  table.body table.milkyway-email-card {\n" +
                "    width: 525px !important;\n" +
                "  }\n" +
                "  table.body table.emailer-footer {\n" +
                "    width: 525px !important;\n" +
                "  }\n" +
                "}\n" +
                "@media only screen and (max-width: 731px) {\n" +
                "  table.body table.milkyway-email-card {\n" +
                "    width: 320px !important;\n" +
                "  }\n" +
                "  table.body table.emailer-footer {\n" +
                "    width: 320px !important;\n" +
                "  }\n" +
                "}\n" +
                "@media only screen and (max-width: 320px) {\n" +
                "  table.body table.milkyway-email-card {\n" +
                "    width: 100% !important; border-radius: 0; box-sizing: none;\n" +
                "  }\n" +
                "  table.body table.emailer-footer {\n" +
                "    width: 100% !important; border-radius: 0; box-sizing: none;\n" +
                "  }\n" +
                "}\n" +
                "@media only screen and (max-width: 280px) {\n" +
                "  table.body table.milkyway-email-card .milkyway-content {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "}\n" +
                "@media (min-width: 596px) {\n" +
                "  .milkyway-header {\n" +
                "    width: 11%;\n" +
                "  }\n" +
                "}\n" +
                "@media (max-width: 596px) {\n" +
                "  .milkyway-header {\n" +
                "    width: 50%;\n" +
                "  }\n" +
                "  .emailer-footer .emailer-border-bottom {\n" +
                "    border-bottom: 0.5px solid #E2E5E7;\n" +
                "  }\n" +
                "  .emailer-footer .make-you-smile {\n" +
                "    margin-top: 24px;\n" +
                "  }\n" +
                "  .emailer-footer .make-you-smile .email-tag-line {\n" +
                "    width: 80%; position: relative; left: 10%;\n" +
                "  }\n" +
                "  .emailer-footer .make-you-smile .universe-address {\n" +
                "    margin-bottom: 10px !important;\n" +
                "  }\n" +
                "  .emailer-footer .make-you-smile .email-tag-line {\n" +
                "    margin-bottom: 10px !important;\n" +
                "  }\n" +
                "  .have-questions-text {\n" +
                "    width: 70%;\n" +
                "  }\n" +
                "  .hide-on-small {\n" +
                "    display: none;\n" +
                "  }\n" +
                "  .product-card-stacked-row .thumbnail-image {\n" +
                "    max-width: 32% !important;\n" +
                "  }\n" +
                "  .product-card-stacked-row .thumbnail-content p {\n" +
                "    width: 64%;\n" +
                "  }\n" +
                "  .welcome-subcontent {\n" +
                "    text-align: left; margin: 20px 0 10px;\n" +
                "  }\n" +
                "  .milkyway-title {\n" +
                "    padding: 16px;\n" +
                "  }\n" +
                "  .meta-data {\n" +
                "    text-align: center;\n" +
                "  }\n" +
                "  .label {\n" +
                "    text-align: center;\n" +
                "  }\n" +
                "  .welcome-email .wavey-background-subcontent {\n" +
                "    width: calc(100% - 32px);\n" +
                "  }\n" +
                "}\n" +
                "@media (min-width: 597px) {\n" +
                "  .emailer-footer .show-on-mobile {\n" +
                "    display: none;\n" +
                "  }\n" +
                "  .emailer-footer .emailer-border-bottom {\n" +
                "    border-bottom: none;\n" +
                "  }\n" +
                "  .have-questions-text {\n" +
                "    border-bottom: none;\n" +
                "  }\n" +
                "  .hide-on-large {\n" +
                "    display: none;\n" +
                "  }\n" +
                "  .milkyway-title {\n" +
                "    padding: 55px 55px 16px;\n" +
                "  }\n" +
                "}\n" +
                "@media only screen and (max-width: 290px) {\n" +
                "  table.container.your-tickets .tickets-container {\n" +
                "    width: 100%;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n" +
                "    <table class=\"body\" data-made-with-foundation=\"\" style=\"background: #FAFAFA; border-collapse: collapse; border-spacing: 0; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; height: 100%; line-height: 1.3; margin: 0; padding: 0; text-align: left; vertical-align: top; width: 100%\" bgcolor=\"#FAFAFA\">\n" +
                "      <tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\">\n" +
                "        <td class=\"center\" align=\"center\" valign=\"top\" style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 1.3; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\">\n" +
                "          <center style=\"min-width: 580px; width: 100%\">\n" +
                "            <table class=\" spacer  float-center\" align=\"center\" style=\"border-collapse: collapse; border-spacing: 0; float: none; margin: 0 auto; padding: 0; text-align: center; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td height=\"20px\" style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 20px; font-weight: normal; hyphens: auto; line-height: 20px; margin: 0; mso-line-height-rule: exactly; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\">&nbsp;</td></tr></tbody></table>\n" +
                "            <table class=\"header-spacer spacer  float-center\" align=\"center\" style=\"border-collapse: collapse; border-spacing: 0; float: none; line-height: 60px; margin: 0 auto; padding: 0; text-align: center; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td height=\"16px\" style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 16px; margin: 0; mso-line-height-rule: exactly; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\">&nbsp;</td></tr></tbody></table>\n" +
                "\n" +
                "<table class=\"header-spacer-bottom spacer  float-center\" align=\"center\" style=\"border-collapse: collapse; border-spacing: 0; float: none; line-height: 30px; margin: 0 auto; padding: 0; text-align: center; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td height=\"16px\" style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 16px; margin: 0; mso-line-height-rule: exactly; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\">&nbsp;</td></tr></tbody></table>\n" +
                "\n" +
                "            <table class=\"milkyway-email-card container float-center\" align=\"center\" style=\"background: #FFFFFF; border-collapse: collapse; border-radius: 6px; border-spacing: 0; box-shadow: 0 1px 8px 0 rgba(28,35,43,0.15); float: none; margin: 0 auto; overflow: hidden; padding: 0; text-align: center; vertical-align: top; width: 580px\" bgcolor=\"#FFFFFF\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 1.3; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\">\n" +
                "              \n" +
                "              <table class=\"milkyway-content welcome-email container\" align=\"center\" style=\"background: #FFFFFF; border-collapse: collapse; border-spacing: 0; hyphens: none; margin: auto; max-width: 100%; padding: 0; text-align: inherit; vertical-align: top; width: 280px !important\" bgcolor=\"#FFFFFF\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 1.3; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\">\n" +
                "<table class=\" spacer \" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td height=\"50px\" style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 50px; font-weight: normal; hyphens: auto; line-height: 50px; margin: 0; mso-line-height-rule: exactly; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\">&nbsp;</td></tr></tbody></table>\n" +
                "<table class=\" row\" style=\"border-collapse: collapse; border-spacing: 0; display: table; padding: 0; position: relative; text-align: left; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\">\n" +
                "<th class=\" small-12 large-12 columns first last\" style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0 auto; padding: 0; text-align: left; width: 564px\" align=\"left\"><table style=\"border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\">\n" +
                "<th style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0; padding: 0; text-align: left\" align=\"left\">\n" +
                "<center style=\"min-width: 0; width: 100%\">\n" +
                "\n" +
                "<img width=\"250\" src=\"https://i.ibb.co/Rb9GB39/3401.jpg\" align=\"center\" class=\" float-center float-center float-center\" style=\"-ms-interpolation-mode: bicubic; clear: both; display: block; float: none; margin: 0 auto; max-width: 100%; outline: none; text-align: center; text-decoration: none; width: auto\">\n" +
                "  \n" +
                "</center>\n" +
                " \n" +
                "</th>\n" +
                "<th class=\"expander\" style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0; padding: 0; text-align: left; visibility: hidden; width: 0\" align=\"left\"></th>\n" +
                "</tr></tbody></table></th>\n" +
                "</tr></tbody></table>\n" +
                "\n" +
                "<table class=\"milkyway-content row\" style=\"border-collapse: collapse; border-spacing: 0; display: table; hyphens: none; margin: auto; max-width: 100%; padding: 0; position: relative; text-align: left; vertical-align: top; width: 280px !important\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\">\n" +
                "  <th class=\" small-12 large-12 columns first last\" style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0 auto; padding: 0; text-align: left; width: 564px\" align=\"left\"><table style=\"border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\">\n" +
                "<th style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0; padding: 0; text-align: left\" align=\"left\">\n" +
                "    <h1 class=\"welcome-header\" style=\"color: inherit; font-family: Helvetica, Arial, sans-serif; font-size: 24px; font-weight: 600; hyphens: none; line-height: 30px; margin: 0 0 24px; padding: 0; text-align: center; width: 100%; word-wrap: normal\" align=\"center\">Aerosimo 2FA Login</h1>\n" +
                "    <h2 class=\"welcome-subcontent\" style=\"color: #6F7881; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 300; line-height: 22px; margin: 0; padding: 0; text-align: center; width: 100%; word-wrap: normal\" align=\"left\">We are happy you sign up to Aerosimo. This is to verify  your email. Find your temporary password below, you are almost there!</h2>\n" +
                "  </th>\n" +
                "<th class=\"expander\" style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0; padding: 0; text-align: left; visibility: hidden; width: 0\" align=\"left\"></th>\n" +
                "</tr></tbody></table></th>\n" +
                "</tr></tbody></table>\n" +
                "\n" +
                "<table class=\" spacer \" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td height=\"30px\" style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 30px; font-weight: normal; hyphens: auto; line-height: 30px; margin: 0; mso-line-height-rule: exactly; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\">&nbsp;</td></tr></tbody></table>\n" +
                "<table class=\"milkyway-content wrapper\" align=\"center\" style=\"border-collapse: collapse; border-spacing: 0; hyphens: none; margin: auto; max-width: 100%; padding: 0; text-align: left; vertical-align: top; width: 280px !important\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td class=\"wrapper-inner\" style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 1.3; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\"></td></tr></tbody></table>\n" +
                "<table class=\"milkyway-content row\" style=\"border-collapse: collapse; border-spacing: 0; display: table; hyphens: none; margin: auto; max-width: 100%; padding: 0; position: relative; text-align: left; vertical-align: top; width: 280px !important\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\">\n" +
                "<th class=\"milkyway-padding small-12 large-12 columns first last\" valign=\"middle\" style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0 auto; padding: 0; text-align: left; width: 564px\" align=\"left\"><table style=\"border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\">\n" +
                "<th style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0; padding: 0; text-align: left\" align=\"left\">\n" +
                "<table class=\"cta-text primary radius expanded button\" style=\"border-collapse: collapse; border-spacing: 0; font-size: 14px; font-weight: 400; line-height: 0; margin: 0 0 16px; padding: 0; text-align: left; vertical-align: top; width: 100% !important\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td style=\"-moz-hyphens: auto; -webkit-hyphens: auto; border-collapse: collapse !important; color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 1.3; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" valign=\"top\"><table style=\"border-collapse: collapse; border-spacing: 0; padding: 0; text-align: left; vertical-align: top; width: 100%\"><tbody><tr style=\"padding: 0; text-align: left; vertical-align: top\" align=\"left\"><td style=\"-moz-hyphens: auto; -webkit-hyphens: auto; background: #00aeff; border: 2px none #4e78f1; border-collapse: collapse !important; border-radius: 6px; color: #FFFFFF; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; hyphens: auto; line-height: 1.3; margin: 0; padding: 0; text-align: left; vertical-align: top; word-wrap: break-word\" align=\"left\" bgcolor=\"#4E78F1\" valign=\"top\"><a href=\"https://www.universe.com/create?ref=uniiverse-milkyway%2Ftransactional_emailer-welcome_email_general\" style=\"border: 0 solid #4e78f1; border-radius: 6px; color: #FFFFFF; display: inline-block; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: bold; line-height: 1.3; margin: 0; padding: 13px 0; text-align: center; text-decoration: none; width: 100%\" target=\"_blank\">\n" +
                "<p class=\"text-center\" style=\"color: white; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 300; letter-spacing: 1px; line-height: 1.3; margin: 0; padding: 0; text-align: center\" align=\"center\">");
        message.append(generatedPassword);
        message.append("</p>\n" +
                "</a></td></tr></tbody></table></td></tr></tbody></table>\n" +
                "</th>\n" +
                "<th class=\"expander\" style=\"color: #1C232B; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: normal; line-height: 1.3; margin: 0; padding: 0; text-align: left; visibility: hidden; width: 0\" align=\"left\"></th>\n" +
                "</tr></tbody></table></th>\n" +
                "</tr></tbody></table>\n" +
                "        </center>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody></table>\n" +
                "</body>");
        return SendEmail.sendMail(emailAddress,"Authentication Temporary Password",message.toString(),null);
    }
}