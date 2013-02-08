
--首先新建一个表AAAAAAAA,ID主键不可空
CREATE TABLE AAAAAAAA 
(
  ID NUMBER   
, NAME VARCHAR2(20) 
, ADDRESS VARCHAR2(20) 
, CONSTRAINT AAAAAAAA_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

--程序包名为TEST001
--程序包说明内容(用oracle sqldeveloper新建时的内容)
create or replace
PACKAGE TEST001 IS

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
DEPTREC aaaaaaaa%ROWTYPE;--新建一个记录变量DEPTREC

--Add dept...
  FUNCTION ADD_AAAAAAAA(
           dept_no    NUMBER, 
           dept_name VARCHAR2, 
           location  VARCHAR2)
  RETURN NUMBER;
  
  --delete dept...
  FUNCTION delete_aaaaaaaa(dept_no NUMBER)
  RETURN NUMBER;
  
  --query dept...
  PROCEDURE query_aaaaaaaa(dept_no IN NUMBER);
END TEST001;
--包主体(用oracle sqldeveloper右键点击包,选择创建主体后输入的内容)
CREATE OR REPLACE
PACKAGE BODY TEST001 AS

  FUNCTION ADD_AAAAAAAA(
           dept_no    NUMBER, 
           dept_name VARCHAR2, 
           location  VARCHAR2)
  RETURN NUMBER AS
   empno_remaining EXCEPTION; --自定义异常
  PRAGMA EXCEPTION_INIT(EMPNO_REMAINING, -1);
   /* -1 是违反唯一约束条件的错误代码 */
  BEGIN
    /* TODO 需要实施 */ 
    INSERT INTO aaaaaaaa VALUES(dept_no, dept_name, location);
  IF SQL%FOUND THEN
     RETURN 1;
  END IF;
EXCEPTION
     WHEN empno_remaining THEN 
        RETURN 0;
     WHEN OTHERS THEN
        RETURN -1;
  END ADD_AAAAAAAA;

  FUNCTION delete_aaaaaaaa(dept_no NUMBER)
  RETURN NUMBER AS
  BEGIN
    /* TODO 需要实施 */
    RETURN NULL;
  END delete_aaaaaaaa;

  PROCEDURE query_aaaaaaaa(dept_no IN NUMBER) AS
  BEGIN
    /* TODO 需要实施 */
    NULL;
  END query_aaaaaaaa;

END TEST001;

--调用运行方法
set serveroutput on;
DECLARE
    Var NUMBER;
begin
  DBMS_OUTPUT.PUT_LINE('Oracle Hello World!');

  VAR :=   TEST001.ADD_AAAAAAAA(123,'shoukaiseki','japan');
  DBMS_OUTPUT.PUT_LINE('添加成功!');
END;

