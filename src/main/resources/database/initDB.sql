CREATE TABLE IF NOT EXISTS Url
(
    id               int primary key generated always as identity,
    url              varchar   not null,
    alias            varchar   not null unique,
    creation_date    timestamp not null,
    expiration_date  timestamp not null,
    number_of_clicks int
);