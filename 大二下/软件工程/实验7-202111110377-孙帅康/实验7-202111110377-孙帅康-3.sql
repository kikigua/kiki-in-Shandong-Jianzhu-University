/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     2023/6/12 18:20:14                           */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_付款_RELATIONS_销售') then
    alter table 付款
       delete foreign key FK_付款_RELATIONS_销售
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_商品_RELATIONS_销售') then
    alter table 商品
       delete foreign key FK_商品_RELATIONS_销售
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_商品目录_RELATIONS_商品') then
    alter table 商品目录
       delete foreign key FK_商品目录_RELATIONS_商品
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_销售_RELATIONS_CUSTOMER') then
    alter table 销售
       delete foreign key FK_销售_RELATIONS_CUSTOMER
end if;

drop index if exists customer.customer_PK;

drop table if exists customer;

drop index if exists 付款.Relationship_2_FK;

drop index if exists 付款.付款_PK;

drop table if exists 付款;

drop index if exists 商品.Relationship_3_FK;

drop table if exists 商品;

drop index if exists 商品目录.Relationship_4_FK;

drop table if exists 商品目录;

drop index if exists 销售.Relationship_1_FK;

drop table if exists 销售;

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer 
(
   CustomerID           Integar                        not null,
   CustomerName         char(10)                       null,
   CustomerAddress      char(40)                       null,
   CustomerTelephone    char(15)                       null,
   CustomerScore        Mumber(6)                      null,
   constraint PK_CUSTOMER primary key (CustomerID)
);

/*==============================================================*/
/* Index: customer_PK                                           */
/*==============================================================*/
create unique index customer_PK on customer (
CustomerID ASC
);

/*==============================================================*/
/* Table: 付款                                                    */
/*==============================================================*/
create table 付款 
(
   支付编号                 integer                        not null,
   销售记录编号               numeric(10)                    not null,
   CustomerID           Integar                        null,
   日期时间                 Date&Time                      null,
   预付款项                 decimal(6,2)                   null,
   找零                   decimal(6,2)                   null,
   constraint PK_付款 primary key (支付编号, 销售记录编号)
);

/*==============================================================*/
/* Index: 付款_PK                                                 */
/*==============================================================*/
create unique index 付款_PK on 付款 (
支付编号 ASC,
销售记录编号 ASC
);

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/
create index Relationship_2_FK on 付款 (
CustomerID ASC,
销售记录编号 ASC
);

/*==============================================================*/
/* Table: 商品                                                    */
/*==============================================================*/
create table 商品 
(
   商品编号                 integer                        not null,
   CustomerID           Integar                        null,
   销售记录编号               numeric(10)                    null,
   数量                   Short  Integer                 null,
   constraint PK_商品 primary key clustered (商品编号)
);

/*==============================================================*/
/* Index: Relationship_3_FK                                     */
/*==============================================================*/
create index Relationship_3_FK on 商品 (
CustomerID ASC,
销售记录编号 ASC
);

/*==============================================================*/
/* Table: 商品目录                                                  */
/*==============================================================*/
create table 商品目录 
(
   商品编号                 integer                        not null,
   描述                   long varchar                   null,
   厂家                   long binary                    null,
   生产日期                 timestamp                      null,
   单价                   numeric                        null,
   constraint PK_商品目录 primary key clustered (商品编号)
);

/*==============================================================*/
/* Index: Relationship_4_FK                                     */
/*==============================================================*/
create index Relationship_4_FK on 商品目录 (
商品编号 ASC
);

/*==============================================================*/
/* Table: 销售                                                    */
/*==============================================================*/
create table 销售 
(
   CustomerID           Integar                        not null,
   销售记录编号               numeric(10)                    not null,
   日期                   date                           null,
   时间                   time                           null,
   总计                   numeric(10,2)                  null,
   constraint PK_销售 primary key clustered (CustomerID, 销售记录编号)
);

/*==============================================================*/
/* Index: Relationship_1_FK                                     */
/*==============================================================*/
create index Relationship_1_FK on 销售 (
CustomerID ASC
);

alter table 付款
   add constraint FK_付款_RELATIONS_销售 foreign key (CustomerID, 销售记录编号)
      references 销售 (CustomerID, 销售记录编号)
      on update restrict
      on delete restrict;

alter table 商品
   add constraint FK_商品_RELATIONS_销售 foreign key (CustomerID, 销售记录编号)
      references 销售 (CustomerID, 销售记录编号)
      on update restrict
      on delete restrict;

alter table 商品目录
   add constraint FK_商品目录_RELATIONS_商品 foreign key (商品编号)
      references 商品 (商品编号)
      on update restrict
      on delete restrict;

alter table 销售
   add constraint FK_销售_RELATIONS_CUSTOMER foreign key (CustomerID)
      references customer (CustomerID)
      on update restrict
      on delete restrict;

