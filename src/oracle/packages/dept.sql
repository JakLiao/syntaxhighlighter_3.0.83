--http://www.cnblogs.com/huyong/archive/2011/05/26/2057973.html
--新建表
CREATE TABLE "ASUS"."DEPT"
  (
    "DEPTNO" NUMBER NOT NULL ENABLE,
    "DNAME"  VARCHAR2(20 BYTE),
    "LOC"    VARCHAR2(20 BYTE)
  )
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT
  )
  TABLESPACE "MAXDATA" ;

--表说明
create or replace
PACKAGE  DEMO_PKG
IS
  DEPTREC DEPT%ROWTYPE;
  
  --Add dept...
  FUNCTION add_dept(
           dept_no    NUMBER, 
           dept_name VARCHAR2, 
           location  VARCHAR2)
  RETURN NUMBER;
  
  --delete dept...
  FUNCTION delete_dept(dept_no NUMBER)
  RETURN NUMBER;
  
  --query dept...
  PROCEDURE query_dept(dept_no IN NUMBER);
END DEMO_PKG;

--表主体
create or replace
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


--运行
set serveroutput on;
DECLARE
    Var NUMBER;
BEGIN
    Var := DEMO_PKG.add_dept(90,'shoukaiseki', 'japan');
    IF var =-1 THEN
        DBMS_OUTPUT.PUT_LINE(SQLCODE||'----'||SQLERRM);
    ELSIF var =0 THEN
        DBMS_OUTPUT.PUT_LINE('温馨提示:该部门记录已经存在！');
    ELSE
        DBMS_OUTPUT.PUT_LINE('温馨提示:添加记录成功！');
        --下面为调用删除语句
        DEMO_PKG.query_dept(90);
        DBMS_OUTPUT.PUT_LINE(DEMO_PKG.DeptRec.deptno||'---'||
         DEMO_PKG.DeptRec.dname||'---'||DEMO_PKG.DeptRec.loc);
        var := DEMO_PKG.delete_dept(90);
        IF var =-1 THEN
            DBMS_OUTPUT.PUT_LINE(SQLCODE||'----'||SQLERRM);
        ELSIF var=0 THEN
            DBMS_OUTPUT.PUT_LINE('温馨提示:该部门记录不存在！');
        ELSE
            DBMS_OUTPUT.PUT_LINE('温馨提示:删除记录成功！');
        END IF;
        --删除语句结束
    END IF;
END;



