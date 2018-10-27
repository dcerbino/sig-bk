# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table company (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  address                       varchar(255) not null,
  constraint pk_company primary key (id)
);

create table container (
  id                            integer auto_increment not null,
  product_id                    integer,
  foot_size                     integer not null,
  constraint pk_container primary key (id)
);

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

create table shipment_report (
  id                            integer auto_increment not null,
  introduced                    datetime(6) not null,
  terminal                      varchar(255) not null,
  port                          varchar(255) not null,
  navy_company_id               integer,
  provider_id                   integer,
  loading_point                 varchar(255) not null,
  boat                          varchar(255) not null,
  delivery_place                varchar(255) not null,
  constraint uq_shipment_report_navy_company_id unique (navy_company_id),
  constraint uq_shipment_report_provider_id unique (provider_id),
  constraint pk_shipment_report primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  surname                       varchar(255) not null,
  role                          varchar(255) not null,
  constraint pk_user primary key (id)
);

alter table container add constraint fk_container_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_container_product_id on container (product_id);

alter table product add constraint fk_product_type_id foreign key (type_id) references product_type (id) on delete restrict on update restrict;
create index ix_product_type_id on product (type_id);

alter table shipment_report add constraint fk_shipment_report_navy_company_id foreign key (navy_company_id) references company (id) on delete restrict on update restrict;

alter table shipment_report add constraint fk_shipment_report_provider_id foreign key (provider_id) references company (id) on delete restrict on update restrict;


# --- !Downs

alter table container drop foreign key fk_container_product_id;
drop index ix_container_product_id on container;

alter table product drop foreign key fk_product_type_id;
drop index ix_product_type_id on product;

alter table shipment_report drop foreign key fk_shipment_report_navy_company_id;

alter table shipment_report drop foreign key fk_shipment_report_provider_id;

drop table if exists company;

drop table if exists container;

drop table if exists product;

drop table if exists product_type;

drop table if exists shipment_report;

drop table if exists user;

