package org.saravanakumar.Skloora_Mail_Service.Mailings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DBPool {

    @Autowired
    public DataSource ds;
}
