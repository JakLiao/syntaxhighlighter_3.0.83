taskkill -f -im javaw.exe -im java.exe

set JAVA_HOME=E:\bea\jdk150_04
set JAVA_170=d:\usr_xp\Java\jdk1.7.0
set PATH=%PATH%;%JAVA_HOME%\bin
set CLASSPATH=D:\usr_win7\Java\jdk1.7.0\lib\dt.jar;
set CLASSPATH=%CLASSPATH%D:\usr_win7\Java\jdk1.7.0\lib\toos.jar;
set CLASSPATH=%CLASSPATH%F:\oracle\product\10.2.0\db_1\jdbc\lib\classes12.jar;
set CLASSPATH=%CLASSPATH%E:\Eclipse\Workspace\hello\bin\rt.jar;

set CLASSPATH=%CLASSPATH%.\cpdetector_1.0.10.jar;
set CLASSPATH=%CLASSPATH%.\antlr-2.7.4.jar;
set CLASSPATH=%CLASSPATH%.\chardet-1.0.jar;
set CLASSPATH=%CLASSPATH%.\jargs-1.0.jar;


java.exe syntaxhighlighter.AutoSyntaxHighlighter
rem exit
