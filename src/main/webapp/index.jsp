<%--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  ~ This piece of work is to enhance 2FA project functionality.              ~
  ~                                                                          ~
  ~ Author:    Aerosimo                                                      ~
  ~ File:      index.jsp                                                     ~
  ~ Created:   04/10/2021, 11:18                                             ~
  ~ Modified:  04/10/2021, 23:28                                             ~
  ~                                                                          ~
  ~ Copyright (c)  2021.  Aerosimo Ltd                                       ~
  ~                                                                          ~
  ~ Permission is hereby granted, free of charge, to any person obtaining a
  ~ copy of this software and associated documentation files (the "Software"),
  ~ to deal in the Software without restriction, including without limitation
  ~ the rights to use, copy, modify, merge, publish, distribute, sublicense,
  ~ and/or sell copies of the Software, and to permit persons to whom the
  ~ Software is furnished to do so, subject to the following conditions:     ~
  ~                                                                          ~
  ~ The above copyright notice and this permission notice shall be included
  ~ in all copies or substantial portions of the Software.
  ~
  ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
  ~ EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
  ~ OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
  ~ NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
  ~ HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
  ~ WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
  ~ FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
  ~ OR OTHER DEALINGS IN THE SOFTWARE.
  ~                                                                          ~
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>
<html lang="en">
<head>
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Title -->
    <title>Landing Page | 2FA Web Application | Aerosimo Ltd</title>
    <!-- Mobile Specific Meta -->
    <meta content="width=device-width, initial-scale=1, user-scalable=no" name="viewport"/>
    <!-- Author Meta -->
    <meta content="Elijah Omisore" name="author">
    <!-- Meta Description -->
    <meta content="Aerosimo IT Consultancy" name="description">
    <!-- Meta Keyword -->
    <meta content="2FA, Oracle, Authentication, JDBC, Java, PL/SQL, Tomcat, Maven, Jenkins, Bitbucket, Github, BPMN, UML"
          name="keywords">
    <!-- Meta Description -->
    <meta content="Aerosimo 0.0.1" name="generator">
    <!-- Meta Browser compatibility -->
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="Aerosimo" name="apple-mobile-web-app-title">
    <meta content="Aerosimo" name="application-name">
    <!-- Favicon-->
    <link href="img/favicon.ico" rel="shortcut icon"/>
    <link href="img/favicon.ico" rel="icon" type="image/x-icon">
    <link href="img/favicon-32x32.png" rel="icon" sizes="32x32" type="image/png">
    <link href="img/favicon-16x16.png" rel="icon" sizes="16x16" type="image/png">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon" sizes="180x180">
    <link href="img/android-chrome-192x192.png" rel="android-chrome" sizes="192x192">
    <!-- CSS Files -->
    <link href="css/main.css" rel="stylesheet"/>
</head>
<body>

<div class="welcome-page">
    <div class="corner"></div>
    <div class="corner"></div>
    <div class="corner"></div>
    <div class="corner"></div>
    <div class="content">
        <p data-shadow="WELCOME"><span>W</span><span>E</span><span>L</span><span>C</span><span>O</span><span>M</span><span>E</span></p>
    </div>
    <div class="right-vert-line"></div>
    <div class="left-vert-line"></div>
    <div class="continue"><a href="<%=request.getContextPath()%>/signout">Logout</a></div>
</div>
<div class="container">
    <div class="icons">
        <div class="icon tw"><a href="https://www.twitter.com/real_aerosimo" target="_blank">Twitter</a></div>
        <div class="icon ln"><a href="https://www.linkedin.com/in/elijahomisore" target="_blank">Twitter</a></div>
        <div class="icon git"><a href="https://github.com/Aerosimo/2FA.git" target="_blank">GIT</a></div>
    </div>
</div>
</body>
</html>