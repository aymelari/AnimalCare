package org.example.animalcare.controller;

import lombok.RequiredArgsConstructor;
import org.example.animalcare.dto.post.PostRequestDto;
import org.example.animalcare.dto.post.PostResponseDto;
import org.example.animalcare.service.PetPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("adoption/post")
@RequiredArgsConstructor
public class PetPostController {
    private final PetPostService petPostService;

    @PostMapping("/new")
    public ResponseEntity<PostResponseDto> newPost(@RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(petPostService.createPost(postRequestDto));

    }

    @PutMapping("edit/{id}")
    public ResponseEntity<PostResponseDto> editPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(petPostService.updatePost(postRequestDto,id));
    }

    @GetMapping({"/id"})
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(petPostService.getPostById(id));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Set<PostResponseDto>> getAllPosts(@PathVariable Long id) {
        return ResponseEntity.ok(petPostService.getAllPostsByUserId(id));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Void> removePost(@PathVariable Long id) {
        petPostService.deletePost(id);
        return ResponseEntity.ok().build();
    }




}
