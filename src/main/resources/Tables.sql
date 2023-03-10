DROP TABLE if exists Accounts cascade;
DROP TABLE if exists Transactions;

CREATE TABLE Accounts(
    account_id int primary key auto_increment,
    username varchar(255) unique,
    password varchar(255)
);

CREATE TABLE Transactions(
    transaction_id int auto_increment,
    amount int,
    balance int,
    user_id int,
    time_posted_epoch bigint,
    foreign key (user_id) references Accounts(account_id)
);



