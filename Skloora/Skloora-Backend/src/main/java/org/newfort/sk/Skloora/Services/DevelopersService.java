<<<<<<< HEAD
package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Model.Developers;
import org.newfort.sk.Skloora.Repository.DevelopersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

=======
package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Model.Developers;
import org.newfort.sk.Skloora.Repository.DevelopersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DevelopersService {

    @Autowired
    private DevelopersRepo DR;

    public List<Developers> getAllDevs(String token) throws SQLException {

        return DR.getAllDevs(token);

    }

    public List<Developers> serchDev(String query, String token) throws SQLException {

        return DR.searchDev(query, token);
    }

}
>>>>>>> 43f612d06bc51106f5a6a8b8cdcc7ce5570bc9ec
