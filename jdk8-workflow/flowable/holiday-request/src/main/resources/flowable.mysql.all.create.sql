create table ACT_GE_PROPERTY
(
    NAME_  varchar(64),
    VALUE_ varchar(300),
    REV_   integer,
    primary key (NAME_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_GE_BYTEARRAY
(
    ID_            varchar(64),
    REV_           integer,
    NAME_          varchar(255),
    DEPLOYMENT_ID_ varchar(64),
    BYTES_         LONGBLOB,
    GENERATED_     TINYINT,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

insert into ACT_GE_PROPERTY
values ('common.schema.version', '6.3.1.0', 1);

insert into ACT_GE_PROPERTY
values ('next.dbid', '1', 1);

create table ACT_RU_IDENTITYLINK
(
    ID_                  varchar(64),
    REV_                 integer,
    GROUP_ID_            varchar(255),
    TYPE_                varchar(255),
    USER_ID_             varchar(255),
    TASK_ID_             varchar(64),
    PROC_INST_ID_        varchar(64),
    PROC_DEF_ID_         varchar(64),
    SCOPE_ID_            varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_IDENT_LNK_USER on ACT_RU_IDENTITYLINK (USER_ID_);
create index ACT_IDX_IDENT_LNK_GROUP on ACT_RU_IDENTITYLINK (GROUP_ID_);
create index ACT_IDX_IDENT_LNK_SCOPE on ACT_RU_IDENTITYLINK (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_IDENT_LNK_SCOPE_DEF on ACT_RU_IDENTITYLINK (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

insert into ACT_GE_PROPERTY
values ('identitylink.schema.version', '6.3.1.0', 1);
create table ACT_RU_TASK
(
    ID_                  varchar(64),
    REV_                 integer,
    EXECUTION_ID_        varchar(64),
    PROC_INST_ID_        varchar(64),
    PROC_DEF_ID_         varchar(64),
    TASK_DEF_ID_         varchar(64),
    SCOPE_ID_            varchar(255),
    SUB_SCOPE_ID_        varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    NAME_                varchar(255),
    PARENT_TASK_ID_      varchar(64),
    DESCRIPTION_         varchar(4000),
    TASK_DEF_KEY_        varchar(255),
    OWNER_               varchar(255),
    ASSIGNEE_            varchar(255),
    DELEGATION_          varchar(64),
    PRIORITY_            integer,
    CREATE_TIME_         timestamp(3) NULL,
    DUE_DATE_            datetime(3),
    CATEGORY_            varchar(255),
    SUSPENSION_STATE_    integer,
    TENANT_ID_           varchar(255) default '',
    FORM_KEY_            varchar(255),
    CLAIM_TIME_          datetime(3),
    IS_COUNT_ENABLED_    TINYINT,
    VAR_COUNT_           integer,
    ID_LINK_COUNT_       integer,
    SUB_TASK_COUNT_      integer,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_TASK_CREATE on ACT_RU_TASK (CREATE_TIME_);
create index ACT_IDX_TASK_SCOPE on ACT_RU_TASK (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TASK_SUB_SCOPE on ACT_RU_TASK (SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TASK_SCOPE_DEF on ACT_RU_TASK (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

insert into ACT_GE_PROPERTY
values ('task.schema.version', '6.3.1.0', 1);
create table ACT_RU_VARIABLE
(
    ID_           varchar(64)  not null,
    REV_          integer,
    TYPE_         varchar(255) not null,
    NAME_         varchar(255) not null,
    EXECUTION_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    TASK_ID_      varchar(64),
    SCOPE_ID_     varchar(255),
    SUB_SCOPE_ID_ varchar(255),
    SCOPE_TYPE_   varchar(255),
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_       double,
    LONG_         bigint,
    TEXT_         varchar(4000),
    TEXT2_        varchar(4000),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_RU_VAR_SCOPE_ID_TYPE on ACT_RU_VARIABLE (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_RU_VAR_SUB_ID_TYPE on ACT_RU_VARIABLE (SUB_SCOPE_ID_, SCOPE_TYPE_);

alter table ACT_RU_VARIABLE
    add constraint ACT_FK_VAR_BYTEARRAY
        foreign key (BYTEARRAY_ID_)
            references ACT_GE_BYTEARRAY (ID_);

insert into ACT_GE_PROPERTY
values ('variable.schema.version', '6.3.1.0', 1);
create table ACT_RU_JOB
(
    ID_                  varchar(64)  NOT NULL,
    REV_                 integer,
    TYPE_                varchar(255) NOT NULL,
    LOCK_EXP_TIME_       timestamp(3) NULL,
    LOCK_OWNER_          varchar(255),
    EXCLUSIVE_           boolean,
    EXECUTION_ID_        varchar(64),
    PROCESS_INSTANCE_ID_ varchar(64),
    PROC_DEF_ID_         varchar(64),
    SCOPE_ID_            varchar(255),
    SUB_SCOPE_ID_        varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    RETRIES_             integer,
    EXCEPTION_STACK_ID_  varchar(64),
    EXCEPTION_MSG_       varchar(4000),
    DUEDATE_             timestamp(3) NULL,
    REPEAT_              varchar(255),
    HANDLER_TYPE_        varchar(255),
    HANDLER_CFG_         varchar(4000),
    CUSTOM_VALUES_ID_    varchar(64),
    CREATE_TIME_         timestamp(3) NULL,
    TENANT_ID_           varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RU_TIMER_JOB
(
    ID_                  varchar(64)  NOT NULL,
    REV_                 integer,
    TYPE_                varchar(255) NOT NULL,
    LOCK_EXP_TIME_       timestamp(3) NULL,
    LOCK_OWNER_          varchar(255),
    EXCLUSIVE_           boolean,
    EXECUTION_ID_        varchar(64),
    PROCESS_INSTANCE_ID_ varchar(64),
    PROC_DEF_ID_         varchar(64),
    SCOPE_ID_            varchar(255),
    SUB_SCOPE_ID_        varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    RETRIES_             integer,
    EXCEPTION_STACK_ID_  varchar(64),
    EXCEPTION_MSG_       varchar(4000),
    DUEDATE_             timestamp(3) NULL,
    REPEAT_              varchar(255),
    HANDLER_TYPE_        varchar(255),
    HANDLER_CFG_         varchar(4000),
    CUSTOM_VALUES_ID_    varchar(64),
    CREATE_TIME_         timestamp(3) NULL,
    TENANT_ID_           varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RU_SUSPENDED_JOB
(
    ID_                  varchar(64)  NOT NULL,
    REV_                 integer,
    TYPE_                varchar(255) NOT NULL,
    EXCLUSIVE_           boolean,
    EXECUTION_ID_        varchar(64),
    PROCESS_INSTANCE_ID_ varchar(64),
    PROC_DEF_ID_         varchar(64),
    SCOPE_ID_            varchar(255),
    SUB_SCOPE_ID_        varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    RETRIES_             integer,
    EXCEPTION_STACK_ID_  varchar(64),
    EXCEPTION_MSG_       varchar(4000),
    DUEDATE_             timestamp(3) NULL,
    REPEAT_              varchar(255),
    HANDLER_TYPE_        varchar(255),
    HANDLER_CFG_         varchar(4000),
    CUSTOM_VALUES_ID_    varchar(64),
    CREATE_TIME_         timestamp(3) NULL,
    TENANT_ID_           varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RU_DEADLETTER_JOB
(
    ID_                  varchar(64)  NOT NULL,
    REV_                 integer,
    TYPE_                varchar(255) NOT NULL,
    EXCLUSIVE_           boolean,
    EXECUTION_ID_        varchar(64),
    PROCESS_INSTANCE_ID_ varchar(64),
    PROC_DEF_ID_         varchar(64),
    SCOPE_ID_            varchar(255),
    SUB_SCOPE_ID_        varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    EXCEPTION_STACK_ID_  varchar(64),
    EXCEPTION_MSG_       varchar(4000),
    DUEDATE_             timestamp(3) NULL,
    REPEAT_              varchar(255),
    HANDLER_TYPE_        varchar(255),
    HANDLER_CFG_         varchar(4000),
    CUSTOM_VALUES_ID_    varchar(64),
    CREATE_TIME_         timestamp(3) NULL,
    TENANT_ID_           varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RU_HISTORY_JOB
(
    ID_                 varchar(64) NOT NULL,
    REV_                integer,
    LOCK_EXP_TIME_      timestamp(3) NULL,
    LOCK_OWNER_         varchar(255),
    RETRIES_            integer,
    EXCEPTION_STACK_ID_ varchar(64),
    EXCEPTION_MSG_      varchar(4000),
    HANDLER_TYPE_       varchar(255),
    HANDLER_CFG_        varchar(4000),
    CUSTOM_VALUES_ID_   varchar(64),
    ADV_HANDLER_CFG_ID_ varchar(64),
    CREATE_TIME_        timestamp(3) NULL,
    SCOPE_TYPE_         varchar(255),
    TENANT_ID_          varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_JOB_EXCEPTION_STACK_ID on ACT_RU_JOB (EXCEPTION_STACK_ID_);
create index ACT_IDX_JOB_CUSTOM_VALUES_ID on ACT_RU_JOB (CUSTOM_VALUES_ID_);

create index ACT_IDX_TIMER_JOB_EXCEPTION_STACK_ID on ACT_RU_TIMER_JOB (EXCEPTION_STACK_ID_);
create index ACT_IDX_TIMER_JOB_CUSTOM_VALUES_ID on ACT_RU_TIMER_JOB (CUSTOM_VALUES_ID_);

create index ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID on ACT_RU_SUSPENDED_JOB (EXCEPTION_STACK_ID_);
create index ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID on ACT_RU_SUSPENDED_JOB (CUSTOM_VALUES_ID_);

create index ACT_IDX_DEADLETTER_JOB_EXCEPTION_STACK_ID on ACT_RU_DEADLETTER_JOB (EXCEPTION_STACK_ID_);
create index ACT_IDX_DEADLETTER_JOB_CUSTOM_VALUES_ID on ACT_RU_DEADLETTER_JOB (CUSTOM_VALUES_ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_EXCEPTION
        foreign key (EXCEPTION_STACK_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_CUSTOM_VALUES
        foreign key (CUSTOM_VALUES_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_EXCEPTION
        foreign key (EXCEPTION_STACK_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_CUSTOM_VALUES
        foreign key (CUSTOM_VALUES_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_EXCEPTION
        foreign key (EXCEPTION_STACK_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_CUSTOM_VALUES
        foreign key (CUSTOM_VALUES_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_EXCEPTION
        foreign key (EXCEPTION_STACK_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_CUSTOM_VALUES
        foreign key (CUSTOM_VALUES_ID_)
            references ACT_GE_BYTEARRAY (ID_);

create index ACT_IDX_JOB_SCOPE on ACT_RU_JOB (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_JOB_SUB_SCOPE on ACT_RU_JOB (SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_JOB_SCOPE_DEF on ACT_RU_JOB (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create index ACT_IDX_TJOB_SCOPE on ACT_RU_TIMER_JOB (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TJOB_SUB_SCOPE on ACT_RU_TIMER_JOB (SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_TJOB_SCOPE_DEF on ACT_RU_TIMER_JOB (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create index ACT_IDX_SJOB_SCOPE on ACT_RU_SUSPENDED_JOB (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_SJOB_SUB_SCOPE on ACT_RU_SUSPENDED_JOB (SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_SJOB_SCOPE_DEF on ACT_RU_SUSPENDED_JOB (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create index ACT_IDX_DJOB_SCOPE on ACT_RU_DEADLETTER_JOB (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_DJOB_SUB_SCOPE on ACT_RU_DEADLETTER_JOB (SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_DJOB_SCOPE_DEF on ACT_RU_DEADLETTER_JOB (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

insert into ACT_GE_PROPERTY
values ('job.schema.version', '6.3.1.0', 1);
create table ACT_RE_DEPLOYMENT
(
    ID_                   varchar(64),
    NAME_                 varchar(255),
    CATEGORY_             varchar(255),
    KEY_                  varchar(255),
    TENANT_ID_            varchar(255) default '',
    DEPLOY_TIME_          timestamp(3) NULL,
    DERIVED_FROM_         varchar(64),
    DERIVED_FROM_ROOT_    varchar(64),
    PARENT_DEPLOYMENT_ID_ varchar(255),
    ENGINE_VERSION_       varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RE_MODEL
(
    ID_                           varchar(64) not null,
    REV_                          integer,
    NAME_                         varchar(255),
    KEY_                          varchar(255),
    CATEGORY_                     varchar(255),
    CREATE_TIME_                  timestamp(3) null,
    LAST_UPDATE_TIME_             timestamp(3) null,
    VERSION_                      integer,
    META_INFO_                    varchar(4000),
    DEPLOYMENT_ID_                varchar(64),
    EDITOR_SOURCE_VALUE_ID_       varchar(64),
    EDITOR_SOURCE_EXTRA_VALUE_ID_ varchar(64),
    TENANT_ID_                    varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RU_EXECUTION
(
    ID_                   varchar(64),
    REV_                  integer,
    PROC_INST_ID_         varchar(64),
    BUSINESS_KEY_         varchar(255),
    PARENT_ID_            varchar(64),
    PROC_DEF_ID_          varchar(64),
    SUPER_EXEC_           varchar(64),
    ROOT_PROC_INST_ID_    varchar(64),
    ACT_ID_               varchar(255),
    IS_ACTIVE_            TINYINT,
    IS_CONCURRENT_        TINYINT,
    IS_SCOPE_             TINYINT,
    IS_EVENT_SCOPE_       TINYINT,
    IS_MI_ROOT_           TINYINT,
    SUSPENSION_STATE_     integer,
    CACHED_ENT_STATE_     integer,
    TENANT_ID_            varchar(255) default '',
    NAME_                 varchar(255),
    START_ACT_ID_         varchar(255),
    START_TIME_           datetime(3),
    START_USER_ID_        varchar(255),
    LOCK_TIME_            timestamp(3) NULL,
    IS_COUNT_ENABLED_     TINYINT,
    EVT_SUBSCR_COUNT_     integer,
    TASK_COUNT_           integer,
    JOB_COUNT_            integer,
    TIMER_JOB_COUNT_      integer,
    SUSP_JOB_COUNT_       integer,
    DEADLETTER_JOB_COUNT_ integer,
    VAR_COUNT_            integer,
    ID_LINK_COUNT_        integer,
    CALLBACK_ID_          varchar(255),
    CALLBACK_TYPE_        varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RE_PROCDEF
(
    ID_                     varchar(64)  not null,
    REV_                    integer,
    CATEGORY_               varchar(255),
    NAME_                   varchar(255),
    KEY_                    varchar(255) not null,
    VERSION_                integer      not null,
    DEPLOYMENT_ID_          varchar(64),
    RESOURCE_NAME_          varchar(4000),
    DGRM_RESOURCE_NAME_     varchar(4000),
    DESCRIPTION_            varchar(4000),
    HAS_START_FORM_KEY_     TINYINT,
    HAS_GRAPHICAL_NOTATION_ TINYINT,
    SUSPENSION_STATE_       integer,
    TENANT_ID_              varchar(255)          default '',
    ENGINE_VERSION_         varchar(255),
    DERIVED_FROM_           varchar(64),
    DERIVED_FROM_ROOT_      varchar(64),
    DERIVED_VERSION_        integer      not null default 0,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_RU_EVENT_SUBSCR
(
    ID_            varchar(64)  not null,
    REV_           integer,
    EVENT_TYPE_    varchar(255) not null,
    EVENT_NAME_    varchar(255),
    EXECUTION_ID_  varchar(64),
    PROC_INST_ID_  varchar(64),
    ACTIVITY_ID_   varchar(64),
    CONFIGURATION_ varchar(255),
    CREATED_       timestamp(3) not null DEFAULT CURRENT_TIMESTAMP(3),
    PROC_DEF_ID_   varchar(64),
    TENANT_ID_     varchar(255)          default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_EVT_LOG
(
    LOG_NR_       bigint auto_increment,
    TYPE_         varchar(64),
    PROC_DEF_ID_  varchar(64),
    PROC_INST_ID_ varchar(64),
    EXECUTION_ID_ varchar(64),
    TASK_ID_      varchar(64),
    TIME_STAMP_   timestamp(3) not null,
    USER_ID_      varchar(255),
    DATA_         LONGBLOB,
    LOCK_OWNER_   varchar(255),
    LOCK_TIME_    timestamp(3) null,
    IS_PROCESSED_ tinyint default 0,
    primary key (LOG_NR_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_PROCDEF_INFO
(
    ID_           varchar(64) not null,
    PROC_DEF_ID_  varchar(64) not null,
    REV_          integer,
    INFO_JSON_ID_ varchar(64),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_EXEC_BUSKEY on ACT_RU_EXECUTION (BUSINESS_KEY_);
create index ACT_IDC_EXEC_ROOT on ACT_RU_EXECUTION (ROOT_PROC_INST_ID_);
create index ACT_IDX_EVENT_SUBSCR_CONFIG_ on ACT_RU_EVENT_SUBSCR (CONFIGURATION_);
create index ACT_IDX_VARIABLE_TASK_ID on ACT_RU_VARIABLE (TASK_ID_);
create index ACT_IDX_ATHRZ_PROCEDEF on ACT_RU_IDENTITYLINK (PROC_DEF_ID_);
create index ACT_IDX_INFO_PROCDEF on ACT_PROCDEF_INFO (PROC_DEF_ID_);

alter table ACT_GE_BYTEARRAY
    add constraint ACT_FK_BYTEARR_DEPL
        foreign key (DEPLOYMENT_ID_)
            references ACT_RE_DEPLOYMENT (ID_);

alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
        unique (KEY_, VERSION_, DERIVED_VERSION_, TENANT_ID_);

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PROCINST
        foreign key (PROC_INST_ID_)
            references ACT_RU_EXECUTION (ID_) on delete cascade on update cascade;

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PARENT
        foreign key (PARENT_ID_)
            references ACT_RU_EXECUTION (ID_) on delete cascade;

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_SUPER
        foreign key (SUPER_EXEC_)
            references ACT_RU_EXECUTION (ID_) on delete cascade;

alter table ACT_RU_EXECUTION
    add constraint ACT_FK_EXE_PROCDEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_TSKASS_TASK
        foreign key (TASK_ID_)
            references ACT_RU_TASK (ID_);

alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_ATHRZ_PROCEDEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_IDENTITYLINK
    add constraint ACT_FK_IDL_PROCINST
        foreign key (PROC_INST_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TASK
    add constraint ACT_FK_TASK_EXE
        foreign key (EXECUTION_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TASK
    add constraint ACT_FK_TASK_PROCINST
        foreign key (PROC_INST_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TASK
    add constraint ACT_FK_TASK_PROCDEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_VARIABLE
    add constraint ACT_FK_VAR_EXE
        foreign key (EXECUTION_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_VARIABLE
    add constraint ACT_FK_VAR_PROCINST
        foreign key (PROC_INST_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_EXECUTION
        foreign key (EXECUTION_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_PROCESS_INSTANCE
        foreign key (PROCESS_INSTANCE_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_JOB
    add constraint ACT_FK_JOB_PROC_DEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_EXECUTION
        foreign key (EXECUTION_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_PROCESS_INSTANCE
        foreign key (PROCESS_INSTANCE_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_TIMER_JOB
    add constraint ACT_FK_TIMER_JOB_PROC_DEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_EXECUTION
        foreign key (EXECUTION_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE
        foreign key (PROCESS_INSTANCE_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_SUSPENDED_JOB
    add constraint ACT_FK_SUSPENDED_JOB_PROC_DEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_EXECUTION
        foreign key (EXECUTION_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE
        foreign key (PROCESS_INSTANCE_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RU_DEADLETTER_JOB
    add constraint ACT_FK_DEADLETTER_JOB_PROC_DEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_RU_EVENT_SUBSCR
    add constraint ACT_FK_EVENT_EXEC
        foreign key (EXECUTION_ID_)
            references ACT_RU_EXECUTION (ID_);

alter table ACT_RE_MODEL
    add constraint ACT_FK_MODEL_SOURCE
        foreign key (EDITOR_SOURCE_VALUE_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RE_MODEL
    add constraint ACT_FK_MODEL_SOURCE_EXTRA
        foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_RE_MODEL
    add constraint ACT_FK_MODEL_DEPLOYMENT
        foreign key (DEPLOYMENT_ID_)
            references ACT_RE_DEPLOYMENT (ID_);

alter table ACT_PROCDEF_INFO
    add constraint ACT_FK_INFO_JSON_BA
        foreign key (INFO_JSON_ID_)
            references ACT_GE_BYTEARRAY (ID_);

alter table ACT_PROCDEF_INFO
    add constraint ACT_FK_INFO_PROCDEF
        foreign key (PROC_DEF_ID_)
            references ACT_RE_PROCDEF (ID_);

alter table ACT_PROCDEF_INFO
    add constraint ACT_UNIQ_INFO_PROCDEF
        unique (PROC_DEF_ID_);

insert into ACT_GE_PROPERTY
values ('schema.version', '6.3.1.0', 1);

insert into ACT_GE_PROPERTY
values ('schema.history', 'create(6.3.1.0)', 1);

create table ACT_HI_IDENTITYLINK
(
    ID_                  varchar(64),
    GROUP_ID_            varchar(255),
    TYPE_                varchar(255),
    USER_ID_             varchar(255),
    TASK_ID_             varchar(64),
    CREATE_TIME_         datetime(3),
    PROC_INST_ID_        varchar(64),
    SCOPE_ID_            varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_HI_IDENT_LNK_USER on ACT_HI_IDENTITYLINK (USER_ID_);
create index ACT_IDX_HI_IDENT_LNK_SCOPE on ACT_HI_IDENTITYLINK (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_IDENT_LNK_SCOPE_DEF on ACT_HI_IDENTITYLINK (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create table ACT_HI_TASKINST
(
    ID_                  varchar(64) not null,
    REV_                 integer      default 1,
    PROC_DEF_ID_         varchar(64),
    TASK_DEF_ID_         varchar(64),
    TASK_DEF_KEY_        varchar(255),
    PROC_INST_ID_        varchar(64),
    EXECUTION_ID_        varchar(64),
    SCOPE_ID_            varchar(255),
    SUB_SCOPE_ID_        varchar(255),
    SCOPE_TYPE_          varchar(255),
    SCOPE_DEFINITION_ID_ varchar(255),
    NAME_                varchar(255),
    PARENT_TASK_ID_      varchar(64),
    DESCRIPTION_         varchar(4000),
    OWNER_               varchar(255),
    ASSIGNEE_            varchar(255),
    START_TIME_          datetime(3) not null,
    CLAIM_TIME_          datetime(3),
    END_TIME_            datetime(3),
    DURATION_            bigint,
    DELETE_REASON_       varchar(4000),
    PRIORITY_            integer,
    DUE_DATE_            datetime(3),
    FORM_KEY_            varchar(255),
    CATEGORY_            varchar(255),
    TENANT_ID_           varchar(255) default '',
    LAST_UPDATED_TIME_   datetime(3),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_HI_TASK_SCOPE on ACT_HI_TASKINST (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_TASK_SUB_SCOPE on ACT_HI_TASKINST (SUB_SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_TASK_SCOPE_DEF on ACT_HI_TASKINST (SCOPE_DEFINITION_ID_, SCOPE_TYPE_);

create table ACT_HI_VARINST
(
    ID_                varchar(64)  not null,
    REV_               integer default 1,
    PROC_INST_ID_      varchar(64),
    EXECUTION_ID_      varchar(64),
    TASK_ID_           varchar(64),
    NAME_              varchar(255) not null,
    VAR_TYPE_          varchar(100),
    SCOPE_ID_          varchar(255),
    SUB_SCOPE_ID_      varchar(255),
    SCOPE_TYPE_        varchar(255),
    BYTEARRAY_ID_      varchar(64),
    DOUBLE_            double,
    LONG_              bigint,
    TEXT_              varchar(4000),
    TEXT2_             varchar(4000),
    CREATE_TIME_       datetime(3),
    LAST_UPDATED_TIME_ datetime(3),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index ACT_IDX_HI_PROCVAR_NAME_TYPE on ACT_HI_VARINST (NAME_, VAR_TYPE_);
create index ACT_IDX_HI_VAR_SCOPE_ID_TYPE on ACT_HI_VARINST (SCOPE_ID_, SCOPE_TYPE_);
create index ACT_IDX_HI_VAR_SUB_ID_TYPE on ACT_HI_VARINST (SUB_SCOPE_ID_, SCOPE_TYPE_);

create table ACT_HI_PROCINST
(
    ID_                        varchar(64) not null,
    REV_                       integer      default 1,
    PROC_INST_ID_              varchar(64) not null,
    BUSINESS_KEY_              varchar(255),
    PROC_DEF_ID_               varchar(64) not null,
    START_TIME_                datetime(3) not null,
    END_TIME_                  datetime(3),
    DURATION_                  bigint,
    START_USER_ID_             varchar(255),
    START_ACT_ID_              varchar(255),
    END_ACT_ID_                varchar(255),
    SUPER_PROCESS_INSTANCE_ID_ varchar(64),
    DELETE_REASON_             varchar(4000),
    TENANT_ID_                 varchar(255) default '',
    NAME_                      varchar(255),
    CALLBACK_ID_               varchar(255),
    CALLBACK_TYPE_             varchar(255),
    primary key (ID_),
    unique (PROC_INST_ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_HI_ACTINST
(
    ID_                varchar(64)  not null,
    REV_               integer      default 1,
    PROC_DEF_ID_       varchar(64)  not null,
    PROC_INST_ID_      varchar(64)  not null,
    EXECUTION_ID_      varchar(64)  not null,
    ACT_ID_            varchar(255) not null,
    TASK_ID_           varchar(64),
    CALL_PROC_INST_ID_ varchar(64),
    ACT_NAME_          varchar(255),
    ACT_TYPE_          varchar(255) not null,
    ASSIGNEE_          varchar(255),
    START_TIME_        datetime(3) not null,
    END_TIME_          datetime(3),
    DURATION_          bigint,
    DELETE_REASON_     varchar(4000),
    TENANT_ID_         varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_HI_DETAIL
(
    ID_           varchar(64)  not null,
    TYPE_         varchar(255) not null,
    PROC_INST_ID_ varchar(64),
    EXECUTION_ID_ varchar(64),
    TASK_ID_      varchar(64),
    ACT_INST_ID_  varchar(64),
    NAME_         varchar(255) not null,
    VAR_TYPE_     varchar(255),
    REV_          integer,
    TIME_         datetime(3) not null,
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_       double,
    LONG_         bigint,
    TEXT_         varchar(4000),
    TEXT2_        varchar(4000),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_HI_COMMENT
(
    ID_           varchar(64) not null,
    TYPE_         varchar(255),
    TIME_         datetime(3) not null,
    USER_ID_      varchar(255),
    TASK_ID_      varchar(64),
    PROC_INST_ID_ varchar(64),
    ACTION_       varchar(255),
    MESSAGE_      varchar(4000),
    FULL_MSG_     LONGBLOB,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_HI_ATTACHMENT
(
    ID_           varchar(64) not null,
    REV_          integer,
    USER_ID_      varchar(255),
    NAME_         varchar(255),
    DESCRIPTION_  varchar(4000),
    TYPE_         varchar(255),
    TASK_ID_      varchar(64),
    PROC_INST_ID_ varchar(64),
    URL_          varchar(4000),
    CONTENT_ID_   varchar(64),
    TIME_         datetime(3),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


create index ACT_IDX_HI_PRO_INST_END on ACT_HI_PROCINST (END_TIME_);
create index ACT_IDX_HI_PRO_I_BUSKEY on ACT_HI_PROCINST (BUSINESS_KEY_);
create index ACT_IDX_HI_ACT_INST_START on ACT_HI_ACTINST (START_TIME_);
create index ACT_IDX_HI_ACT_INST_END on ACT_HI_ACTINST (END_TIME_);
create index ACT_IDX_HI_DETAIL_PROC_INST on ACT_HI_DETAIL (PROC_INST_ID_);
create index ACT_IDX_HI_DETAIL_ACT_INST on ACT_HI_DETAIL (ACT_INST_ID_);
create index ACT_IDX_HI_DETAIL_TIME on ACT_HI_DETAIL (TIME_);
create index ACT_IDX_HI_DETAIL_NAME on ACT_HI_DETAIL (NAME_);
create index ACT_IDX_HI_DETAIL_TASK_ID on ACT_HI_DETAIL (TASK_ID_);
create index ACT_IDX_HI_PROCVAR_PROC_INST on ACT_HI_VARINST (PROC_INST_ID_);
create index ACT_IDX_HI_PROCVAR_TASK_ID on ACT_HI_VARINST (TASK_ID_);
create index ACT_IDX_HI_PROCVAR_EXE on ACT_HI_VARINST (EXECUTION_ID_);
create index ACT_IDX_HI_ACT_INST_PROCINST on ACT_HI_ACTINST (PROC_INST_ID_, ACT_ID_);
create index ACT_IDX_HI_ACT_INST_EXEC on ACT_HI_ACTINST (EXECUTION_ID_, ACT_ID_);
create index ACT_IDX_HI_IDENT_LNK_TASK on ACT_HI_IDENTITYLINK (TASK_ID_);
create index ACT_IDX_HI_IDENT_LNK_PROCINST on ACT_HI_IDENTITYLINK (PROC_INST_ID_);
create index ACT_IDX_HI_TASK_INST_PROCINST on ACT_HI_TASKINST (PROC_INST_ID_);


CREATE TABLE ACT_CMMN_DATABASECHANGELOG
(
    ID            VARCHAR(255) NOT NULL,
    AUTHOR        VARCHAR(255) NOT NULL,
    FILENAME      VARCHAR(255) NOT NULL,
    DATEEXECUTED  datetime     NOT NULL,
    ORDEREXECUTED INT          NOT NULL,
    EXECTYPE      VARCHAR(10)  NOT NULL,
    MD5SUM        VARCHAR(35) NULL,
    DESCRIPTION   VARCHAR(255) NULL,
    COMMENTS      VARCHAR(255) NULL,
    TAG           VARCHAR(255) NULL,
    LIQUIBASE     VARCHAR(20) NULL,
    CONTEXTS      VARCHAR(255) NULL,
    LABELS        VARCHAR(255) NULL,
    DEPLOYMENT_ID VARCHAR(10) NULL
);

CREATE TABLE ACT_CMMN_DEPLOYMENT
(
    ID_                   VARCHAR(255) NOT NULL,
    NAME_                 VARCHAR(255) NULL,
    CATEGORY_             VARCHAR(255) NULL,
    KEY_                  VARCHAR(255) NULL,
    DEPLOY_TIME_          datetime NULL,
    PARENT_DEPLOYMENT_ID_ VARCHAR(255) NULL,
    TENANT_ID_            VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_ACT_CMMN_DEPLOYMENT PRIMARY KEY (ID_)
);

CREATE TABLE ACT_CMMN_DEPLOYMENT_RESOURCE
(
    ID_             VARCHAR(255) NOT NULL,
    NAME_           VARCHAR(255) NULL,
    DEPLOYMENT_ID_  VARCHAR(255) NULL,
    RESOURCE_BYTES_ LONGBLOB NULL,
    CONSTRAINT PK_CMMN_DEPLOYMENT_RESOURCE PRIMARY KEY (ID_)
);

ALTER TABLE ACT_CMMN_DEPLOYMENT_RESOURCE
    ADD CONSTRAINT ACT_FK_CMMN_RSRC_DPL FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_CMMN_DEPLOYMENT (ID_);

CREATE INDEX ACT_IDX_CMMN_RSRC_DPL ON ACT_CMMN_DEPLOYMENT_RESOURCE (DEPLOYMENT_ID_);

CREATE TABLE ACT_CMMN_CASEDEF
(
    ID_                     VARCHAR(255) NOT NULL,
    REV_                    INT          NOT NULL,
    NAME_                   VARCHAR(255) NULL,
    KEY_                    VARCHAR(255) NOT NULL,
    VERSION_                INT          NOT NULL,
    CATEGORY_               VARCHAR(255) NULL,
    DEPLOYMENT_ID_          VARCHAR(255) NULL,
    RESOURCE_NAME_          VARCHAR(4000) NULL,
    DESCRIPTION_            VARCHAR(4000) NULL,
    HAS_GRAPHICAL_NOTATION_ BIT(1) NULL,
    TENANT_ID_              VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_ACT_CMMN_CASEDEF PRIMARY KEY (ID_)
);

ALTER TABLE ACT_CMMN_CASEDEF
    ADD CONSTRAINT ACT_FK_CASE_DEF_DPLY FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_CMMN_DEPLOYMENT (ID_);

CREATE INDEX ACT_IDX_CASE_DEF_DPLY ON ACT_CMMN_CASEDEF (DEPLOYMENT_ID_);

CREATE TABLE ACT_CMMN_RU_CASE_INST
(
    ID_            VARCHAR(255) NOT NULL,
    REV_           INT          NOT NULL,
    BUSINESS_KEY_  VARCHAR(255) NULL,
    NAME_          VARCHAR(255) NULL,
    PARENT_ID_     VARCHAR(255) NULL,
    CASE_DEF_ID_   VARCHAR(255) NULL,
    STATE_         VARCHAR(255) NULL,
    START_TIME_    datetime NULL,
    START_USER_ID_ VARCHAR(255) NULL,
    CALLBACK_ID_   VARCHAR(255) NULL,
    CALLBACK_TYPE_ VARCHAR(255) NULL,
    TENANT_ID_     VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_ACT_CMMN_RU_CASE_INST PRIMARY KEY (ID_)
);

ALTER TABLE ACT_CMMN_RU_CASE_INST
    ADD CONSTRAINT ACT_FK_CASE_INST_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_);

CREATE INDEX ACT_IDX_CASE_INST_CASE_DEF ON ACT_CMMN_RU_CASE_INST (CASE_DEF_ID_);

CREATE INDEX ACT_IDX_CASE_INST_PARENT ON ACT_CMMN_RU_CASE_INST (PARENT_ID_);

CREATE TABLE ACT_CMMN_RU_PLAN_ITEM_INST
(
    ID_             VARCHAR(255) NOT NULL,
    REV_            INT          NOT NULL,
    CASE_DEF_ID_    VARCHAR(255) NULL,
    CASE_INST_ID_   VARCHAR(255) NULL,
    STAGE_INST_ID_  VARCHAR(255) NULL,
    IS_STAGE_       BIT(1) NULL,
    ELEMENT_ID_     VARCHAR(255) NULL,
    NAME_           VARCHAR(255) NULL,
    STATE_          VARCHAR(255) NULL,
    START_TIME_     datetime NULL,
    START_USER_ID_  VARCHAR(255) NULL,
    REFERENCE_ID_   VARCHAR(255) NULL,
    REFERENCE_TYPE_ VARCHAR(255) NULL,
    TENANT_ID_      VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_CMMN_PLAN_ITEM_INST PRIMARY KEY (ID_)
);

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST
    ADD CONSTRAINT ACT_FK_PLAN_ITEM_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_);

CREATE INDEX ACT_IDX_PLAN_ITEM_CASE_DEF ON ACT_CMMN_RU_PLAN_ITEM_INST (CASE_DEF_ID_);

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST
    ADD CONSTRAINT ACT_FK_PLAN_ITEM_CASE_INST FOREIGN KEY (CASE_INST_ID_) REFERENCES ACT_CMMN_RU_CASE_INST (ID_);

CREATE INDEX ACT_IDX_PLAN_ITEM_CASE_INST ON ACT_CMMN_RU_PLAN_ITEM_INST (CASE_INST_ID_);

CREATE TABLE ACT_CMMN_RU_SENTRY_PART_INST
(
    ID_                VARCHAR(255) NOT NULL,
    REV_               INT          NOT NULL,
    CASE_DEF_ID_       VARCHAR(255) NULL,
    CASE_INST_ID_      VARCHAR(255) NULL,
    PLAN_ITEM_INST_ID_ VARCHAR(255) NULL,
    ON_PART_ID_        VARCHAR(255) NULL,
    IF_PART_ID_        VARCHAR(255) NULL,
    TIME_STAMP_        datetime NULL,
    CONSTRAINT PK_CMMN_SENTRY_PART_INST PRIMARY KEY (ID_)
);

ALTER TABLE ACT_CMMN_RU_SENTRY_PART_INST
    ADD CONSTRAINT ACT_FK_SENTRY_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_);

CREATE INDEX ACT_IDX_SENTRY_CASE_DEF ON ACT_CMMN_RU_SENTRY_PART_INST (CASE_DEF_ID_);

ALTER TABLE ACT_CMMN_RU_SENTRY_PART_INST
    ADD CONSTRAINT ACT_FK_SENTRY_CASE_INST FOREIGN KEY (CASE_INST_ID_) REFERENCES ACT_CMMN_RU_CASE_INST (ID_);

CREATE INDEX ACT_IDX_SENTRY_CASE_INST ON ACT_CMMN_RU_SENTRY_PART_INST (CASE_INST_ID_);

ALTER TABLE ACT_CMMN_RU_SENTRY_PART_INST
    ADD CONSTRAINT ACT_FK_SENTRY_PLAN_ITEM FOREIGN KEY (PLAN_ITEM_INST_ID_) REFERENCES ACT_CMMN_RU_PLAN_ITEM_INST (ID_);

CREATE INDEX ACT_IDX_SENTRY_PLAN_ITEM ON ACT_CMMN_RU_SENTRY_PART_INST (PLAN_ITEM_INST_ID_);

CREATE TABLE ACT_CMMN_RU_MIL_INST
(
    ID_           VARCHAR(255) NOT NULL,
    NAME_         VARCHAR(255) NOT NULL,
    TIME_STAMP_   datetime     NOT NULL,
    CASE_INST_ID_ VARCHAR(255) NOT NULL,
    CASE_DEF_ID_  VARCHAR(255) NOT NULL,
    ELEMENT_ID_   VARCHAR(255) NOT NULL,
    CONSTRAINT PK_ACT_CMMN_RU_MIL_INST PRIMARY KEY (ID_)
);

ALTER TABLE ACT_CMMN_RU_MIL_INST
    ADD CONSTRAINT ACT_FK_MIL_CASE_DEF FOREIGN KEY (CASE_DEF_ID_) REFERENCES ACT_CMMN_CASEDEF (ID_);

CREATE INDEX ACT_IDX_MIL_CASE_DEF ON ACT_CMMN_RU_MIL_INST (CASE_DEF_ID_);

ALTER TABLE ACT_CMMN_RU_MIL_INST
    ADD CONSTRAINT ACT_FK_MIL_CASE_INST FOREIGN KEY (CASE_INST_ID_) REFERENCES ACT_CMMN_RU_CASE_INST (ID_);

CREATE INDEX ACT_IDX_MIL_CASE_INST ON ACT_CMMN_RU_MIL_INST (CASE_INST_ID_);

CREATE TABLE ACT_CMMN_HI_CASE_INST
(
    ID_            VARCHAR(255) NOT NULL,
    REV_           INT          NOT NULL,
    BUSINESS_KEY_  VARCHAR(255) NULL,
    NAME_          VARCHAR(255) NULL,
    PARENT_ID_     VARCHAR(255) NULL,
    CASE_DEF_ID_   VARCHAR(255) NULL,
    STATE_         VARCHAR(255) NULL,
    START_TIME_    datetime NULL,
    END_TIME_      datetime NULL,
    START_USER_ID_ VARCHAR(255) NULL,
    CALLBACK_ID_   VARCHAR(255) NULL,
    CALLBACK_TYPE_ VARCHAR(255) NULL,
    TENANT_ID_     VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_ACT_CMMN_HI_CASE_INST PRIMARY KEY (ID_)
);

CREATE TABLE ACT_CMMN_HI_MIL_INST
(
    ID_           VARCHAR(255) NOT NULL,
    REV_          INT          NOT NULL,
    NAME_         VARCHAR(255) NOT NULL,
    TIME_STAMP_   datetime     NOT NULL,
    CASE_INST_ID_ VARCHAR(255) NOT NULL,
    CASE_DEF_ID_  VARCHAR(255) NOT NULL,
    ELEMENT_ID_   VARCHAR(255) NOT NULL,
    CONSTRAINT PK_ACT_CMMN_HI_MIL_INST PRIMARY KEY (ID_)
);

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                        LIQUIBASE, DEPLOYMENT_ID)
VALUES ('1', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', NOW(), 1, '8:8b4b922d90b05ff27483abefc9597aa6',
        'createTable tableName=ACT_CMMN_DEPLOYMENT; createTable tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_CMMN_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_CMMN_RSRC_DPL, referencedTableName=ACT_CMMN_DEPLOYMENT; create...',
        '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084965');

ALTER TABLE ACT_CMMN_CASEDEF
    ADD DGRM_RESOURCE_NAME_ VARCHAR(4000) NULL, ADD HAS_START_FORM_KEY_ BIT(1) NULL;

ALTER TABLE ACT_CMMN_DEPLOYMENT_RESOURCE
    ADD GENERATED_ BIT(1) NULL;

ALTER TABLE ACT_CMMN_RU_CASE_INST
    ADD LOCK_TIME_ datetime NULL;

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST
    ADD ITEM_DEFINITION_ID_ VARCHAR(255) NULL, ADD ITEM_DEFINITION_TYPE_ VARCHAR(255) NULL;

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                        LIQUIBASE, DEPLOYMENT_ID)
VALUES ('2', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', NOW(), 3, '8:65e39b3d385706bb261cbeffe7533cbe',
        'addColumn tableName=ACT_CMMN_CASEDEF; addColumn tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST',
        '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084965');

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST
    ADD IS_COMPLETEABLE_ BIT(1) NULL;

ALTER TABLE ACT_CMMN_RU_CASE_INST
    ADD IS_COMPLETEABLE_ BIT(1) NULL;

CREATE INDEX ACT_IDX_PLAN_ITEM_STAGE_INST ON ACT_CMMN_RU_PLAN_ITEM_INST (STAGE_INST_ID_);

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST
    ADD IS_COUNT_ENABLED_ BIT(1) NULL;

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST
    ADD VAR_COUNT_ INT NULL;

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST
    ADD SENTRY_PART_INST_COUNT_ INT NULL;

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                        LIQUIBASE, DEPLOYMENT_ID)
VALUES ('3', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', NOW(), 5, '8:c01f6e802b49436b4489040da3012359',
        'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_PLAN_ITEM_STAGE_INST, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableNam...',
        '', 'EXECUTED', NULL, NULL, '3.6.1', '6986084965');

CREATE TABLE ACT_CMMN_HI_PLAN_ITEM_INST
(
    ID_                   VARCHAR(255) NOT NULL,
    REV_                  INT          NOT NULL,
    NAME_                 VARCHAR(255) NULL,
    STATE_                VARCHAR(255) NULL,
    CASE_DEF_ID_          VARCHAR(255) NULL,
    CASE_INST_ID_         VARCHAR(255) NULL,
    STAGE_INST_ID_        VARCHAR(255) NULL,
    IS_STAGE_             BIT(1) NULL,
    ELEMENT_ID_           VARCHAR(255) NULL,
    ITEM_DEFINITION_ID_   VARCHAR(255) NULL,
    ITEM_DEFINITION_TYPE_ VARCHAR(255) NULL,
    CREATED_TIME_         datetime NULL,
    LAST_AVAILABLE_TIME_  datetime NULL,
    LAST_ENABLED_TIME_    datetime NULL,
    LAST_DISABLED_TIME_   datetime NULL,
    LAST_STARTED_TIME_    datetime NULL,
    LAST_SUSPENDED_TIME_  datetime NULL,
    COMPLETED_TIME_       datetime NULL,
    OCCURRED_TIME_        datetime NULL,
    TERMINATED_TIME_      datetime NULL,
    EXIT_TIME_            datetime NULL,
    ENDED_TIME_           datetime NULL,
    LAST_UPDATED_TIME_    datetime NULL,
    START_USER_ID_        VARCHAR(255) NULL,
    REFERENCE_ID_         VARCHAR(255) NULL,
    REFERENCE_TYPE_       VARCHAR(255) NULL,
    TENANT_ID_            VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_ACT_CMMN_HI_PLAN_ITEM_INST PRIMARY KEY (ID_)
);

ALTER TABLE ACT_CMMN_RU_MIL_INST
    ADD TENANT_ID_ VARCHAR(255) DEFAULT '' NULL;

ALTER TABLE ACT_CMMN_HI_MIL_INST
    ADD TENANT_ID_ VARCHAR(255) DEFAULT '' NULL;

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                        LIQUIBASE, DEPLOYMENT_ID)
VALUES ('4', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', NOW(), 7, '8:e40d29cb79345b7fb5afd38a7f0ba8fc',
        'createTable tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_MIL_INST; addColumn tableName=ACT_CMMN_HI_MIL_INST', '',
        'EXECUTED', NULL, NULL, '3.6.1', '6986084965');



CREATE TABLE ACT_DMN_DATABASECHANGELOG
(
    ID            VARCHAR(255) NOT NULL,
    AUTHOR        VARCHAR(255) NOT NULL,
    FILENAME      VARCHAR(255) NOT NULL,
    DATEEXECUTED  datetime     NOT NULL,
    ORDEREXECUTED INT          NOT NULL,
    EXECTYPE      VARCHAR(10)  NOT NULL,
    MD5SUM        VARCHAR(35) NULL,
    DESCRIPTION   VARCHAR(255) NULL,
    COMMENTS      VARCHAR(255) NULL,
    TAG           VARCHAR(255) NULL,
    LIQUIBASE     VARCHAR(20) NULL,
    CONTEXTS      VARCHAR(255) NULL,
    LABELS        VARCHAR(255) NULL,
    DEPLOYMENT_ID VARCHAR(10) NULL
);

CREATE TABLE ACT_DMN_DEPLOYMENT
(
    ID_                   VARCHAR(255) NOT NULL,
    NAME_                 VARCHAR(255) NULL,
    CATEGORY_             VARCHAR(255) NULL,
    DEPLOY_TIME_          datetime NULL,
    TENANT_ID_            VARCHAR(255) NULL,
    PARENT_DEPLOYMENT_ID_ VARCHAR(255) NULL,
    CONSTRAINT PK_ACT_DMN_DEPLOYMENT PRIMARY KEY (ID_)
);

CREATE TABLE ACT_DMN_DEPLOYMENT_RESOURCE
(
    ID_             VARCHAR(255) NOT NULL,
    NAME_           VARCHAR(255) NULL,
    DEPLOYMENT_ID_  VARCHAR(255) NULL,
    RESOURCE_BYTES_ LONGBLOB NULL,
    CONSTRAINT PK_ACT_DMN_DEPLOYMENT_RESOURCE PRIMARY KEY (ID_)
);

CREATE TABLE ACT_DMN_DECISION_TABLE
(
    ID_                   VARCHAR(255) NOT NULL,
    NAME_                 VARCHAR(255) NULL,
    VERSION_              INT NULL,
    KEY_                  VARCHAR(255) NULL,
    CATEGORY_             VARCHAR(255) NULL,
    DEPLOYMENT_ID_        VARCHAR(255) NULL,
    PARENT_DEPLOYMENT_ID_ VARCHAR(255) NULL,
    TENANT_ID_            VARCHAR(255) NULL,
    RESOURCE_NAME_        VARCHAR(255) NULL,
    DESCRIPTION_          VARCHAR(255) NULL,
    CONSTRAINT PK_ACT_DMN_DECISION_TABLE PRIMARY KEY (ID_)
);

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                       LIQUIBASE, DEPLOYMENT_ID)
VALUES ('1', 'activiti', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', NOW(), 1, '8:c8701f1c71018b55029f450b2e9a10a1',
        'createTable tableName=ACT_DMN_DEPLOYMENT; createTable tableName=ACT_DMN_DEPLOYMENT_RESOURCE; createTable tableName=ACT_DMN_DECISION_TABLE',
        '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085036');

CREATE TABLE ACT_DMN_HI_DECISION_EXECUTION
(
    ID_                     VARCHAR(255) NOT NULL,
    DECISION_DEFINITION_ID_ VARCHAR(255) NULL,
    DEPLOYMENT_ID_          VARCHAR(255) NULL,
    START_TIME_             datetime NULL,
    END_TIME_               datetime NULL,
    INSTANCE_ID_            VARCHAR(255) NULL,
    EXECUTION_ID_           VARCHAR(255) NULL,
    ACTIVITY_ID_            VARCHAR(255) NULL,
    FAILED_                 BIT(1) DEFAULT 0 NULL,
    TENANT_ID_              VARCHAR(255) NULL,
    EXECUTION_JSON_         LONGTEXT NULL,
    CONSTRAINT PK_ACT_DMN_HI_DECISION_EXECUTION PRIMARY KEY (ID_)
);

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                       LIQUIBASE, DEPLOYMENT_ID)
VALUES ('2', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', NOW(), 3, '8:47f94b27feb7df8a30d4e338c7bd5fb8',
        'createTable tableName=ACT_DMN_HI_DECISION_EXECUTION', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085036');

ALTER TABLE ACT_DMN_HI_DECISION_EXECUTION
    ADD SCOPE_TYPE_ VARCHAR(255) NULL;

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                       LIQUIBASE, DEPLOYMENT_ID)
VALUES ('3', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', NOW(), 5, '8:ac17eae89fbdccb6e08daf3c7797b579',
        'addColumn tableName=ACT_DMN_HI_DECISION_EXECUTION', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085036');

ALTER TABLE ACT_DMN_DECISION_TABLE DROP COLUMN PARENT_DEPLOYMENT_ID_;

INSERT INTO ACT_DMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                       LIQUIBASE, DEPLOYMENT_ID)
VALUES ('4', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', NOW(), 7, '8:f73aabc4529e7292c2942073d1cff6f9',
        'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_DMN_DECISION_TABLE', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085036');



CREATE TABLE ACT_FO_DATABASECHANGELOG
(
    ID            VARCHAR(255) NOT NULL,
    AUTHOR        VARCHAR(255) NOT NULL,
    FILENAME      VARCHAR(255) NOT NULL,
    DATEEXECUTED  datetime     NOT NULL,
    ORDEREXECUTED INT          NOT NULL,
    EXECTYPE      VARCHAR(10)  NOT NULL,
    MD5SUM        VARCHAR(35) NULL,
    DESCRIPTION   VARCHAR(255) NULL,
    COMMENTS      VARCHAR(255) NULL,
    TAG           VARCHAR(255) NULL,
    LIQUIBASE     VARCHAR(20) NULL,
    CONTEXTS      VARCHAR(255) NULL,
    LABELS        VARCHAR(255) NULL,
    DEPLOYMENT_ID VARCHAR(10) NULL
);

CREATE TABLE ACT_FO_FORM_DEPLOYMENT
(
    ID_                   VARCHAR(255) NOT NULL,
    NAME_                 VARCHAR(255) NULL,
    CATEGORY_             VARCHAR(255) NULL,
    DEPLOY_TIME_          datetime NULL,
    TENANT_ID_            VARCHAR(255) NULL,
    PARENT_DEPLOYMENT_ID_ VARCHAR(255) NULL,
    CONSTRAINT PK_ACT_FO_FORM_DEPLOYMENT PRIMARY KEY (ID_)
);

CREATE TABLE ACT_FO_FORM_RESOURCE
(
    ID_             VARCHAR(255) NOT NULL,
    NAME_           VARCHAR(255) NULL,
    DEPLOYMENT_ID_  VARCHAR(255) NULL,
    RESOURCE_BYTES_ LONGBLOB NULL,
    CONSTRAINT PK_ACT_FO_FORM_RESOURCE PRIMARY KEY (ID_)
);

CREATE TABLE ACT_FO_FORM_DEFINITION
(
    ID_                   VARCHAR(255) NOT NULL,
    NAME_                 VARCHAR(255) NULL,
    VERSION_              INT NULL,
    KEY_                  VARCHAR(255) NULL,
    CATEGORY_             VARCHAR(255) NULL,
    DEPLOYMENT_ID_        VARCHAR(255) NULL,
    PARENT_DEPLOYMENT_ID_ VARCHAR(255) NULL,
    TENANT_ID_            VARCHAR(255) NULL,
    RESOURCE_NAME_        VARCHAR(255) NULL,
    DESCRIPTION_          VARCHAR(255) NULL,
    CONSTRAINT PK_ACT_FO_FORM_DEFINITION PRIMARY KEY (ID_)
);

CREATE TABLE ACT_FO_FORM_INSTANCE
(
    ID_                 VARCHAR(255) NOT NULL,
    FORM_DEFINITION_ID_ VARCHAR(255) NOT NULL,
    TASK_ID_            VARCHAR(255) NULL,
    PROC_INST_ID_       VARCHAR(255) NULL,
    PROC_DEF_ID_        VARCHAR(255) NULL,
    SUBMITTED_DATE_     datetime NULL,
    SUBMITTED_BY_       VARCHAR(255) NULL,
    FORM_VALUES_ID_     VARCHAR(255) NULL,
    TENANT_ID_          VARCHAR(255) NULL,
    CONSTRAINT PK_ACT_FO_FORM_INSTANCE PRIMARY KEY (ID_)
);

INSERT INTO ACT_FO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                      LIQUIBASE, DEPLOYMENT_ID)
VALUES ('1', 'activiti', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', NOW(), 1, '8:033ebf9380889aed7c453927ecc3250d',
        'createTable tableName=ACT_FO_FORM_DEPLOYMENT; createTable tableName=ACT_FO_FORM_RESOURCE; createTable tableName=ACT_FO_FORM_DEFINITION; createTable tableName=ACT_FO_FORM_INSTANCE',
        '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085091');

ALTER TABLE ACT_FO_FORM_INSTANCE
    ADD SCOPE_ID_ VARCHAR(255) NULL, ADD SCOPE_TYPE_ VARCHAR(255) NULL, ADD SCOPE_DEFINITION_ID_ VARCHAR(255) NULL;

INSERT INTO ACT_FO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                      LIQUIBASE, DEPLOYMENT_ID)
VALUES ('2', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', NOW(), 3, '8:986365ceb40445ce3b27a8e6b40f159b',
        'addColumn tableName=ACT_FO_FORM_INSTANCE', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085091');

ALTER TABLE ACT_FO_FORM_DEFINITION DROP COLUMN PARENT_DEPLOYMENT_ID_;

INSERT INTO ACT_FO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                      LIQUIBASE, DEPLOYMENT_ID)
VALUES ('3', 'flowable', 'org/flowable/form/db/liquibase/flowable-form-db-changelog.xml', NOW(), 5, '8:abf482518ceb09830ef674e52c06bf15',
        'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_FO_FORM_DEFINITION', '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085091');



CREATE TABLE ACT_CO_DATABASECHANGELOG
(
    ID            VARCHAR(255) NOT NULL,
    AUTHOR        VARCHAR(255) NOT NULL,
    FILENAME      VARCHAR(255) NOT NULL,
    DATEEXECUTED  datetime     NOT NULL,
    ORDEREXECUTED INT          NOT NULL,
    EXECTYPE      VARCHAR(10)  NOT NULL,
    MD5SUM        VARCHAR(35) NULL,
    DESCRIPTION   VARCHAR(255) NULL,
    COMMENTS      VARCHAR(255) NULL,
    TAG           VARCHAR(255) NULL,
    LIQUIBASE     VARCHAR(20) NULL,
    CONTEXTS      VARCHAR(255) NULL,
    LABELS        VARCHAR(255) NULL,
    DEPLOYMENT_ID VARCHAR(10) NULL
);

CREATE TABLE ACT_CO_CONTENT_ITEM
(
    ID_                 VARCHAR(255) NOT NULL,
    NAME_               VARCHAR(255) NOT NULL,
    MIME_TYPE_          VARCHAR(255) NULL,
    TASK_ID_            VARCHAR(255) NULL,
    PROC_INST_ID_       VARCHAR(255) NULL,
    CONTENT_STORE_ID_   VARCHAR(255) NULL,
    CONTENT_STORE_NAME_ VARCHAR(255) NULL,
    FIELD_              VARCHAR(400) NULL,
    CONTENT_AVAILABLE_  BIT(1) DEFAULT 0 NULL,
    CREATED_            timestamp(6) NULL,
    CREATED_BY_         VARCHAR(255) NULL,
    LAST_MODIFIED_      timestamp(6) NULL,
    LAST_MODIFIED_BY_   VARCHAR(255) NULL,
    CONTENT_SIZE_       BIGINT DEFAULT 0 NULL,
    TENANT_ID_          VARCHAR(255) NULL,
    CONSTRAINT PK_ACT_CO_CONTENT_ITEM PRIMARY KEY (ID_)
);

CREATE INDEX idx_contitem_taskid ON ACT_CO_CONTENT_ITEM (TASK_ID_);

CREATE INDEX idx_contitem_procid ON ACT_CO_CONTENT_ITEM (PROC_INST_ID_);

INSERT INTO ACT_CO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                      LIQUIBASE, DEPLOYMENT_ID)
VALUES ('1', 'activiti', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', NOW(), 1, '8:7644d7165cfe799200a2abdd3419e8b6',
        'createTable tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_taskid, tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_procid, tableName=ACT_CO_CONTENT_ITEM',
        '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085125');

ALTER TABLE ACT_CO_CONTENT_ITEM
    ADD SCOPE_ID_ VARCHAR(255) NULL, ADD SCOPE_TYPE_ VARCHAR(255) NULL;

CREATE INDEX idx_contitem_scope ON ACT_CO_CONTENT_ITEM (SCOPE_ID_, SCOPE_TYPE_);

INSERT INTO ACT_CO_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                      LIQUIBASE, DEPLOYMENT_ID)
VALUES ('2', 'flowable', 'org/flowable/content/db/liquibase/flowable-content-db-changelog.xml', NOW(), 3, '8:fe7b11ac7dbbf9c43006b23bbab60bab',
        'addColumn tableName=ACT_CO_CONTENT_ITEM; createIndex indexName=idx_contitem_scope, tableName=ACT_CO_CONTENT_ITEM', '', 'EXECUTED', NULL,
        NULL, '3.6.1', '6986085125');


create table ACT_ID_PROPERTY
(
    NAME_  varchar(64),
    VALUE_ varchar(300),
    REV_   integer,
    primary key (NAME_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

insert into ACT_ID_PROPERTY
values ('schema.version', '6.3.1.0', 1);

create table ACT_ID_BYTEARRAY
(
    ID_    varchar(64),
    REV_   integer,
    NAME_  varchar(255),
    BYTES_ LONGBLOB,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_GROUP
(
    ID_   varchar(64),
    REV_  integer,
    NAME_ varchar(255),
    TYPE_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_MEMBERSHIP
(
    USER_ID_  varchar(64),
    GROUP_ID_ varchar(64),
    primary key (USER_ID_, GROUP_ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_USER
(
    ID_         varchar(64),
    REV_        integer,
    FIRST_      varchar(255),
    LAST_       varchar(255),
    EMAIL_      varchar(255),
    PWD_        varchar(255),
    PICTURE_ID_ varchar(64),
    TENANT_ID_  varchar(255) default '',
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_INFO
(
    ID_        varchar(64),
    REV_       integer,
    USER_ID_   varchar(64),
    TYPE_      varchar(64),
    KEY_       varchar(255),
    VALUE_     varchar(255),
    PASSWORD_  LONGBLOB,
    PARENT_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_TOKEN
(
    ID_          varchar(64) not null,
    REV_         integer,
    TOKEN_VALUE_ varchar(255),
    TOKEN_DATE_  timestamp(3),
    IP_ADDRESS_  varchar(255),
    USER_AGENT_  varchar(255),
    USER_ID_     varchar(255),
    TOKEN_DATA_  varchar(2000),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_PRIV
(
    ID_   varchar(64)  not null,
    NAME_ varchar(255) not null,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_PRIV_MAPPING
(
    ID_       varchar(64) not null,
    PRIV_ID_  varchar(64) not null,
    USER_ID_  varchar(255),
    GROUP_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

alter table ACT_ID_MEMBERSHIP
    add constraint ACT_FK_MEMB_GROUP
        foreign key (GROUP_ID_)
            references ACT_ID_GROUP (ID_);

alter table ACT_ID_MEMBERSHIP
    add constraint ACT_FK_MEMB_USER
        foreign key (USER_ID_)
            references ACT_ID_USER (ID_);

alter table ACT_ID_PRIV_MAPPING
    add constraint ACT_FK_PRIV_MAPPING
        foreign key (PRIV_ID_)
            references ACT_ID_PRIV (ID_);

create index ACT_IDX_PRIV_USER on ACT_ID_PRIV_MAPPING (USER_ID_);
create index ACT_IDX_PRIV_GROUP on ACT_ID_PRIV_MAPPING (GROUP_ID_);

alter table ACT_ID_PRIV
    add constraint ACT_UNIQ_PRIV_NAME
        unique (NAME_);


CREATE TABLE ACT_APP_DATABASECHANGELOG
(
    ID            VARCHAR(255) NOT NULL,
    AUTHOR        VARCHAR(255) NOT NULL,
    FILENAME      VARCHAR(255) NOT NULL,
    DATEEXECUTED  datetime     NOT NULL,
    ORDEREXECUTED INT          NOT NULL,
    EXECTYPE      VARCHAR(10)  NOT NULL,
    MD5SUM        VARCHAR(35) NULL,
    DESCRIPTION   VARCHAR(255) NULL,
    COMMENTS      VARCHAR(255) NULL,
    TAG           VARCHAR(255) NULL,
    LIQUIBASE     VARCHAR(20) NULL,
    CONTEXTS      VARCHAR(255) NULL,
    LABELS        VARCHAR(255) NULL,
    DEPLOYMENT_ID VARCHAR(10) NULL
);

CREATE TABLE ACT_APP_DEPLOYMENT
(
    ID_          VARCHAR(255) NOT NULL,
    NAME_        VARCHAR(255) NULL,
    CATEGORY_    VARCHAR(255) NULL,
    KEY_         VARCHAR(255) NULL,
    DEPLOY_TIME_ datetime NULL,
    TENANT_ID_   VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_ACT_APP_DEPLOYMENT PRIMARY KEY (ID_)
);

CREATE TABLE ACT_APP_DEPLOYMENT_RESOURCE
(
    ID_             VARCHAR(255) NOT NULL,
    NAME_           VARCHAR(255) NULL,
    DEPLOYMENT_ID_  VARCHAR(255) NULL,
    RESOURCE_BYTES_ LONGBLOB NULL,
    CONSTRAINT PK_APP_DEPLOYMENT_RESOURCE PRIMARY KEY (ID_)
);

ALTER TABLE ACT_APP_DEPLOYMENT_RESOURCE
    ADD CONSTRAINT ACT_FK_APP_RSRC_DPL FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_APP_DEPLOYMENT (ID_);

CREATE INDEX ACT_IDX_APP_RSRC_DPL ON ACT_APP_DEPLOYMENT_RESOURCE (DEPLOYMENT_ID_);

CREATE TABLE ACT_APP_APPDEF
(
    ID_            VARCHAR(255) NOT NULL,
    REV_           INT          NOT NULL,
    NAME_          VARCHAR(255) NULL,
    KEY_           VARCHAR(255) NOT NULL,
    VERSION_       INT          NOT NULL,
    CATEGORY_      VARCHAR(255) NULL,
    DEPLOYMENT_ID_ VARCHAR(255) NULL,
    RESOURCE_NAME_ VARCHAR(4000) NULL,
    DESCRIPTION_   VARCHAR(4000) NULL,
    TENANT_ID_     VARCHAR(255) DEFAULT '' NULL,
    CONSTRAINT PK_ACT_APP_APPDEF PRIMARY KEY (ID_)
);

ALTER TABLE ACT_APP_APPDEF
    ADD CONSTRAINT ACT_FK_APP_DEF_DPLY FOREIGN KEY (DEPLOYMENT_ID_) REFERENCES ACT_APP_DEPLOYMENT (ID_);

CREATE INDEX ACT_IDX_APP_DEF_DPLY ON ACT_APP_APPDEF (DEPLOYMENT_ID_);

INSERT INTO ACT_APP_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS,
                                       LIQUIBASE, DEPLOYMENT_ID)
VALUES ('1', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', NOW(), 1, '8:496fc778bdf2ab13f2e1926d0e63e0a2',
        'createTable tableName=ACT_APP_DEPLOYMENT; createTable tableName=ACT_APP_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_APP_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_APP_RSRC_DPL, referencedTableName=ACT_APP_DEPLOYMENT; createIndex...',
        '', 'EXECUTED', NULL, NULL, '3.6.1', '6986085155');


