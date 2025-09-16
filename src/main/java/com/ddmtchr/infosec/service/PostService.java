package com.ddmtchr.infosec.service;

import com.ddmtchr.infosec.entity.Post;
import com.ddmtchr.infosec.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> Post.builder()
                        .id(post.getId())
                        .title(HtmlUtils.htmlEscape(post.getTitle()))
                        .content(HtmlUtils.htmlEscape(post.getContent()))
                        .author(HtmlUtils.htmlEscape(post.getAuthor()))
                        .build())
                .toList();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }
}
