package com.example.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    @GetMapping("/{requestedId}")
    public ResponseEntity<Post> findById() {
        Post postTest = new Post(99L,"Hello~~~");
        return ResponseEntity.ok(postTest);
    }
}
