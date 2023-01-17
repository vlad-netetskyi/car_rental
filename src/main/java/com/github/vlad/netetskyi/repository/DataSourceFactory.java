package com.github.vlad.netetskyi.repository;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static DataSource dataSource;

    public static synchronized DataSource getInstance() {
        if (dataSource == null) {
            dataSource = getPGDataSource();
        }

        return dataSource;
    }

    private static DataSource getPGDataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerNames(new String[]{"localhost"});
        ds.setDatabaseName("car_rental");
        ds.setUser("car_rental_app");
        ds.setPassword("car_123_app");
        return ds;
    }
}
