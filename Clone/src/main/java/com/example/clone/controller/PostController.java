package com.example.clone.controller;


import com.example.clone.config.S3Uploader;
import com.example.clone.dto.*;
import com.example.clone.model.Post;
import com.example.clone.security.UserDetailsImpl;
import com.example.clone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    private  final S3Uploader s3Uploader;


    //메인페이지 조회 리스트
    @GetMapping("/post")
    public List<Post> getPost(){
        return postService.getPostList();

    }


    //상세페이지
    /*@GetMapping("post/{postId}")//하나 골라서 상세정보
    public PostGetResponse getPostOne(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl)
    //public Post getPostOne(@PathVariable Long postId)
    //public Post getPostOne(@PathVariable Long postId)
    {
        return postService.getPostOne(postId, userDetailsImpl);
        //return postService.getPostOne(postId);
    }*/


    @GetMapping("/post/{postId}")
    public PostsResponseDto getDetails(@PathVariable Long postId) {

        return postService.getdetails(postId);
    }

//    @GetMapping("/post/{postId}")
//    public PostGetResponse getPostOne(@PathVariable Long postId)
//    {
//        return postService.getPostOne(postId);
//    }

    //저장
   /* @PostMapping("post")
    public Response savePost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
    //public Response savePost(@RequestBody PostRequestDto postRequestDto) {
    //public PostResponseDto savePost(@RequestBody PostRequestDto postRequestDto) {

       return postService.savePost(postRequestDto, userDetailsImpl);
       // return postService.savePost(postRequestDto);

    }*/
    @PostMapping("/post")
    public ResponseEntity<String> writePost(@RequestPart("file") MultipartFile multipartFile, @RequestPart("post") PostsRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        System.out.println(requestDto.getContent());

        // String image = s3Uploader.upload(multipartFile, "postImage");
        String image = s3Uploader.upload(multipartFile,"postImage");
        requestDto.setImage(image);
        postService.writePost(requestDto, userDetails.getUser());
        return ResponseEntity.ok()
                .body("작성되었습니다 true");
    }






    //삭제
   /* @DeleteMapping("post/{postId}")
    public Response deletePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl)
    //public Response deletePost(@PathVariable Long postId)
    //public PostResponseDto deletePost(@PathVariable Long postId)
    {
        return postService.deletePost(postId, userDetailsImpl);
        //return postService.deletePost(postId);
    }*/

    //수정
    @PutMapping("post/{postId}")
    public ResponseEntity<String> editpost(@RequestPart("file") MultipartFile multipartFile, @PathVariable Long postId,
                                           @RequestPart("post")PostsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String image = s3Uploader.upload(multipartFile,"postImage");
        requestDto.setImage(image);
        postService.editpost(requestDto,postId, userDetails.getUser());
        return ResponseEntity.ok()
                .body("수정되었습니다 true");
    }



    @DeleteMapping("post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok()
                .body("삭제되었습니다 true");
    }




    @PostMapping("/post/state/{postId}")
    public Response SelectConsumer(@PathVariable Long postId, @RequestBody PurchaseRequestDto purchaseRequestDto) {


        System.out.println(purchaseRequestDto.getId());
        return postService.selectConsumer(postId, purchaseRequestDto.getId());

    }




}
