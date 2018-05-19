package com.screenerd.service

import com.screenerd.domain.Post
import com.screenerd.domain.User
import com.screenerd.repository.PostRepository
import com.screenerd.repository.UserRepository
import org.springframework.data.repository.CrudRepository
import spock.lang.Specification

/**
 * Created by mathieukostiuk on 27/04/2018.
 */

class PostServiceSpec extends Specification {

    PostService postService
    PostRepository postRepository
    UserRepository userRepository
    User user
    Post post

    void setup() {
        postRepository = Mock()
        userRepository = Mock()
        postService = new PostService()
        postService.postRepository = postRepository
        postService.userRepository = userRepository
    }

    def "check the type of the repository"() {
        expect: "the repository is a Crud Repository"
        postRepository instanceof CrudRepository
    }

    def "test save of a Post in the repository"() {
        given: "a post"
        def post = Mock(Post) {
            getUser() >> Mock(User) {
                getPosts() >> []
            }
        }

        when: "the post is saved"
        postService.savePost(post)

        then: "the save is delegated to the repository"
        1 * postRepository.save(post)
    }

    def "test find all Posts"() {
        when: "requesting for all posts"
        postService.findAllPosts()

        then: "the request is delegated to the repository"
        1 * postRepository.findAll()
    }

    def "test delete a post" () {
        given: "the repo has one post"
        post = Mock(Post) {
            getUser() >> Mock(User) {
                getPosts() >> Mock(ArrayList) {
                    remove(post) >> post
                }

            }
            getId() >> 1
        }
        postService.postRepository = Mock(PostRepository) {
            findOne(1) >> post
        }

        and: "the post is saved"
        postService.savePost(post)
        def id = post.getId()

        when: "we delete the post"
        postService.deletePost(id)

        then: "the deletion is delegated to the repository"
        1 * postService.postRepository.delete(id)
    }
}
