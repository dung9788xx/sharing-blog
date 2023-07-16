package com.nhom2.sharingblog.config;

import org.hibernate.dialect.MySQLDialect;

public class CustomDialect extends MySQLDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8mb3_unicode_ci";
    }
}