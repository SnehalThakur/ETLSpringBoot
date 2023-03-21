https://data.gov.in/catalog/all-india-pincode-directory

mysql> describe employee;
+--------+------+------+-----+---------+-------+
| Field  | Type | Null | Key | Default | Extra |
+--------+------+------+-----+---------+-------+
| id     | int  | NO   |     | NULL    |       |
| name   | text | YES  |     | NULL    |       |
| age    | int  | NO   |     | NULL    |       |
| gender | text | YES  |     | NULL    |       |
+--------+------+------+-----+---------+-------+

CREATE TABLE employee (
    id int,
    firstname varchar(255),
    lastname varchar(255),
    date_of_birth varchar(255),
    gender varchar(255),
    designation varchar(255),
    date_of_joining varchar(255),
    department varchar(255)
);
INSERT INTO employee (id, firstname, lastname, date_of_birth, gender, designation,date_of_joining,  department) VALUES
('101', 'Saurabh', 'Thakur', '15-03-1990', 'Male', 'Executive' , '01-01-2022', 'Marketing');
INSERT INTO employee (id, firstname, lastname, date_of_birth, gender, designation,date_of_joining,  department) VALUES
('102', 'Jatin', 'Asnani', '20-05-1991', 'Male', 'Dev', '15-11-2022', 'Development');
--------------------------------------

officename,pincode,officetype,deliverystatus,divisionname,regionname,circlename,taluk,districtname,statename,telephone,relatedsuboffice,relatedheadoffice,longitude,latitude


CREATE TABLE india_po (
    officename varchar(255),
    pincode varchar(255),
    officetype varchar(255),
    deliverystatus varchar(255),
    divisionname varchar(255),
    regionname varchar(255),
    circlename varchar(255),
    taluk varchar(255),
    districtname varchar(255),
    statename varchar(255),
    telephone varchar(255),
    relatedsuboffice varchar(255),
    relatedheadoffice varchar(255),
    longitude varchar(255),
    latitude varchar(255)
);
INSERT INTO employee (officename,pincode,officetype,deliverystatus,divisionname,regionname,circlename,taluk,districtname,statename,telephone,relatedsuboffice,relatedheadoffice,longitude,latitude) VALUES
('101', 'Saurabh', 'Thakur', '15-03-1990', 'Male', '01-01-2022', 'Marketing');
-------------------------------------

    "name": "Verizon Wireless - $20 Data Add-On Card",
    "shortDescription": "Compatible with most Verizon Wireless cell phones; redeemable for 3GB of bridge data; $20 value",
    "bestSellingRank": 3195,
    "thumbnailImage": "http://img.bbystatic.com/BestBuy_US/images/products/4246/4246008_s.gif",
    "salePrice": 20,
    "manufacturer": "Verizon Wireless",
    "url": "http://www.bestbuy.com/site/verizon-wireless-20-data-add-on-card/4246008.p?id=1219097020430&skuId=4246008&cmp=RMX&ky=2d3GfEmNIzjA0vkzveHdZEBgpPCyMnLTJ",
    "type": "HardGood",
    "image": "http://img.bbystatic.com/BestBuy_US/images/products/4246/4246008_sc.jpg",
    "customerReviewCount": null,
    "shipping": "Free shipping",
    "salePrice_range": "1 - 50",
    "objectID": "4246008",
    "categories": "Prepaid Minutes"

CREATE TABLE bestbuy (
    name varchar(255),
    short_description LONGTEXT,
    best_selling_rank int,
    thumbnail_image varchar(255),
    sale_price DOUBLE,
    manufacturer varchar(255),
    url varchar(255),
    type varchar(255),
    image varchar(255),
    customer_review_count int,
    shipping varchar(255),
    sale_price_range varchar(255),
    object_id varchar(255),
    categories varchar(255)
);
------------------------------------------
TimeKey,FullDateAlternateKey,DayNumberOfWeek,EnglishDayNameOfWeek,SpanishDayNameOfWeek,FrenchDayNameOfWeek,DayNumberOfMonth,DayNumberOfYear,WeekNumberOfYear,EnglishMonthName,SpanishMonthName,FrenchMonthName,MonthNumberOfYear,CalendarQuarter,CalendarYear,CalendarSemester,FiscalQuarter,FiscalYear,FiscalSemester

CREATE TABLE bestbuy (
    time_key varchar(255),
    full_date_alternate_key varchar(255),
    day_number_of_week varchar(255),
    english_day_name_of_week varchar(255),
    spanish_day_name_of_week varchar(255),
    manufacturer varchar(255),
    url varchar(255),
    type varchar(255),
    image varchar(255),
    customer_review_count varchar(255),
    shipping varchar(255),
    sale_price_range varchar(255),
    object_id varchar(255),
    categories varchar(255)
);







-------------------------------------------


CREATE TABLE department (
    id int,
    name varchar(255),
    head varchar(255)
);


INSERT INTO department (id, name, head) VALUES ('101', 'Dev', 'Snehal');
INSERT INTO department (id, name, head) VALUES ('102', 'Testing', 'Ashu');


Alter ADD

ALTER TABLE employee ADD deptId varchar(255);