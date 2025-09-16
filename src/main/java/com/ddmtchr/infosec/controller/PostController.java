package com.ddmtchr.infosec.controller;

import com.ddmtchr.infosec.entity.Post;
import com.ddmtchr.infosec.service.PostService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post saved = postService.addPost(post);
        return ResponseEntity.ok(saved);
    }
}
