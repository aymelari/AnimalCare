package org.example.animalcare.controller;

import lombok.RequiredArgsConstructor;
import org.example.animalcare.dto.post.PostRequestDto;
import org.example.animalcare.dto.post.PostResponseDto;
import org.example.animalcare.service.PetPostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("adoption/post")
@RequiredArgsConstructor
public class PetPostController {
    private final PetPostService petPostService;

    @PostMapping(value="/new" )
    public ResponseEntity<PostResponseDto> newPost(@RequestBody  PostRequestDto postRequestDto) throws IOException {
        return ResponseEntity.ok(petPostService.createPost(postRequestDto));

    }

    @PutMapping("edit/{id}")
    public ResponseEntity<PostResponseDto> editPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) throws IOException {
        return ResponseEntity.ok(petPostService.updatePost(postRequestDto,id));
    }

    @GetMapping({"/{id}"})
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

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Long id) {
        byte[] imageBytes = petPostService.getPostImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + id + ".jpg\"")
                .body(imageBytes);
    }

    @PostMapping(value = "/{id}/upload-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadUserImage(@PathVariable Long id,
                                                  @ModelAttribute("file") MultipartFile file) throws IOException {
        String key = petPostService.uploadImage( file,id);
        return ResponseEntity.ok(key);
    }


}
