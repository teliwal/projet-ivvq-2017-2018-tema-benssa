package com.screenerd.controller

import com.screenerd.domain.Post
import com.screenerd.domain.User
import com.screenerd.repository.PostRepository
import com.screenerd.repository.UserRepository
import com.screenerd.service.LikeService
import spock.lang.Specification

/**
 * Created by telly on 19/05/18.
 */
class LikeControllerSpec extends Specification{

    LikeController likeController
    LikeService likeService
    UserRepository userRepository
    PostRepository postRepository

    def setup(){
        likeService = Mock()
        userRepository = Mock()
        postRepository = Mock()
        likeController = new LikeController()
        likeController.likeService = likeService
        likeController.userRepository = userRepository
        likeController.postRepository = postRepository
    }

    def "test add like"(){
        given: "a user with the id exists"
        userRepository.findOne(1) >> Mock(User)
        and: "a post with the id 1 exists"
        postRepository.findOne(1) >> Mock(Post)

        when: "the add like URL is triggered"
        likeController.addLike(1,1,2)

        then: "the save is delegated to the likeService"
        1 * likeService.saveLike(_)
    }

}
