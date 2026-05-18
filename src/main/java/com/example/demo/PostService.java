package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + id));
    }

    @Transactional
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post update(Long id, Post updated) {
        Post post = findById(id);
        post.setTitle(updated.getTitle());
        post.setContent(updated.getContent());
        post.setAuthor(updated.getAuthor());
        return post;
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}