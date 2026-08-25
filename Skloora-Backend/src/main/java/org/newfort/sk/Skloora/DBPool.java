package org.newfort.sk.Skloora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DBPool {

    @Autowired
    public DataSource ds;

}
