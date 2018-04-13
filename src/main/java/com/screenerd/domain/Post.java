package com.screenerd.domain;

import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by telly on 09/03/18.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @NotNull
    private byte[] image;

    @NotNull
    private String imageFormat;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    private String description;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private  List<Like> likes = new ArrayList<>();

    public Post(){}

    public void addLike(Like like) {
        likes.add(like);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
