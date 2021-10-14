/******************************************************************************
 * This piece of work is to enhance 2FA project functionality.                *
 *                                                                            *
 * Author:    Aerosimo                                                        *
 * File:      auth.sql                                                        *
 * Created:   04/10/2021, 21:09                                               *
 * Modified:  11/10/2021, 06:37                                               *
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

-- Create Tables
CREATE TABLE auth_tbl
(
    uname         VARCHAR2(400 byte),
    pword         VARCHAR2(4000 byte),
    email         VARCHAR2(400 byte),
    mfa_enabled   CHAR(1),
    status        VARCHAR2(20 byte),
    created_date  TIMESTAMP,
    modified_date TIMESTAMP
);

CREATE TABLE audit_tbl
(
    uname  VARCHAR2(400 byte),
    inet   VARCHAR2(50 byte),
    login  TIMESTAMP,
    logout TIMESTAMP
);

CREATE TABLE otp_tbl
(
    uname    VARCHAR2(400 byte),
    authCode VARCHAR2(50 BYTE),
    status   VARCHAR2(20 byte),
    lapse    TIMESTAMP
);

-- Comment on tables
COMMENT ON COLUMN auth_tbl.uname IS 'This is contact username and a primary identifier.';
COMMENT ON COLUMN auth_tbl.pword IS 'This is contact set encrypted password';
COMMENT ON COLUMN auth_tbl.email IS 'Electronic mail is a method of exchanging messages between people using electronic devices.';
COMMENT ON COLUMN auth_tbl.mfa_enabled IS 'This will indicate if multi-factor authentication is enabled or not.';
COMMENT ON COLUMN auth_tbl.status IS 'This the state of the user account either active or inactive.';
COMMENT ON COLUMN auth_tbl.created_date IS 'Audit column - date it was first created.';
COMMENT ON COLUMN auth_tbl.modified_date IS 'Audit column - date of last update.';
COMMENT ON TABLE auth_tbl IS 'A user account is a location on a network server used to store a computer username, password, and other information.';

COMMENT ON COLUMN audit_tbl.uname IS 'This is the primary identifier';
COMMENT ON COLUMN audit_tbl.inet IS 'This store user ip address';
COMMENT ON COLUMN audit_tbl.login IS 'This is the time a user login to the system';
COMMENT ON COLUMN audit_tbl.logout IS 'This is the time a user logout of the system';
COMMENT ON TABLE audit_tbl IS 'This is used to store user session.';

COMMENT ON COLUMN otp_tbl.uname IS 'This is the primary identifier';
COMMENT ON COLUMN otp_tbl.authCode IS 'The unique transaction code';
COMMENT ON COLUMN otp_tbl.status IS 'This the state of the otp, either pending or closed.';
COMMENT ON COLUMN otp_tbl.lapse IS 'This the time the otp expires.';
COMMENT ON TABLE otp_tbl IS 'This is used to generate validation or confirmation code for user session.';

-- Setting Primary Key
ALTER TABLE auth_tbl
    ADD CONSTRAINT auth_pk PRIMARY KEY (uname);

-- Setting Unique Key
ALTER TABLE auth_tbl
    ADD CONSTRAINT email_unq UNIQUE (email);

-- Setting Foreign Key
ALTER TABLE audit_tbl
    ADD CONSTRAINT audit_fk FOREIGN KEY (uname) REFERENCES auth_tbl (uname) ON DELETE CASCADE;
ALTER TABLE otp_tbl
    ADD CONSTRAINT otp_fk FOREIGN KEY (uname) REFERENCES auth_tbl (uname) ON DELETE CASCADE;

-- Setting Check Constraint
ALTER TABLE auth_tbl
    ADD CONSTRAINT mfa_chk CHECK (mfa_enabled IN ('Y', 'N')) ENABLE;
ALTER TABLE auth_tbl
    ADD CONSTRAINT authstatus_chk CHECK (status IN ('Active', 'Inactive', 'Closed', 'Temp')) ENABLE;
ALTER TABLE otp_tbl
    ADD CONSTRAINT otpstatus_chk CHECK (status IN ('Pending', 'Closed')) ENABLE;

-- Creating Triggers
CREATE OR REPLACE TRIGGER insertauth_trg
    BEFORE INSERT
    ON auth_tbl
    FOR EACH ROW
BEGIN
    IF :NEW.mfa_enabled IS NULL THEN
        SELECT 'N'
        INTO :NEW.mfa_enabled
        FROM dual;
    END IF;
    SELECT 'Temp' INTO :NEW.status FROM dual;
    SELECT SYSTIMESTAMP INTO :NEW.created_Date FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER updateauth_trg
    BEFORE UPDATE
    ON auth_tbl
    FOR EACH ROW
BEGIN
    SELECT SYSTIMESTAMP INTO :NEW.modified_date FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER insertotp_trg
    BEFORE INSERT
    ON otp_tbl
    FOR EACH ROW
BEGIN
    IF :NEW.authCode IS NULL THEN
        SELECT dbms_random.String('X', 6)
        INTO :NEW.authCode
        FROM dual;
    END IF;
    SELECT 'Pending' INTO :NEW.status FROM dual;
    SELECT SYSTIMESTAMP + interval '15' minute INTO :NEW.lapse FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER insertaudit_trg
    BEFORE INSERT
    ON audit_tbl
    FOR EACH ROW
BEGIN
    IF :NEW.inet IS NULL THEN
        SELECT SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15)
        INTO :NEW.inet
        FROM dual;
    END IF;
    IF :NEW.login IS NULL THEN
        SELECT SYSTIMESTAMP INTO :NEW.login FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER updateaudit_trg
    BEFORE UPDATE
    ON audit_tbl
    FOR EACH ROW
BEGIN
    IF :NEW.logout IS NULL THEN
        SELECT SYSTIMESTAMP INTO :NEW.logout FROM DUAL;
    END IF;
END;
/

-- Enable Triggers
ALTER TRIGGER insertauth_trg ENABLE;
ALTER TRIGGER updateauth_trg ENABLE;
ALTER TRIGGER insertotp_trg ENABLE;
ALTER TRIGGER insertaudit_trg ENABLE;
ALTER TRIGGER updateaudit_trg ENABLE;

-- Create Packages
CREATE OR REPLACE PACKAGE auth_pkg
AS
    /* $Header: auth_pkg. 1.0.1 04-OCT-21 21:35 Package
    =================================================================================
      Copyright (c) 2021 Aerosimo

      Permission is hereby granted, free of charge, to any person obtaining a copy
      of this software and associated documentation files (the "Software"), to deal
      in the Software without restriction, including without limitation the rights
      to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
      copies of the Software, and to permit persons to whom the Software is
      furnished to do so, subject to the following conditions:

      The above copyright notice and this permission notice shall be included in all
      copies or substantial portions of the Software.

      THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
      IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
      FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
      AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
      LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
      OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
      SOFTWARE.
    ================================================================================
    Name: auth_pkg
    Program Type: Package Specification
    Purpose: ADD/FIND/UPDATE/DELETE entity
    =================================================================================
    HISTORY
    =================================================================================
    | DATE 		| Owner 	| Activity
    =================================================================================
    | 04-OCT-21	| eomisore 	| Created initial script.|
    =================================================================================
    */

    -- create user details to authenticate against
    PROCEDURE signup(
        i_uname IN auth_tbl.uname%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        i_email IN auth_tbl.email%TYPE,
        i_mfa IN auth_tbl.mfa_enabled%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2);

    -- log user out of the session
    PROCEDURE signout(
        i_uname IN auth_tbl.uname%TYPE,
        i_inet IN audit_tbl.inet%TYPE);

    -- authenticate user
    PROCEDURE signin(
        i_uname IN auth_tbl.uname%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        i_inet IN audit_tbl.inet%TYPE,
        i_authCode IN otp_tbl.authCode%TYPE,
        o_email OUT auth_tbl.email%TYPE,
        o_authCode OUT otp_tbl.authCode%TYPE,
        o_expire OUT otp_tbl.lapse%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2);

    -- forgot user details to authenticate against
    PROCEDURE forgotPassword(
        i_email IN auth_tbl.email%TYPE,
        i_authCode IN otp_tbl.authCode%TYPE,
        o_uname OUT auth_tbl.uname%TYPE,
        o_authCode OUT otp_tbl.authCode%TYPE,
        o_expire OUT otp_tbl.lapse%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2);

    -- reset user details to authenticate against
    PROCEDURE resetPassword(
        i_authCode IN otp_tbl.authCode%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        o_uname OUT auth_tbl.uname%TYPE,
        o_email OUT auth_tbl.email%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2);

    -- reset user temporary password
    PROCEDURE setPassword(
        i_uname IN auth_tbl.uname%TYPE,
        i_temp IN auth_tbl.pword%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        o_email OUT auth_tbl.email%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2);

    -- generate verifier access code
    PROCEDURE setOTP(
        i_uname IN otp_tbl.uname%TYPE,
        i_authCode IN otp_tbl.authCode%TYPE,
        o_authCode OUT otp_tbl.authCode%TYPE,
        o_expire OUT otp_tbl.lapse%TYPE);

    -- log activity time
    PROCEDURE setAudit(
        i_uname IN audit_tbl.uname%TYPE,
        i_inet IN audit_tbl.inet%TYPE,
        i_action IN VARCHAR2);

    -- check the access code is valid
    PROCEDURE checkOTP(
        i_authCode IN otp_tbl.authCode%TYPE,
        i_uname OUT otp_tbl.uname%TYPE,
        o_response OUT VARCHAR2);

END auth_pkg;
/

-- Create Packages
CREATE OR REPLACE PACKAGE BODY auth_pkg
AS
    /* $Body: auth_pkg. 1.0.1 04-OCT-21 21:55 Package
    =================================================================================
      Copyright (c) 2021 Aerosimo

      Permission is hereby granted, free of charge, to any person obtaining a copy
      of this software and associated documentation files (the "Software"), to deal
      in the Software without restriction, including without limitation the rights
      to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
      copies of the Software, and to permit persons to whom the Software is
      furnished to do so, subject to the following conditions:

      The above copyright notice and this permission notice shall be included in all
      copies or substantial portions of the Software.

      THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
      IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
      FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
      AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
      LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
      OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
      SOFTWARE.
    ================================================================================
    Name: auth_pkg
    Program Type: Package Specification
    Purpose: ADD/FIND/UPDATE/DELETE entity
    =================================================================================
    HISTORY
    =================================================================================
    | DATE 		| Owner 	| Activity
    =================================================================================
    | 04-OCT-21	| eomisore 	| Created initial script.|
    =================================================================================
    */

    -- create user details to authenticate against
    PROCEDURE signup(
        i_uname IN auth_tbl.uname%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        i_email IN auth_tbl.email%TYPE,
        i_mfa IN auth_tbl.mfa_enabled%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2)
    AS
    BEGIN
        INSERT INTO auth_tbl (uname, email, pword,mfa_enabled)
        VALUES (i_uname, i_email, enc_dec.encrypt(i_pword), i_mfa);
        o_faultCode := 0;
        o_faultMsg := 'Success';
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            O_faultCode := SQLCODE;
            O_faultMsg := SUBSTR(SQLERRM, 1, 2000);
    END signup;

    -- reset user temporary password
    PROCEDURE setPassword(
        i_uname IN auth_tbl.uname%TYPE,
        i_temp IN auth_tbl.pword%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        o_email OUT auth_tbl.email%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2)
    AS
        match_count NUMBER;
    BEGIN
        SELECT (SELECT COUNT(uname)
                FROM auth_tbl
                WHERE uname = i_uname
                  AND enc_dec.decrypt(pword) = i_temp),
               email
        INTO match_count, o_email
        FROM auth_tbl
        WHERE uname = i_uname
          AND enc_dec.decrypt(pword) = i_temp;
        IF (match_count = 1) THEN
            UPDATE auth_tbl
            SET pword = enc_dec.encrypt(i_pword),
                status = 'Active'
            WHERE uname = i_uname;
        END IF;
        o_faultCode := 0;
        o_faultMsg := 'Success';
        IF SQL%NOTFOUND THEN
            RAISE NO_DATA_FOUND;
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            o_faultCode := -20001;
            o_faultMsg := 'Wrong Username Or Password!';
        WHEN OTHERS THEN
            ROLLBACK;
            O_faultcode := SQLCODE;
            O_faultmsg := SUBSTR(SQLERRM, 1, 2000);
    END setPassword;

    -- log user out of the session
    PROCEDURE signout(
        i_uname IN auth_tbl.uname%TYPE,
        i_inet IN audit_tbl.inet%TYPE)
    AS
    BEGIN
        setAudit(i_uname, i_inet, 'logout');
    END signout;

    -- generate verifier access code
    PROCEDURE setOTP(
        i_uname IN otp_tbl.uname%TYPE,
        i_authCode IN otp_tbl.authCode%TYPE,
        o_authCode OUT otp_tbl.authCode%TYPE,
        o_expire OUT otp_tbl.lapse%TYPE)
    AS
    BEGIN
        INSERT INTO otp_tbl (uname, authCode, status)
        VALUES (i_uname, i_authCode, 'Pending')
        RETURNING authCode,lapse INTO o_authCode,o_expire;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
    END setOTP;

    -- log activity time
    PROCEDURE setAudit(
        i_uname IN audit_tbl.uname%TYPE,
        i_inet IN audit_tbl.inet%TYPE,
        i_action IN VARCHAR2)
    AS
    BEGIN
        IF (i_action = 'login') THEN
            INSERT INTO audit_tbl (uname, inet, login)
            VALUES (i_uname, i_inet, systimestamp);
        ELSIF (i_action = 'logout') THEN
            UPDATE audit_tbl
            SET logout = systimestamp
            WHERE uname = i_uname
              AND inet = i_inet;
            IF SQL%NOTFOUND THEN
                RAISE NO_DATA_FOUND;
            END IF;
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            UPDATE audit_tbl
            SET logout = systimestamp
            WHERE uname = i_uname
              AND logout IS NULL;
        WHEN OTHERS THEN
            ROLLBACK;
    END setAudit;

    -- authenticate user
    PROCEDURE signin(
        i_uname IN auth_tbl.uname%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        i_inet IN audit_tbl.inet%TYPE,
        i_authCode IN otp_tbl.authCode%TYPE,
        o_email OUT auth_tbl.email%TYPE,
        o_authCode OUT otp_tbl.authCode%TYPE,
        o_expire OUT otp_tbl.lapse%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2)
    AS
        match_count NUMBER;
        x_status    VARCHAR2(20 byte);
    BEGIN
        SELECT (SELECT COUNT(uname)
                FROM auth_tbl
                WHERE uname = i_uname
                  AND enc_dec.decrypt(pword) = i_pword),
               email, status
        INTO match_count, o_email, x_status
        FROM auth_tbl
        WHERE uname = i_uname
          AND enc_dec.decrypt(pword) = i_pword;
        IF (match_count = 1 AND x_status ='Active') THEN
            setAudit(i_uname, i_inet, 'login');
            setOTP(i_uname, i_authCode, o_authCode, o_expire);
            o_faultcode := 0;
            o_faultmsg := 'Success';
        ELSIF (match_count = 1 AND x_status ='Temp') THEN
            o_faultcode := 0;
            o_faultmsg := 'Temporary';
        ELSE
            IF SQL%NOTFOUND THEN
                RAISE NO_DATA_FOUND;
            END IF;
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            o_faultCode := -20001;
            o_faultMsg := 'Wrong Username Or Password!';
        WHEN OTHERS THEN
            ROLLBACK;
            O_faultcode := SQLCODE;
            O_faultmsg := SUBSTR(SQLERRM, 1, 2000);
    END signin;

    -- check the access code is valid
    PROCEDURE checkOTP(
        i_authCode IN otp_tbl.authCode%TYPE,
        i_uname OUT otp_tbl.uname%TYPE,
        o_response OUT VARCHAR2)
    AS
    BEGIN
        SELECT uname, 'Success'
        INTO i_uname, o_response
        FROM otp_tbl
        WHERE authCode = i_authCode
          AND lapse < systimestamp;
        IF SQL%NOTFOUND THEN
            RAISE NO_DATA_FOUND;
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            o_response := 'The otp token is not valid or expired';
        WHEN OTHERS THEN
            ROLLBACK;
            o_response := SUBSTR(SQLERRM, 1, 2000);
    END checkOTP;

    -- forgot user details to authenticate against
    PROCEDURE forgotPassword(
        i_email IN auth_tbl.email%TYPE,
        i_authCode IN otp_tbl.authCode%TYPE,
        o_uname OUT auth_tbl.uname%TYPE,
        o_authCode OUT otp_tbl.authCode%TYPE,
        o_expire OUT otp_tbl.lapse%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2)
    AS
        match_count NUMBER;
    BEGIN
        SELECT COUNT(uname) INTO match_count FROM auth_tbl WHERE email = i_email;
        IF match_count = 1 THEN
            SELECT uname INTO o_uname FROM auth_tbl WHERE email = i_email;
            setOTP(o_uname,i_authCode,o_authCode,o_expire);
            UPDATE auth_tbl SET status = 'Inactive' WHERE email = i_email;
            o_faultCode := 0;
            o_faultMsg := 'Success';
        END IF;
        IF SQL%NOTFOUND THEN
            RAISE NO_DATA_FOUND;
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            o_faultCode := -20002;
            o_faultMsg := 'The provided email is not valid or found';
        WHEN OTHERS THEN
            ROLLBACK;
            O_faultCode := SQLCODE;
            O_faultMsg := SUBSTR(SQLERRM, 1, 2000);
    END forgotPassword;

    -- reset user details to authenticate against
    PROCEDURE resetPassword(
        i_authCode IN otp_tbl.authCode%TYPE,
        i_pword IN auth_tbl.pword%TYPE,
        o_uname OUT auth_tbl.uname%TYPE,
        o_email OUT auth_tbl.email%TYPE,
        o_faultcode OUT NUMBER,
        o_faultmsg OUT VARCHAR2)
    AS
        fmsg       VARCHAR2(4000 byte);
    BEGIN
        checkOTP(i_authCode, o_uname, fmsg);
        IF fmsg = 'Success' THEN
            UPDATE auth_tbl
            SET status = 'Active',
                pword  = enc_dec.encrypt(i_pword)
            WHERE uname = o_uname
            RETURNING email INTO o_email;
            o_faultCode := 0;
            o_faultMsg := 'Success';
        ELSE
            o_faultCode := 0;
            o_faultMsg := fmsg;
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            O_faultCode := SQLCODE;
            O_faultMsg := SUBSTR(SQLERRM, 1, 2000);
    END resetPassword;

END auth_pkg;
/

ALTER PACKAGE auth_pkg COMPILE PACKAGE;
ALTER PACKAGE auth_pkg COMPILE BODY;
/