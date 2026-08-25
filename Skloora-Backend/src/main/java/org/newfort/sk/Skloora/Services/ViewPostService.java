package org.newfort.sk.Skloora.Services;

import org.newfort.sk.Skloora.Model.Post;
import org.newfort.sk.Skloora.Repositorys.ViewPostRepo;
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
