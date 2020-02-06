-- Survey table
create table if not exists restaurant
(
    id          bigint auto_increment primary key,
    eid         uuid default random_uuid(),
    name        varchar(255) not null,
    nickname    varchar(255) null,
    description varchar(255) null,
    type        varchar(50)  not null,

    constraint uuid
        unique (eid)
);