# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bill_of_loading (
  id                            bigint auto_increment not null,
  container_id                  bigint,
  date                          datetime(6) not null,
  company_id                    bigint not null,
  constraint uq_bill_of_loading_container_id unique (container_id),
  constraint uq_bill_of_loading_company_id unique (company_id),
  constraint pk_bill_of_loading primary key (id)
);

create table company (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  address                       varchar(255) not null,
  constraint pk_company primary key (id)
);

create table container (
  id                            bigint auto_increment not null,
  product_id                    bigint,
  foot_size                     integer not null,
  constraint pk_container primary key (id)
);

create table fine (
  id                            bigint auto_increment not null,
  reason                        varchar(255) not null,
  ammount                       double not null,
  currency                      varchar(255) not null,
  constraint pk_fine primary key (id)
);

create table torder (
  id                            bigint auto_increment not null,
  date                          datetime(6) not null,
  company_id                    bigint not null,
  bill_of_loading_id            bigint not null,
  constraint uq_torder_company_id unique (company_id),
  constraint uq_torder_bill_of_loading_id unique (bill_of_loading_id),
  constraint pk_torder primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  order_id                      bigint not null,
  name                          varchar(255) not null,
  weight                        double not null,
  type_id                       bigint,
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
  container_id                  bigint not null,
  trunk_id                      bigint not null,
  enter_time                    datetime(6) not null,
  leave_time                    datetime(6) not null,
  order_id                      bigint not null,
  fine_id                       bigint not null,
  constraint uq_shipment_container_id unique (container_id),
  constraint uq_shipment_trunk_id unique (trunk_id),
  constraint uq_shipment_order_id unique (order_id),
  constraint uq_shipment_fine_id unique (fine_id),
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
  boat                          varchar(255) not null,
  delivery_place                varchar(255) not null,
  constraint uq_shipment_report_navy_company_id unique (navy_company_id),
  constraint uq_shipment_report_provider_id unique (provider_id),
  constraint pk_shipment_report primary key (id)
);

create table truck (
  id                            bigint auto_increment not null,
  container_id                  bigint not null,
  driver                        varchar(255) not null,
  license_plate                 varchar(255) not null,
  constraint uq_truck_container_id unique (container_id),
  constraint pk_truck primary key (id)
);

alter table bill_of_loading add constraint fk_bill_of_loading_container_id foreign key (container_id) references container (id) on delete restrict on update restrict;

alter table bill_of_loading add constraint fk_bill_of_loading_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;

alter table container add constraint fk_container_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_container_product_id on container (product_id);

alter table torder add constraint fk_torder_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;

alter table torder add constraint fk_torder_bill_of_loading_id foreign key (bill_of_loading_id) references bill_of_loading (id) on delete restrict on update restrict;

alter table product add constraint fk_product_order_id foreign key (order_id) references torder (id) on delete restrict on update restrict;
create index ix_product_order_id on product (order_id);

alter table product add constraint fk_product_type_id foreign key (type_id) references product_type (id) on delete restrict on update restrict;
create index ix_product_type_id on product (type_id);

alter table shipment add constraint fk_shipment_container_id foreign key (container_id) references container (id) on delete restrict on update restrict;

alter table shipment add constraint fk_shipment_trunk_id foreign key (trunk_id) references truck (id) on delete restrict on update restrict;

alter table shipment add constraint fk_shipment_order_id foreign key (order_id) references torder (id) on delete restrict on update restrict;

alter table shipment add constraint fk_shipment_fine_id foreign key (fine_id) references fine (id) on delete restrict on update restrict;

alter table shipment_report add constraint fk_shipment_report_navy_company_id foreign key (navy_company_id) references company (id) on delete restrict on update restrict;

alter table shipment_report add constraint fk_shipment_report_provider_id foreign key (provider_id) references company (id) on delete restrict on update restrict;

alter table truck add constraint fk_truck_container_id foreign key (container_id) references container (id) on delete restrict on update restrict;


# --- !Downs

alter table bill_of_loading drop foreign key fk_bill_of_loading_container_id;

alter table bill_of_loading drop foreign key fk_bill_of_loading_company_id;

alter table container drop foreign key fk_container_product_id;
drop index ix_container_product_id on container;

alter table torder drop foreign key fk_torder_company_id;

alter table torder drop foreign key fk_torder_bill_of_loading_id;

alter table product drop foreign key fk_product_order_id;
drop index ix_product_order_id on product;

alter table product drop foreign key fk_product_type_id;
drop index ix_product_type_id on product;

alter table shipment drop foreign key fk_shipment_container_id;

alter table shipment drop foreign key fk_shipment_trunk_id;

alter table shipment drop foreign key fk_shipment_order_id;

alter table shipment drop foreign key fk_shipment_fine_id;

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

