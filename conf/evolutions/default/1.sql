# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bill_of_loading (
  id                            bigint auto_increment not null,
  container_id                  varchar(255),
  date                          datetime(6),
  company_id                    bigint,
  order_id                      bigint not null,
  constraint uq_bill_of_loading_container_id unique (container_id),
  constraint uq_bill_of_loading_company_id unique (company_id),
  constraint uq_bill_of_loading_order_id unique (order_id),
  constraint pk_bill_of_loading primary key (id)
);

create table company (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  address                       varchar(255) not null,
  constraint pk_company primary key (id)
);

create table container (
  id                            varchar(255) not null,
  product_id                    bigint,
  foot_size                     integer not null,
  constraint pk_container primary key (id)
);

create table fine (
  id                            bigint auto_increment not null,
  shipment_id                   bigint not null,
  reason                        varchar(255) not null,
  amount                        double not null,
  currency                      varchar(255) not null,
  constraint pk_fine primary key (id)
);

create table torder (
  id                            bigint auto_increment not null,
  product_id                    bigint,
  date                          datetime(6) not null,
  company_id                    bigint not null,
  constraint uq_torder_company_id unique (company_id),
  constraint pk_torder primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  product_type_id               bigint,
  constraint pk_product primary key (id)
);

create table product_type (
  id                            bigint auto_increment not null,
  material                      varchar(255) not null,
  color                         varchar(255) not null,
  name                          varchar(255) not null,
  constraint pk_product_type primary key (id)
);

create table shipment (
  id                            bigint auto_increment not null,
  container_id                  varchar(255) not null,
  truck_id                      bigint not null,
  enter_time                    datetime(6) not null,
  leave_time                    datetime(6) not null,
  order_id                      bigint not null,
  constraint uq_shipment_container_id unique (container_id),
  constraint uq_shipment_truck_id unique (truck_id),
  constraint uq_shipment_order_id unique (order_id),
  constraint pk_shipment primary key (id)
);

create table shipment_report (
  id                            bigint auto_increment not null,
  introduced                    datetime(6) not null,
  terminal                      varchar(255) not null,
  port                          varchar(255) not null,
  navy_company_id               bigint,
  provider_id                   bigint,
  loading_point                 varchar(255) not null,
  docking_point                 varchar(255) not null,
  boat                          varchar(255) not null,
  delivery_place                varchar(255) not null,
  constraint uq_shipment_report_navy_company_id unique (navy_company_id),
  constraint uq_shipment_report_provider_id unique (provider_id),
  constraint pk_shipment_report primary key (id)
);

create table truck (
  id                            bigint auto_increment not null,
  container_id                  varchar(255),
  driver                        varchar(255) not null,
  license_plate                 varchar(255) not null,
  constraint uq_truck_container_id unique (container_id),
  constraint pk_truck primary key (id)
);

alter table bill_of_loading add constraint fk_bill_of_loading_container_id foreign key (container_id) references container (id) on delete restrict on update restrict;

alter table bill_of_loading add constraint fk_bill_of_loading_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;

alter table bill_of_loading add constraint fk_bill_of_loading_order_id foreign key (order_id) references torder (id) on delete restrict on update restrict;

alter table container add constraint fk_container_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_container_product_id on container (product_id);

alter table fine add constraint fk_fine_shipment_id foreign key (shipment_id) references shipment (id) on delete restrict on update restrict;
create index ix_fine_shipment_id on fine (shipment_id);

alter table torder add constraint fk_torder_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_torder_product_id on torder (product_id);

alter table torder add constraint fk_torder_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;

alter table product add constraint fk_product_product_type_id foreign key (product_type_id) references product_type (id) on delete restrict on update restrict;
create index ix_product_product_type_id on product (product_type_id);

alter table shipment add constraint fk_shipment_container_id foreign key (container_id) references container (id) on delete restrict on update restrict;

alter table shipment add constraint fk_shipment_truck_id foreign key (truck_id) references truck (id) on delete restrict on update restrict;

alter table shipment add constraint fk_shipment_order_id foreign key (order_id) references torder (id) on delete restrict on update restrict;

alter table shipment_report add constraint fk_shipment_report_navy_company_id foreign key (navy_company_id) references company (id) on delete restrict on update restrict;

alter table shipment_report add constraint fk_shipment_report_provider_id foreign key (provider_id) references company (id) on delete restrict on update restrict;

alter table truck add constraint fk_truck_container_id foreign key (container_id) references container (id) on delete restrict on update restrict;


# --- !Downs

alter table bill_of_loading drop foreign key fk_bill_of_loading_container_id;

alter table bill_of_loading drop foreign key fk_bill_of_loading_company_id;

alter table bill_of_loading drop foreign key fk_bill_of_loading_order_id;

alter table container drop foreign key fk_container_product_id;
drop index ix_container_product_id on container;

alter table fine drop foreign key fk_fine_shipment_id;
drop index ix_fine_shipment_id on fine;

alter table torder drop foreign key fk_torder_product_id;
drop index ix_torder_product_id on torder;

alter table torder drop foreign key fk_torder_company_id;

alter table product drop foreign key fk_product_product_type_id;
drop index ix_product_product_type_id on product;

alter table shipment drop foreign key fk_shipment_container_id;

alter table shipment drop foreign key fk_shipment_truck_id;

alter table shipment drop foreign key fk_shipment_order_id;

alter table shipment_report drop foreign key fk_shipment_report_navy_company_id;

alter table shipment_report drop foreign key fk_shipment_report_provider_id;

alter table truck drop foreign key fk_truck_container_id;

drop table if exists bill_of_loading;

drop table if exists company;

drop table if exists container;

drop table if exists fine;

drop table if exists torder;

drop table if exists product;

drop table if exists product_type;

drop table if exists shipment;

drop table if exists shipment_report;

drop table if exists truck;

