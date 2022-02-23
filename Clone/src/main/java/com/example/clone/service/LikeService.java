package com.example.clone.service;

import com.example.clone.dto.LikeUserDto;
import com.example.clone.model.Post;
import com.example.clone.repository.PostRepository;
import com.example.clone.dto.LikeRequestDto;
import com.example.clone.dto.LikeResponseDto;
import com.example.clone.model.Likes;
import com.example.clone.model.User;
import com.example.clone.repository.LikeRepository;
import com.example.clone.repository.UserRepository;
import com.example.clone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public LikeResponseDto clickLike(Long postId, UserDetailsImpl userDetails) {
        LikeRequestDto likeRequestDto = new LikeRequestDto();
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 존재하지 않습니다.")
        );

        Likes likesCheck = likeRepository.findByUserAndPost(userDetails.getUser(), post).orElse(null);//후에 이게 있냐없냐에 따라 스테이트 변화
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        Likes likes = new Likes();
        if (likesCheck == null) {
            likes = likeRepository.save(likeRequestDto.toEntity(post, user));
            List<Likes> likesList = likeRepository.findAllByPost(post);

            //post.setState(true);
            post.setLikeCnt(likesList.size());//라이크한리스트 세서 사이즈값으로 돌려줌

            return likes.toDto(true, likesList.size());
        }
        else {
            likeRepository.deleteById(likesCheck.getId());

            List<Likes> likesList = likeRepository.findAllByPost(post);

            post.setLikeCnt(likesList.size());

            return likesCheck.toDto(false, likesList.size());
        }
    }



    public LikeUserDto getLike (Long postId) {
        LikeUserDto dto = new LikeUserDto();
        Post post = postRepository.findById(postId).get();
        List <Likes> likesList = likeRepository.findAllByPost(post);
        List <User> users = new ArrayList<>();
        for (Likes item : likesList) {
            users.add(item.getUser());
        }

        dto.setUsers(users);
        return dto;
    }

//    public Optional<Likes> getLikesList(Long postId, UserDetailsImpl userDetails){
//
//        Post post = postRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("해당 글이 존재하지 않습니다.")
//        );
//
//        return likeRepository.findByUserAndPost(userDetails.getUser(), post);
//
//    }


}
