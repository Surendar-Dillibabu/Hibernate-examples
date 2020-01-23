CREATE OR REPLACE PACKAGE HIBERNATE_TRAINING_PKG IS
  PROCEDURE UPD_EMPLOYEE_DETAILS(IN_EMPLOYEE_ID   IN NUMBER,
                                 IN_EMPLOYEE_NAME IN VARCHAR2,
                                 IN_JOINING_DATE  IN DATE,
                                 IN_SALARY        IN NUMBER,
                                 OU_RESULT        OUT VARCHAR2);

  PROCEDURE GET_EMPLOYEE_DETAILS(EMP_CURSOR OUT SYS_REFCURSOR);
END HIBERNATE_TRAINING_PKG;
/

CREATE OR REPLACE PACKAGE BODY HIBERNATE_TRAINING_PKG IS
  PROCEDURE UPD_EMPLOYEE_DETAILS(IN_EMPLOYEE_ID   IN NUMBER,
                                 IN_EMPLOYEE_NAME IN VARCHAR2,
                                 IN_JOINING_DATE  IN DATE,
                                 IN_SALARY        IN NUMBER,
                                 OU_RESULT        OUT VARCHAR2) AS
  BEGIN
    OU_RESULT := 'FAILURE';
    UPDATE employee_tbl
       set EMPLOYEE_NAME = IN_EMPLOYEE_NAME,
           JOINING_DATE  = IN_JOINING_DATE,
           SALARY        = IN_SALARY
     where EMPLOYEE_ID = IN_EMPLOYEE_ID;
    COMMIT;
    OU_RESULT := 'SUCCESS';
  END UPD_EMPLOYEE_DETAILS;

  PROCEDURE GET_EMPLOYEE_DETAILS(EMP_CURSOR OUT SYS_REFCURSOR) AS
  BEGIN
    OPEN EMP_CURSOR FOR
      SELECT EMPLOYEE_ID, EMPLOYEE_NAME, JOINING_DATE, SALARY
        FROM employee_tbl;
  END GET_EMPLOYEE_DETAILS;
END HIBERNATE_TRAINING_PKG;
/