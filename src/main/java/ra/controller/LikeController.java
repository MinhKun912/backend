package ra.controller;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Like;
import ra.model.entity.Post;
import ra.model.entity.User;
import ra.model.service.like.LikeService;
import ra.model.service.post.PostService;
import ra.model.service.user.UserService;
import ra.payload.request.LikeRequest;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/like")
public class LikeController {
    @Autowired
    private LikeService service;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @GetMapping("/getLike")
    public Optional<Like> getLike(User user, Post post){
        Optional<Like> list=service.findByPostAndUser(user,post);
        return list;
    }
    @GetMapping("/count/{id}")
    public ResponseEntity<?> countLikeByPostId(@PathVariable Long  id){
        Post post = postService.findById(id);
        if (post!=null){
            Long count = service.countLike(id);
            return (new ResponseEntity<>(count,HttpStatus.OK));
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLike(@RequestBody LikeRequest like) {
        User user = userService.findById(like.getUser_id());
        Post post = postService.findById(like.getPost_id());
        Optional<Like> newLike = service.findByPostAndUser(user, post);
        if (newLike.isPresent()) {
            newLike.get().setStatus(!newLike.get().isStatus());
        } else {
            Like like1= new Like();

            like1.setUser(user);
                like1.setPost(post);
            like1.setStatus(true);
            service.save(like1);
            post.setLike(like1);
        }
        return (new ResponseEntity<>( HttpStatus.OK));

    }
}
