# 항해99 클론코딩 8조 - 당근마켓📮

<img src="https://user-images.githubusercontent.com/97422693/155444394-feb226b9-fdd8-4575-afaa-b179e190abc1.PNG" width="500px">



 
 ## 🤷 프로젝트 소개 
 <p> 당근마켓 클론 코딩입니다. </p>
 <p> </p>
 <p> </p>
 <p> </p>
  
[당근마켓!] http://jeonhaekang.shop.s3-website.ap-northeast-2.amazonaws.com
</br>


 ## 🏼‍💻 개발기간 및 팀원소개
 ### 기간: 2022.02.18 ~ 2022.02.24   
 <p> </p>
 <p> </p>
 <p> </p>
 

#### 프론트 - [전해강](https://github.com/YJ-my),[유영탁](https://github.com/YJ-my)
#### 백 - [김채경](https://github.com/howCanIFind),[김종훈](https://github.com/kjhbbjoker)

#### 프론트 GIT-HUB 주소 https://github.com/YJ-my/sparta-w6-letter


### API 설계서
https://www.notion.so/8-Clone-Coding-e23b83af71d744b492a8771ee301d9cd




## ERD

 <p> </p>
 <p> </p>
 <p> </p>
### Entity Relatuonship Diagram

![엔티티 연관관계](https://user-images.githubusercontent.com/97422693/155443917-e63eed67-005d-44ce-91ce-b55b86913f6a.PNG)



## 🏷 API Table
<details>
 <summary>자세히 보기</summary>


<p align="center"float="left">
  <img src="https://user-images.githubusercontent.com/90129613/146339824-1a6ab044-a7f5-4738-a3f2-f99bced644d5.png" width="600" />
  <img src="https://user-images.githubusercontent.com/90129613/146339833-a2246d8e-73e2-4f22-a4a9-bcf762897f07.png" width="600" /> 
  <img src="https://user-images.githubusercontent.com/90129613/146339840-b15bf4e0-4b26-4020-97b2-56f8c215aee8.png" width="600" />
</p>

<!-- |기능|Method|URL|Request|Response|
|:-----:|:----:|----|----|----|
|로그인 요청|POST|/user/login|{username: "iamuser",</br>password: "1234"}| |
|회원</br>가입|POST|/api/signup|{username:"iamuser"</br>,"password:"1234"</br>,passwordCheck:"1234"}||
|아이디 중복 검사|POST|/api/idCheck|{username:"iamuser"}|{result:false}|
|로그인 여부</br>확인|GET|/api/islogin||{userInfo:{username:"username"}</br>}|
|로그아웃|GET|/api/logout|||
|사진</br>업로드|POST|/api/images||{imageUrl:"/images/cancle.png"}|
|게시글 작성|POST|/api/posts|{title:"제목입니다",</br>content:"반가워요",</br>imageUrl:"/images/cancle.png"}||
|게시글 수정|PUT|/api/posts/{postId}|{content:"반갑습니다"}||
|게시글 삭제|DELETE||||
|랜덤</br>게시글 조회|GET|/api/posts||{postId:1,</br>title:"제목",</br>content:"글내용",</br>comments:[{</br>commentId:1,</br>comment:"댓글내용",</br>createdAt:LocalDateTime}]</br>}|
|내가</br>작성한 게시글 조회|GET|/api/comments/{postId}||{postId:1,</br>title:"제목",</br>content:"글내용",</br>comments:[{</br>commentId:1,</br>comment:"댓글내용",</br>createdAt:LocalDateTime},</br>{commentId:2,</br>comment:"댓글내용2",</br>createdAt:LocalDateTime}]</br>}|
|내가</br> 댓글을 작성한 게시글 조회|GET|/api/comments/{commentId}||{postId:1,</br>title:"제목",</br>content:"글내용",</br>comments:[{</br>commentId:1,</br>comment:"댓글내용",</br>createdAt:LocalDateTime},</br>{commentId:2,</br>comment:"댓글내용2",</br>createdAt:LocalDateTime}]</br>}|
|댓글</br> 작성|POST|/api/comments/{postId}|{comment:"댓글"}||
|피드</br>페이지|GET|/api/feeds||[myPosts:[{</br>postId:1</br>title:"제목",</br>content:"내용",},</br>{postId:2,</br>title:"제목2",</br>content"내용2"}],</br>myComments:[{</br>commentId:1,</br>comment:"댓글",</br>createdAt:LocalDateTime},</br>{commentId:2,</br>comment:"댓글2",</br>createdAt:LocaldateTime}]</br>]|
 -->
 
</details>


## 

 <p> </p>
 <p> </p>
 <p> </p>


<details>
<summary>프론트측으로 MultipartFile과 JSON으로 보낼때 문제 발생 </summary>
<div markdown="1">

```java
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
```
@RequestPart로 둘다 multipart/form-data 형태로 전송하는 것으로 해결

</div>
</details>







<details>
<summary>게시물 삭제시 Likes와의 연관 관계로 인해 좋아요를 누르면 삭제 안되는 문제</summary>
<div markdown="1">


```java
 @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Likes> LikesList  = new ArrayList<>();
```
cascade = CascadeType.REMOVE 를 이용하여 해결 

</div>
</details>




<details>
<summary>거래를 한 상대에게 무한정으로 후기를 남길 수 있음</summary>
<div markdown="1">

```java
public RatedDto addRate(RateDto rateDto){ //유저 평가하기

        RatedDto ratedDto = new RatedDto();
        Post post = postRepository.findById(rateDto.getPostId()).get();
        post.setRated(true);


        int rate1 = rateDto.getRate();
        User user = userRepository.findById(rateDto.getId()).get();  //평가 점수 더하는 로직
        int currentRate = user.getRate();
        user.setRate(currentRate + rate1);
        User user2 = userRepository.save(user);


        rateDto.setRate(user2.getRate());
        ratedDto.setRated(true);
        return ratedDto;
    }
```
프론트측에 유저의 아이디만 받고 평점을 남기는 식으로 하였는데 이번에 post아이디도 함께 받아서 포스트에 boolean rated = false;//평가여부 항목을 추가하여 post 아이디를 받아서
  해당 게시물을 찾고 true로 바꿔서 거래를 한 게시물을 알려주었습니다.

</div>
</details>
    
    
    
<details>
<summary>서버 과부하 최소화를 위한 성능 최적화 작업</summary>
<div markdown="1">




..........

</div>
</details>
