create table t_bills
(
    billID            int auto_increment comment '帐单编号'
        primary key,
    billDate          date         not null comment '帐单日期',
    consumptionType   varchar(500) null comment '消费类型',
    consumptionAmount float        not null comment '消费金额',
    consumer          varchar(500) not null comment '消费人',
    constraint t_bills_billID_uindex
        unique (billID)
)
    comment '帐单表';

INSERT INTO family.t_bills (billID, billDate, consumptionType, consumptionAmount, consumer) VALUES (1, '2022-07-26', '物质文化消费', 529, '张三妈');
INSERT INTO family.t_bills (billID, billDate, consumptionType, consumptionAmount, consumer) VALUES (2, '2022-07-27', '精神文化消费', 399, '张三爸');
INSERT INTO family.t_bills (billID, billDate, consumptionType, consumptionAmount, consumer) VALUES (3, '2022-07-27', '劳务消费', 79.9, '张三');
INSERT INTO family.t_bills (billID, billDate, consumptionType, consumptionAmount, consumer) VALUES (4, '2022-07-28', '物质文化消费', 11.11, '寿豪泽');
INSERT INTO family.t_bills (billID, billDate, consumptionType, consumptionAmount, consumer) VALUES (5, '2022-07-28', '劳务消费', 299.99, '张三');
INSERT INTO family.t_bills (billID, billDate, consumptionType, consumptionAmount, consumer) VALUES (6, '2022-07-28', '精神文化消费', 59.34, '张三爸');
INSERT INTO family.t_bills (billID, billDate, consumptionType, consumptionAmount, consumer) VALUES (9, '2022-07-29', '物质文化消费', 628, '王五');
