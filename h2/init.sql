Hibernate:
alter table if exists app_participant
drop constraint if exists FKr6d4b6cylueamumj3y0m7bfpp
Hibernate:
alter table if exists app_participant
drop constraint if exists FKlebgrnbny1h39gorte0vdq84r
Hibernate:
alter table if exists app_participant
drop constraint if exists FKbe1q3d79fm83j2a7sda3eosmu
Hibernate:
alter table if exists app_participant
drop constraint if exists FKfhyy2fhvr151oi6ukyck93y7n
Hibernate:
drop table if exists app_company_description cascade
    Hibernate:
drop table if exists app_event cascade
    Hibernate:
drop table if exists app_participant cascade
    Hibernate:
drop table if exists app_payment cascade
    Hibernate:
drop table if exists app_person_description cascade
    Hibernate:
create table app_company_description (
                                         participants_count integer,
                                         id bigserial not null,
                                         company_description varchar(5000),
                                         code varchar(255),
                                         name varchar(255),
                                         primary key (id)
)
    Hibernate:
create table app_event (
                           id bigserial not null,
                           start_at timestamp(6),
                           description varchar(1000),
                           name varchar(255),
                           place varchar(255),
                           primary key (id)
)
    Hibernate:
create table app_participant (
                                 company boolean not null,
                                 Company_Description_id bigint,
                                 Person_Description_id bigint,
                                 appPayment_id bigint,
                                 app_event_id bigint,
                                 id bigserial not null,
                                 primary key (id)
)
    Hibernate:
create table app_payment (
                             payment_type smallint check (payment_type between 0 and 1),
                             id bigserial not null,
                             name varchar(255),
                             primary key (id)
)
    Hibernate:
create table app_person_description (
                                        id bigserial not null,
                                        description varchar(1500),
                                        first_name varchar(255),
                                        last_name varchar(255),
                                        personal_code varchar(255),
                                        primary key (id)
)
    Hibernate:
alter table if exists app_participant
    add constraint FKr6d4b6cylueamumj3y0m7bfpp
    foreign key (Company_Description_id)
    references app_company_description
    on delete cascade
Hibernate:
alter table if exists app_participant
    add constraint FKlebgrnbny1h39gorte0vdq84r
    foreign key (app_event_id)
    references app_event
    Hibernate:
alter table if exists app_participant
    add constraint FKbe1q3d79fm83j2a7sda3eosmu
    foreign key (appPayment_id)
    references app_payment
    Hibernate:
alter table if exists app_participant
    add constraint FKfhyy2fhvr151oi6ukyck93y7n
    foreign key (Person_Description_id)
    references app_person_description
    on delete cascade

    INSERT INTO public.app_payment ("name",payment_type) VALUES
    ('pangaülekanne',0),
    ('sularaha',1);
