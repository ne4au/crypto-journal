package com.nechay.jour.utils;

/**
 * @author anechaev
 * @since 05.05.2024
 */
public final class AppProperties {
    private AppProperties() {}

    public static final class DB {
        private DB() {}

        public static final String HOST = "cj.db.host";
        public static final String PORT = "cj.db.port";
        public static final String NAME = "cj.db.name";
        public static final String USERNAME = "cj.db.username";
        public static final String PASSWORD = "cj.db.password";
    }
}
