package org.newfort.sk.Skloora.Services;

import java.sql.SQLException;
import java.util.List;

import org.newfort.sk.Skloora.Getters_Setters.Post;
import org.newfort.sk.Skloora.Repositorys.ViewPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewPostService {

    @Autowired
    private ViewPostRepo VPR;

    public List<Post> getAllPost() throws SQLException
    {
        return VPR.getAllPost();
    }

}
