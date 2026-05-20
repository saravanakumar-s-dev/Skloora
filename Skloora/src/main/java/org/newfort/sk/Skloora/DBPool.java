package org.newfort.sk.Skloora;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBPool {

    @Autowired
    public DataSource ds;

}
