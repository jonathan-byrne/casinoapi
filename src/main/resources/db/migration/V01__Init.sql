create table players (
    id int auto_increment not null,
    username varchar(256) not null,
    balance double not null,
    primary key (id)
);

insert into players (username, balance) values ('Fred', 5.0);
insert into players (username, balance) values ('Bob', 72.6);

create table transactions (
    id int auto_increment not null,
    transaction_type varchar(256),
    amount double not null,
    date_created timestamp not null,
    player_id int not null,
    primary key (id)
);