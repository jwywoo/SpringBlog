package com.example.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController to control behaviors related to Post
// @RequestMapping if request url contains /posts
@RestController
@RequestMapping("/posts")
public class PostController {
    // @GetMapping if request url contains /posts/requestedId
    // @PathVariable variables of request urls
    @GetMapping("/{requestedId}")
    public ResponseEntity<Post> findById(@PathVariable Long requestedId) {
        if (requestedId.equals(99L)) {
            Post postTest = new Post(99L,"Hello~~~");
            return ResponseEntity.ok(postTest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
