create table passports
(
    id              serial primary key not null,
    seria           int,
    number          int,
    name            varchar(500),
    expiration_date timestamp

);

insert into passports (seria, number, name, expiration_date)
values (9833, 4823645, 'Иванов Иван Иванович', '2022-04-23, 13:10:11');

insert into passports (seria, number, name, expiration_date)
values (9956 ,4873653, 'Владимир Ильич Л', '2017-05-09, 13:10:11');