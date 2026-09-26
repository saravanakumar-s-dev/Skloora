<<<<<<< HEAD
package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Model.Post;
import org.newfort.sk.Skloora.Repository.ViewPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

=======
package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Model.Post;
import org.newfort.sk.Skloora.Repository.ViewPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ViewPostService {

    @Autowired
    private ViewPostRepo VPR;

    public List<Post> getAllPost() throws SQLException {
        return VPR.getAllPost();
    }

}
>>>>>>> 43f612d06bc51106f5a6a8b8cdcc7ce5570bc9ec
