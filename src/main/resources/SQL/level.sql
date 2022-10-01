CREATE TABLE `level_student_info`
(
    `lsi_id`                 bigint(20) unsigned                    NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `lsi_timestamp_create`   timestamp                              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
    `lsi_timestamp_modified` timestamp                              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间戳',
    `lsi_is_deleted`         tinyint(4) unsigned                    NOT NULL DEFAULT '0' COMMENT '是否删除, 0.否, 1.是',
    `lsi_version`            int(10) unsigned                       NOT NULL DEFAULT '0' COMMENT '乐观锁字段',
    `lsi_student_pk_id`      bigint(20) unsigned                    NOT NULL DEFAULT '0' COMMENT '学生表主键Id',
    `lsi_is_passed`          tinyint(4) unsigned                    NOT NULL DEFAULT '0' COMMENT '是否过关',
    `lsi_level_id`           int(8) unsigned                        NOT NULL DEFAULT '0' COMMENT '关卡的Id，第几关',
    `lsi_flag`               varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '关卡过关密钥',
    `lsi_score`              int(8) unsigned                        NOT NULL DEFAULT '0' COMMENT '关卡分数',
    PRIMARY KEY (`lsi_id`),
    UNIQUE `uk_levelId_studentPKId` (`lsi_student_pk_id`, `lsi_level_id`) using BTREE,
    INDEX `idx_studentPKId` (`lsi_student_pk_id`) using BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='学生关卡过关信息表';