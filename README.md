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
 

#### 프론트 - [전해강](https://github.com/jeonhaekang/hanghae_clone_01),[유영탁](https://github.com/YJ-my)
#### 백 - [김채경](https://github.com/KimCG1130/Clone_Backendd),[김종훈](https://github.com/kjhbbjoker/Clone_Backend)

#### 프론트 GIT-HUB 주소 https://github.com/YJ-my/sparta-w6-letter


### API 설계서
https://www.notion.so/8-Clone-Coding-e23b83af71d744b492a8771ee301d9cd




## 🧾ERD

 <p> </p>
 <p> </p>
 <p> </p>
### Entity Relatuonship Diagram

![엔티티 연관관계](https://user-images.githubusercontent.com/97422693/155443917-e63eed67-005d-44ce-91ce-b55b86913f6a.PNG)


## 🏷 페이지 구현하려던 와이어프레임
<details>
 <summary>회원가입 로그인</summary>
 <div markdown="1">


<p align="center"float="left">
 
  <img src="https://user-images.githubusercontent.com/97422693/155450373-b425a182-e903-48e6-88b6-0219ad14c374.png" width="600" />
  
</p>

 
</details>
 
 

 <details>
 <summary>메인페이지</summary>
 <div markdown="1">


<p align="center"float="left">
 
  <img src="https://user-images.githubusercontent.com/97422693/155450620-2d50a05b-e8a7-4c05-9d5c-e9e4762a34b5.png" width="600" />
  
</p>

 
</details>
  
  
  
  <details>
 <summary>디테일 페이지</summary>
 <div markdown="1">


<p align="center"float="left">
 
  <img src="https://user-images.githubusercontent.com/97422693/155450664-9fb42f85-31a1-4bb0-8c4e-df5e3bb06297.png" width="600" />
  
</p>

 
</details>
    
    
    
  <details>
 <summary>마이 페이지</summary>
 <div markdown="1">


<p align="center"float="left">
 
  <img src="https://user-images.githubusercontent.com/97422693/155450729-96a6084a-3fc6-440c-804e-2ded1ef3312f.png" width="600" />
  
</p>

 
</details>
   
   
   
<details>
 <summary>후기 페이지</summary>
 <div markdown="1">


<p align="center"float="left">
 
  <img src="https://user-images.githubusercontent.com/97422693/155450915-fc151c8b-4d42-488e-9a9a-e101023eacde.png" width="600" />
  
</p>

 
</details>
 
 <details>
 <summary>작성 페이지</summary>
 <div markdown="1">


<p align="center"float="left">
 
  <img src="https://user-images.githubusercontent.com/97422693/155451063-240711e8-1afd-4192-8560-09282cb7fda9.png" width="600" />
  
</p>

 
</details>
   
   
   
  
   




## 🏷 API Table
<details>
 <summary>자세히 보기</summary>
 <div markdown="1">


<p align="center"float="left">
  <img src="https://user-images.githubusercontent.com/97422693/155446071-e26104d9-31df-4fe4-bfbb-5fcc41d289bd.PNG" width="600" />
  <img src="https://user-images.githubusercontent.com/97422693/155446093-e2481c41-2e07-4052-b8d3-da585ac9e829.PNG" width="600" /> 
  <img src="https://user-images.githubusercontent.com/97422693/155446111-d6cffd5f-4625-4593-aeaa-c0a3b3dcde43.PNG" width="600" />
</p>

 
</details>


## 🧾Trouble Shooting

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
<summary>S3 버킷 관련 ACL 관련 에러 </summary>
<div markdown="1">

 
 <img src="https://user-images.githubusercontent.com/97422693/155449111-a04a7db8-f8ab-4841-bf4e-edc89047e996.PNG" width="600" />


AWS에 들어가서 객체 소유권에 대한  ACL 활성화를 해주었다 


</div>
</details>
