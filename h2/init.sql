
create table app_company_description (
                                         id bigserial not null,
                                         code varchar(255),
                                         companyDescription varchar(5000),
                                         name varchar(255),
                                         participantsCount integer,
                                         appParticipant_id bigint,
                                         primary key (id)
);
    
create table app_event (
                           id bigserial not null,
                           description varchar(1000),
                           name varchar(255),
                           place varchar(255),
                           startAt timestamp(6),
                           primary key (id)
);
    
create table app_participant (
                                 id bigserial not null,
                                 isCompany boolean not null,
                                 appCompanyDescription_id bigint,
                                 app_event_id bigint,
                                 appPayment_id bigint,
                                 appPersonDescription_id bigint,
                                 primary key (id)
);
    
create table app_payment (
                             id bigserial not null,
                             name varchar(255),
                             paymentType smallint check (paymentType between 0 and 1),
                             primary key (id)
);
    
create table app_person_description (
                                        id bigserial not null,
                                        description varchar(1500),
                                        firstName varchar(255),
                                        lastName varchar(255),
                                        personalCode varchar(255),
                                        appParticipant_id bigint,
                                        primary key (id)
);
    
alter table if exists app_company_description
drop constraint if exists UK_g1obfllppelcaikdsgmljae6w;

alter table if exists app_company_description
    add constraint UK_g1obfllppelcaikdsgmljae6w unique (appParticipant_id);
    
alter table if exists app_participant
drop constraint if exists UK_3lr1684bpvylqyhsruh7kdf8e;

alter table if exists app_participant
    add constraint UK_3lr1684bpvylqyhsruh7kdf8e unique (appCompanyDescription_id);
    
alter table if exists app_participant
drop constraint if exists UK_9asfuql14amuura6vwr2c9w9i;

alter table if exists app_participant
    add constraint UK_9asfuql14amuura6vwr2c9w9i unique (appPayment_id);
    
alter table if exists app_participant
drop constraint if exists UK_jg45iam0hlka8xwna8d5at6o0;

alter table if exists app_participant
    add constraint UK_jg45iam0hlka8xwna8d5at6o0 unique (appPersonDescription_id);
    
alter table if exists app_person_description
drop constraint if exists UK_loe8wju1wdusrcafihcr8bc1h;

alter table if exists app_person_description
    add constraint UK_loe8wju1wdusrcafihcr8bc1h unique (appParticipant_id);
    
alter table if exists app_company_description
    add constraint FK75wej33c69s7jxh1q0umf7qyd
    foreign key (appParticipant_id)
    references app_participant;
    
alter table if exists app_participant
    add constraint FK4t76ri4sxmkdlxbh6ha7y339b
    foreign key (appCompanyDescription_id)
    references app_company_description;
    
alter table if exists app_participant
    add constraint FKlebgrnbny1h39gorte0vdq84r
    foreign key (app_event_id)
    references app_event;
    
alter table if exists app_participant
    add constraint FKbe1q3d79fm83j2a7sda3eosmu
    foreign key (appPayment_id)
    references app_payment;
    
alter table if exists app_participant
    add constraint FKbw2t1m2hjcmc4b81bikddwhkb
    foreign key (appPersonDescription_id)
    references app_person_description;
    
alter table if exists app_person_description
    add constraint FKhb0qctaqkfmu193784brc6oqh
    foreign key (appParticipant_id)
    references app_participant;

INSERT INTO public.app_payment ("name",paymenttype) VALUES
                                                        ('pangaülekanne',0),
                                                        ('sularaha',1);

