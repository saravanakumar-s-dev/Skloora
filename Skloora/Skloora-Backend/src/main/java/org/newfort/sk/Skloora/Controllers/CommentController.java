package org.newfort.sk.Skloora.Controllers;

import org.newfort.sk.Skloora.Model.Comment;
import org.newfort.sk.Skloora.Repository.CookieRepo;
import org.newfort.sk.Skloora.Service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CookieRepo cookieRepo;

    @PostMapping("/add")
    public String addComment(

            @RequestParam String post_by,

            @RequestParam String post_des,

            @RequestParam Date post_date,

            @RequestParam String comment_text,

            @CookieValue(
                    value = "rememberMe",
                    required = false
            )
            String login_id

    ) throws SQLException {


        if (login_id == null || login_id.isBlank()) {

            return "User not logged in";
        }
        String email = cookieRepo.TokenToEmail(login_id);


        if (email == null || email.isBlank()) {

            return "Invalid login";
        }

        commentService.addComment(

                post_by,

                post_des,

                post_date,

                email,

                comment_text
        );


        return "Comment added successfully";
    }

    @GetMapping("/get")
    public List<Comment> getComments(

            @RequestParam String post_by,

            @RequestParam String post_des,

            @RequestParam Date post_date

    ) throws SQLException {

        return commentService.getComments(

                post_by,

                post_des,

                post_date
        );
    }
}