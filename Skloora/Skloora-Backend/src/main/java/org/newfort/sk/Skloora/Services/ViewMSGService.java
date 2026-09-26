<<<<<<< HEAD
package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Model.MSG;
import org.newfort.sk.Skloora.Repository.MSGViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

=======
package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Model.MSG;
import org.newfort.sk.Skloora.Repository.MSGViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ViewMSGService {

    @Autowired
    private MSGViewRepo MVR;


    public List<MSG> getAllMSG(String email) throws SQLException {

        return MVR.getAllMSG(email);

    }

}
>>>>>>> 43f612d06bc51106f5a6a8b8cdcc7ce5570bc9ec
