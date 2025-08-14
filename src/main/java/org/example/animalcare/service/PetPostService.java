package org.example.animalcare.service;

import lombok.RequiredArgsConstructor;
import org.example.animalcare.dto.post.PostRequestDto;
import org.example.animalcare.dto.post.PostResponseDto;
import org.example.animalcare.entity.pet.PetPost;
import org.example.animalcare.entity.user.UserGeneral;
import org.example.animalcare.exception.PostNotFoundException;
import org.example.animalcare.exception.UserNotFoundException;
import org.example.animalcare.repository.PostRepository;
import org.example.animalcare.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PetPostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;


    public byte[] getPostImage(Long postId) {
        PetPost petPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        return s3Service.downloadFileBytes(petPost.getPhotoPath());
    }

    public String uploadImage(MultipartFile file,Long postId) throws IOException {
        PetPost petPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        String key = "user-images/" + postId + "/" + file.getOriginalFilename();
        s3Service.uploadFile(key, file.getInputStream(), file.getContentType());
        petPost.setPhotoPath(key);
        postRepository.save(petPost);
        return key;
    }


    public PostResponseDto createPost(PostRequestDto postRequestDto) throws IOException {
        UserGeneral user = userRepository.findById(postRequestDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        PetPost petPost = new PetPost();
          petPost= PetPost.builder()
                  .title(postRequestDto.getTitle())
                  .description(postRequestDto.getDescription())
                  .animalType(postRequestDto.getAnimalType())
                  .breed(postRequestDto.getBreed())
                  .gender(postRequestDto.getGender())
                  .age(postRequestDto.getAge())
                  .color(postRequestDto.getColor())
                  .healthCondition(postRequestDto.getHealthCondition())
                  .location(postRequestDto.getLocation())
                  .user(user)
                  .createdAt(postRequestDto.getCreatedAt())
                  .type(postRequestDto.getType())
                  .build();
          postRepository.save(petPost);

          PostResponseDto postResponseDto = new PostResponseDto();
          postResponseDto= PostResponseDto.builder()
                  .id(petPost.getId())
                  .title(petPost.getTitle())
                  .description(petPost.getDescription())
                  .animalType(petPost.getAnimalType())
                  .breed(petPost.getBreed())
                  .gender(petPost.getGender())
                  .age(petPost.getAge())
                  .color(petPost.getColor())
                  .healthCondition(petPost.getHealthCondition())
                  .location(petPost.getLocation())
                  .photoPath(petPost.getPhotoPath())
                  .userId(petPost.getUser().getId())
                  .createdAt(petPost.getCreatedAt())
                  .type(petPost.getType())
                  .build();
          return postResponseDto;

    }

    public PostResponseDto updatePost(PostRequestDto postRequestDto,Long postId) throws IOException {
        UserGeneral user = userRepository.findById(postRequestDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        PetPost petPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        petPost.setTitle(postRequestDto.getTitle());
        petPost.setDescription(postRequestDto.getDescription());
        petPost.setAnimalType(postRequestDto.getAnimalType());
        petPost.setBreed(postRequestDto.getBreed());
        petPost.setGender(postRequestDto.getGender());
        petPost.setAge(postRequestDto.getAge());
        petPost.setColor(postRequestDto.getColor());
        petPost.setHealthCondition(postRequestDto.getHealthCondition());
        petPost.setLocation(postRequestDto.getLocation());
        petPost.setUser(user);
        petPost.setCreatedAt(postRequestDto.getCreatedAt());
        petPost.setType(postRequestDto.getType());
        postRepository.save(petPost);


        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto=PostResponseDto.builder()
                .id(postId)
                .title(petPost.getTitle())
                .description(petPost.getDescription())
                .animalType(petPost.getAnimalType())
                .breed(petPost.getBreed())
                .gender(petPost.getGender())
                .age(petPost.getAge())
                .color(petPost.getColor())
                .healthCondition(petPost.getHealthCondition())
                .location(petPost.getLocation())
                .photoPath(petPost.getPhotoPath())
                .userId(petPost.getUser().getId())
                .createdAt(petPost.getCreatedAt())
                .type(petPost.getType())
                .build();
        return postResponseDto;

    }

    public PostResponseDto getPostById(Long postId){
        PetPost petPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto=PostResponseDto.builder()
                .id(postId)
                .title(petPost.getTitle())
                .description(petPost.getDescription())
                .animalType(petPost.getAnimalType())
                .breed(petPost.getBreed())
                .gender(petPost.getGender())
                .age(petPost.getAge())
                .color(petPost.getColor())
                .healthCondition(petPost.getHealthCondition())
                .location(petPost.getLocation())
                .photoPath(petPost.getPhotoPath())
                .userId(petPost.getUser().getId())
                .createdAt(petPost.getCreatedAt())
                .type(petPost.getType())
                .build();
        return postResponseDto;
    }

    public Set<PostResponseDto> getAllPostsByUserId(Long userId){
        UserGeneral userGeneral = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Set<PetPost> petPosts = postRepository.findByUser(userGeneral);
       Set<PostResponseDto> postResponseDtos = new HashSet<>();
        if(petPosts==null){
            throw new PostNotFoundException("Post not found for the user :" + userId);
        }else{
            for(PetPost petPost : petPosts){
                PostResponseDto build = PostResponseDto.builder()
                        .id(petPost.getId())
                        .title(petPost.getTitle())
                        .description(petPost.getDescription())
                        .animalType(petPost.getAnimalType())
                        .breed(petPost.getBreed())
                        .gender(petPost.getGender())
                        .age(petPost.getAge())
                        .color(petPost.getColor())
                        .healthCondition(petPost.getHealthCondition())
                        .location(petPost.getLocation())
                        .photoPath(petPost.getPhotoPath())
                        .userId(petPost.getUser().getId())
                        .createdAt(petPost.getCreatedAt())
                        .type(petPost.getType())
                        .build();
                postResponseDtos.add(build);
            }
        }
        return postResponseDtos;
    }

    public void deletePost(Long postId){
        PetPost petPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        postRepository.delete(petPost);
    }



}
