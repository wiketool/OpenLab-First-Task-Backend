CREATE TABLE `student_info`
(
    `si_id`                 bigint(20) unsigned                   NOT NULL AUTO_INCREMENT COMMENT '学生信息表主键ID',
    `si_timestamp_create`   timestamp                             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
    `si_timestamp_modified` timestamp                             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间戳',
    `si_is_deleted`         tinyint(4) unsigned                   NOT NULL DEFAULT '0' COMMENT '是否删除, 0.否, 1.是',
    `si_version`            int(10) unsigned                      NOT NULL DEFAULT '0' COMMENT '乐观锁字段',
    `si_student_id`         bigint(15) unsigned                   NOT NULL DEFAULT '0' COMMENT '学生学号',
    `si_name`               varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '学生姓名',
    `si_qq`                 bigint(15) unsigned                   NOT NULL DEFAULT '0' COMMENT '学生QQ',
    `si_phone`              bigint(15) unsigned                   NOT NULL DEFAULT '0' COMMENT '学生手机',

    PRIMARY KEY (`si_id`),
    UNIQUE `si_student_id_uk` (`si_student_id`) using BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='学生信息表';