SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_info
-- ----------------------------
DROP TABLE IF EXISTS `t_info`;
CREATE TABLE `t_info`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `topic`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   DEFAULT NULL,
    `message`     varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `insert_date` timestamp                                                DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


SET FOREIGN_KEY_CHECKS = 1;
