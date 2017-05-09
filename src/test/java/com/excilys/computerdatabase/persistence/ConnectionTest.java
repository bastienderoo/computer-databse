package com.excilys.computerdatabase.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConnectionTest {


    @Test
    public void testConnection() {

        assertNotNull(ConnectionDatabase.INSTANCE.getConnection());

    }
}