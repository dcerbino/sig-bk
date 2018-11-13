# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table delivery (
  id                            bigint auto_increment not null,
  container                     varchar(255),
  license_plate                 varchar(255),
  driver_full_name              varchar(255),
  arrival_to_plant              datetime(6),
  container_discharge_start     datetime(6),
  container_discharge_end       datetime(6),
  block_discharge_start         datetime(6),
  block_discharge_end           datetime(6),
  damage_fine                   double,
  return_date                   datetime(6),
  late_return_fine              double,
  purchase_order_id             bigint,
  constraint uq_delivery_purchase_order_id unique (purchase_order_id),
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
  constraint pk_provider_company primary key (id)
);

create table purchase_order (
  id                            bigint auto_increment not null,
  product_id                    bigint,
  provider_id                   bigint,
  date                          datetime(6),
  delivery_id                   bigint,
  quantity_in_tons              float,
  constraint uq_purchase_order_delivery_id unique (delivery_id),
  constraint pk_purchase_order primary key (id)
);

alter table delivery add constraint fk_delivery_purchase_order_id foreign key (purchase_order_id) references purchase_order (id) on delete restrict on update restrict;

alter table purchase_order add constraint fk_purchase_order_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_purchase_order_product_id on purchase_order (product_id);

alter table purchase_order add constraint fk_purchase_order_provider_id foreign key (provider_id) references provider_company (id) on delete restrict on update restrict;
create index ix_purchase_order_provider_id on purchase_order (provider_id);

alter table purchase_order add constraint fk_purchase_order_delivery_id foreign key (delivery_id) references delivery (id) on delete restrict on update restrict;


# --- !Downs

alter table delivery drop foreign key fk_delivery_purchase_order_id;

alter table purchase_order drop foreign key fk_purchase_order_product_id;
drop index ix_purchase_order_product_id on purchase_order;

alter table purchase_order drop foreign key fk_purchase_order_provider_id;
drop index ix_purchase_order_provider_id on purchase_order;

alter table purchase_order drop foreign key fk_purchase_order_delivery_id;

drop table if exists delivery;

drop table if exists product;

drop table if exists provider_company;

drop table if exists purchase_order;

