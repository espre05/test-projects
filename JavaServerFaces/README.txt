javax.servlet.http.HttpServlet.service(HttpServlet.java:717)
It's conflict with el-api.jar in Tomcat lib directory. But without this Jasper and JSP doesn't work on server.
Can You help with this? 

Response:
You need to remove el-api.jar from tomcat lib dir and include el-api-2.2.jar and el-impl-2.2.jar there. Be carefully to not have the new el-api in your project WEB-INF/LIB You also need to create an empty beans.xml inside WEB-INF dir or you will get in trouble. Weld needs this file. 

================ oracle config --------------
drop sequence customerid_seq;
create sequence customerid_seq
	start with 1 
	increment by 1 
nomaxvalue;

Drop table customer if exists;
create table customer (
customer_id number(20) primary key not null,
name varchar(45) not null,
address varchar(255) ,
created_date date 
);

drop trigger customerid_trigger;
create trigger customerid_trigger
	before insert on scott.customer
	for each row
begin
	select scott.customerid_seq.nextval into :new.customer_id from dual;
end;


INSERT INTO scott.customer(name, address)
VALUES( 'prem1', 'address1');
INSERT INTO scott.customer(name, address)
VALUES('esty2', 'address2');


jdbc:oracle:thin:@localhost:1521:ORCL
oracle.jdbc.OracleDriver