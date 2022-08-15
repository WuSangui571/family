create table t_families
(
    names varchar(500) null comment '家庭成员姓名',
    constraint t_families_names_uindex
        unique (names)
)
    comment '家庭成员表';

INSERT INTO family.t_families (names) VALUES ('寿豪泽');
INSERT INTO family.t_families (names) VALUES ('张三');
INSERT INTO family.t_families (names) VALUES ('张三妈');
INSERT INTO family.t_families (names) VALUES ('张三爸');
INSERT INTO family.t_families (names) VALUES ('王五');
