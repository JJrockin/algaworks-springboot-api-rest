create table event_report (
	id bigint not null auto_increment,
    delivery_id bigint not null,
    description text not null,
    registry_date datetime not null,

    primary key (id)
);

alter table event_report add constraint pk_event_report_delivery
foreign key (delivery_id) references delivery (id);