create table if not exists singer
(
    singer_id  bigint auto_increment
        primary key,
    first_name varchar(20)  null,
    last_name  varchar(20)  null,
    song       varchar(100) null
);

