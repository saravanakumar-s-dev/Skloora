<<<<<<< HEAD
package org.newfort.sk.Skloora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

=======
package org.newfort.sk.Skloora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DBPool {

    @Autowired
    public DataSource ds;

}
>>>>>>> 43f612d06bc51106f5a6a8b8cdcc7ce5570bc9ec
