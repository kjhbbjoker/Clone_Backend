package com.example.clone.model;

import com.example.clone.dto.PostRequestDto;
import com.example.clone.dto.PostsRequestDto;
import com.example.clone.dto.PostsResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postId;//게시판id

    @Column(nullable = false)
    private String title;//제목

    @Column(nullable = false)
    private String content;//내용

//    @CreatedDate
//    @Column(name="created_at")
//    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Float price;//가격

    @Column(nullable = false)
    private String category;

    @Column private String consumer;//구입자

    @Column
    private boolean state = false;//판매여부

    @Column
    private boolean rated = false;//평가여부


    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;



    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Likes> LikesList  = new ArrayList<>();


//a
//    @Column(nullable = true)
//    private String consumer;//구입자
//
//    @Column(nullable = false)
//    private boolean state;//판매여부
//
//    @Column(nullable = false)
//    private String image;//이미지url
//
    @Column(nullable = false)
    private int viewCnt = 0;//조회수

    @Column(nullable = false)
    private int likeCnt = 0;//조회수


    @Column
    private String image;
//작성날짜는 안넣어도 알아서 컬럼으로 들어감

//이 아래로는 연관관계,유저 id 로 연관관계시키는거랑 유저 엔테티그자체 넘겨주는거 골라서 쓰기
//    @ManyToOne
//    @JoinColumn
//    private User id;

//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;


//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    @JsonIgnoreProperties({"post"})
//    private List<Post> postList = new ArrayList<Post>();

    //public Post(PostRequestDto postRequestDto, User user)
    public Post(PostRequestDto postRequestDto)
    {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.price = postRequestDto.getPrice();


        //this.image = postRequestDto.getImage();


    }

    public Post(PostRequestDto postRequestDto, User user) {

        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.price = postRequestDto.getPrice();
        this.user = user;

    }

    public void update(String title, String image, float price, String content, String category, User user)
    {
        this.title = title;
        this.image= image;
        this.price= price;
        this.content=content;
        this.category=category;
        this.user= user;

    }

    public Post(PostsResponseDto postsResponseDto, User user){
        this.postId= postsResponseDto.getPostId();
        this.title= postsResponseDto.getTitle();
        this.user= user;
        this.content=postsResponseDto.getContent();
        this.price = postsResponseDto.getPrice();
        this.viewCnt=postsResponseDto.getViewCnt();
        this.likeCnt=postsResponseDto.getLikeCnt();



    }



    public Post(PostsRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.price = requestDto.getPrice();
        this.category = requestDto.getCategory();
        this.image = requestDto.getImage();
        this.user = user;
    }
}

