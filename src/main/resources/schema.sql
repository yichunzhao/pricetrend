CREATE TABLE NASDAQ_STOCKS(
id INTEGER NOT NULL UNIQUE PRIMARY KEY,
symbol VARCHAR(255),
securityName VARCHAR(255),
marketCategory VARCHAR(25),
testIssue VARCHAR(25),
financialStatus VARCHAR(25),
roundLotSize VARCHAR(25),
eTF VARCHAR(25),
nextShares VARCHAR(25),
dataNotEnough BOOL,
is20Day233AvgIncremental BOOL
);


--CREATE TABLE stock_historical_quotes
--(
--    id integer NOT NULL UNIQUE PRIMARY KEY,
--    adj_close numeric(19,2),
--    close numeric(19,2),
--    date date,
--    high numeric(19,2),
--    low numeric(19,2),
--    open numeric(19,2),
--    symbol character varying(255) COLLATE pg_catalog."default",
--    volume bigint
--);