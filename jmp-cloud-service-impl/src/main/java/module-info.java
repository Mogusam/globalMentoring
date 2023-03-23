module jmp.cloud.service.impl {
    requires jpm.dto;
    requires jmp.service.api;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.xml;
    requires java.persistence;
    requires java.logging;
    requires jdk.unsupported;

    exports org.cloud.service to application;
}