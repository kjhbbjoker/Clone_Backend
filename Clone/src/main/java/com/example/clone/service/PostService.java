package com.example.clone.service;


import com.example.clone.dto.*;
import com.example.clone.model.Likes;
import com.example.clone.model.Post;
import com.example.clone.model.User;
import com.example.clone.repository.LikeRepository;
import com.example.clone.repository.PostRepository;
import com.example.clone.repository.UserRepository;
import com.example.clone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final LikeRepository likeRepository;


    //생성
    /*@Transactional
    public Response savePost(PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
    //public Response savePost(PostRequestDto postRequestDto) {
    //public PostResponseDto savePost(PostRequestDto postRequestDto) {

        User user = userRepository.findByNickname(userDetailsImpl.getUser().getNickname()).orElse(null);

        Post post = new Post(postRequestDto, user);
        //Post post = new Post(postRequestDto);;
        postRepository.save(post);

        Response response = new Response();
        response.setStatus(200);

        //PostResponseDto postResponseDto = new PostResponseDto();
        //postResponseDto.setStatus(200);


        return response;
        //return postResponseDto;
    }*/


    //글 작성
    public Long writePost(PostsRequestDto requestDto, User user) {

        Post post = new Post(requestDto, user);


        return postRepository.save(post).getPostId();
    }

    //삭제
   /* public Response deletePost(Long postId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl)
    //public Response deletePost(Long postId)
    //public PostResponseDto deletePost(Long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );

        postRepository.deleteById(postId);

        Response response = new Response();
        response.setStatus(200);
        //PostResponseDto postResponseDto = new PostResponseDto();
        //postResponseDto.setStatus(200);


        return response;
        //return postResponseDto;
    }*/

    @Transactional
    public boolean deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        postRepository.delete(post);
        return true;
    }


    //수정
   /*@Transactional
    public Response updatePost(Long postId, PostsRequestDto postsRequestDto) {
        Post getPost = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 포스트입니다.") );
        getPost.update(postsRequestDto);

        Response response = new Response();
        response.setStatus(200);

        return response;
    }*/


    @Transactional
    public void editpost(PostsRequestDto postsRequestDto, Long postId, User user) {
//        Posts posts = new Posts(requestDto, user);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        //post.update(requestDto.getContent(),requestDto.getTitle(),requestDto.getCategory(),requestDto.getImage(),requestDto.getPrice(),post.getUser());
        post.update(postsRequestDto.getTitle(), postsRequestDto.getImage(), postsRequestDto.getPrice(), postsRequestDto.getContent(), postsRequestDto.getCategory(), post.getUser());
    }


    public List<Post> getPostList() {


        //return commentRepository.findAllByOrderByCreatedAtDesc();
        return postRepository.findAllByOrderByCreatedAtDesc();

    }

    public PostsResponseDto getdetails(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );

        return new PostsResponseDto(
                postId,
                post.getContent(),
                post.getPrice(),
                post.getCategory(),
                post.getCreatedAt(),
                post.getTitle(),
                post.getImage(),
                post.getLikeCnt(),
                post.getViewCnt(),
                post.getUser()
        );
    }


    @Transactional
    //public Post getPostOne(Long postId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
    public PostGetResponse getPostOne(Long postId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {

        User user = userRepository.findByNickname(userDetailsImpl.getUser().getNickname()).orElse(null);
        PostGetResponse postGetResponse = new PostGetResponse();
        postGetResponse.setUser(user);
        postGetResponse.setStatus(200);
        return postGetResponse;


//        return postRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("해당하는 게시글이 없습니다. id = " + postId)
//        );
    }

//    public PostGetResponse getPostOne(Long postId)
//    {
//        Post getPost =  postRepository.findById(postId).orElseThrow(
//                ()->new IllegalArgumentException("해당 글이 존재하지 않습니다."));
//
//
//
//        PostGetResponseDto postGetResponseDto = new PostGetResponseDto(getPost);
//
//        PostGetResponse postGetResponse = new PostGetResponse();
//
//        postGetResponse.setStatus(200);
//        postGetResponse.setGetResponseDto(postGetResponseDto);
//
//        return postGetResponse;
//    }

//    @Transactional
//    public void deletePost(Long id) {
//        postRepository.deleteById(id);
//    }


    @Transactional
    public Response selectConsumer(Long postId, Long id) {
        Response response = new Response();
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 존재하지 않습니다.")
        );

        System.out.println(id);
//        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
//        );
        String checkCon = post.getConsumer();


        if (checkCon == null) {
            post.setConsumer(String.valueOf(id));//고른 유저의 아이디값
            post.setState(true);//판매완료로 바뀜
            System.out.println(checkCon);
            System.out.println(id);


            response.setResult(true);
        }

        return response;


    }


    public LikePostDto getLikeList (User user) { //좋아요한 게시글 목록
        Long userId = user.getId();
        LikePostDto dto = new LikePostDto();
        User users = userRepository.findById(userId).get();
        List <Likes> likesList = likeRepository.findAllByUser(users);
        List <Post> posts = new ArrayList<>();
        for (Likes item : likesList) {
            posts.add(item.getPost());
        }

        dto.setPosts(posts);
        return dto;
    }
}
