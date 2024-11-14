create table t_user
(
    id          bigint auto_increment
        primary key,
    user_name   varchar(50)                 null,
    user_email  varchar(50) charset utf8mb3 null,
    user_city   varchar(30) charset utf8mb3 null,
    age         int                         null,
    sex         tinyint                     null,
    mobile      varchar(20)                 null,
    email       varchar(50)                 null,
    note        varchar(200)                null,
    position_id bigint                      null
);

create index user_username_index
    on t_user (user_name);


INSERT INTO mybatis.t_user (user_name, user_email, user_city, age, sex, mobile, email, note, position_id) VALUES ('user1', 'user1@qq.com', '深圳', 18, null, null, null, null, null);
INSERT INTO mybatis.t_user (user_name, user_email, user_city, age, sex, mobile, email, note, position_id) VALUES ('user2', 'user2@qq.com', '桂林', 16, null, null, null, null, null);
INSERT INTO mybatis.t_user (user_name, user_email, user_city, age, sex, mobile, email, note, position_id) VALUES ('fairy', 'fairy@qq.com', '深圳', 18, null, null, null, null, null);
