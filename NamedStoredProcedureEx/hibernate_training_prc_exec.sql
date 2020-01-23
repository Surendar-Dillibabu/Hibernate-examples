DECLARE
  l_cursor SYS_REFCURSOR;
  l_eid    employee_tbl.EMPLOYEE_ID%TYPE;
  l_ename  employee_tbl.EMPLOYEE_NAME%TYPE;
  l_j_date employee_tbl.JOINING_DATE%TYPE;
  l_salary employee_tbl.SALARY%TYPE;
BEGIN
  HIBERNATE_TRAINING_PKG.GET_EMPLOYEE_DETAILS(EMP_CURSOR => l_cursor);

  LOOP
    FETCH l_cursor
      INTO l_eid, l_ename, l_j_date, l_salary;
    EXIT WHEN l_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(l_eid || ' | ' || l_ename || ' | ' || l_j_date ||
                         ' | ' || l_salary);
  END LOOP;
  CLOSE l_cursor;
END;
/



DECLARE
  RESULT VARCHAR2(50);
BEGIN
  HIBERNATE_TRAINING_PKG.UPD_EMPLOYEE_DETAILS(1,
                                              'Surendar.D',
                                              TO_DATE('20/01/2017',
                                                      'DD/MM/YYYY'),
                                              5000,
                                              RESULT);
  dbms_output.put_line('RESULT :' || RESULT);
END;
/
