# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table container (
  id                            varchar(255) not null,
  product_id                    bigint,
  foot_size                     integer not null,
  constraint pk_container primary key (id)
);

create table delivery (
  id                            bigint auto_increment not null,
  container                     varchar(255) not null,
  license_plate                 varchar(255) not null,
  driver_full_name              varchar(255) not null,
  arrival_to_plant              datetime(6) not null,
  container_discharge_start     datetime(6) not null,
  container_discharge_end       datetime(6) not null,
  block_discharge_start         datetime(6) not null,
  block_discharge_end           datetime(6) not null,
  damage_fine                   double not null,
  return_date                   datetime(6) not null,
  late_return_fine              double not null,
  purchase_order_id             bigint,
  constraint pk_delivery primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  constraint pk_product primary key (id)
);

create table provider_company (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  address                       varchar(255) not null,
  constraint pk_provider_company primary key (id)
);

create table purchase_order (
  id                            bigint auto_increment not null,
  product_id                    bigint,
  company_id                    bigint,
  date                          datetime(6),
  quantity_in_tons              float not null,
  constraint pk_purchase_order primary key (id)
);

alter table container add constraint fk_container_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_container_product_id on container (product_id);

alter table delivery add constraint fk_delivery_purchase_order_id foreign key (purchase_order_id) references purchase_order (id) on delete restrict on update restrict;
create index ix_delivery_purchase_order_id on delivery (purchase_order_id);

alter table purchase_order add constraint fk_purchase_order_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_purchase_order_product_id on purchase_order (product_id);

alter table purchase_order add constraint fk_purchase_order_company_id foreign key (company_id) references provider_company (id) on delete restrict on update restrict;
create index ix_purchase_order_company_id on purchase_order (company_id);


# --- !Downs

alter table container drop foreign key fk_container_product_id;
drop index ix_container_product_id on container;

alter table delivery drop foreign key fk_delivery_purchase_order_id;
drop index ix_delivery_purchase_order_id on delivery;

alter table purchase_order drop foreign key fk_purchase_order_product_id;
drop index ix_purchase_order_product_id on purchase_order;

alter table purchase_order drop foreign key fk_purchase_order_company_id;
drop index ix_purchase_order_company_id on purchase_order;

drop table if exists container;

drop table if exists delivery;

drop table if exists product;

drop table if exists provider_company;

drop table if exists purchase_order;

