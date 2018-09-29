use javaee;
create table ipfinder (
  startip int,
  endip int,
  param char(255),
  primary key (startip,endip)
);

select  * from javaee.ipfinder;

truncate javaee.ipfinder;