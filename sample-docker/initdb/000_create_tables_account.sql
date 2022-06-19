
create table if not exists account (
    id serial,
    email_address varchar unique not null,
    password varchar not null,
    first_name varchar,
    last_name varchar,

    register_datetime timestamp,
    register_account_id int,
    update_datetime timestamp,
    update_account_id int,
    delete_flg char(1) not null default '0',

    primary key(id)
);

create table if not exists account_login_history (
    uuid varchar,
    remote_addr varchar not null,
    account int not null,
    login_datetime timestamp not null,

    register_datetime timestamp,
    register_account_id int,
    update_datetime timestamp,
    update_account_id int,
    delete_flg char(1) not null default '0',

    primary key(uuid)
);