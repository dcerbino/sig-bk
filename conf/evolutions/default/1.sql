# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  weight                        double not null,
  type_id                       integer,
  constraint pk_product primary key (id)
);

create table product_type (
  id                            integer auto_increment not null,
  material                      varchar(255) not null,
  color                         varchar(255) not null,
  name                          varchar(255) not null,
  constraint pk_product_type primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  surname                       varchar(255) not null,
  role                          varchar(255) not null,
  constraint pk_user primary key (id)
);

alter table product add constraint fk_product_type_id foreign key (type_id) references product_type (id) on delete restrict on update restrict;
create index ix_product_type_id on product (type_id);


# --- !Downs

alter table product drop foreign key fk_product_type_id;
drop index ix_product_type_id on product;

drop table if exists product;

drop table if exists product_type;

drop table if exists user;

