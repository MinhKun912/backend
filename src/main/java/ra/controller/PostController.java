package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.model.entity.Post;
import ra.model.entity.User;
import ra.model.service.post.IPostService;
import ra.model.service.post.PostService;
import ra.model.service.storage.StorageService;
import ra.model.service.user.UserService;
import ra.payload.request.PostRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private IPostService service;
    @Autowired
    private PostService postService;
    @Autowired
    private StorageService serviceUpload;

    @GetMapping("/postUpload")
    public List<Post> getService() {
        List<Post> listPost = service.findAll();
        return listPost;
    }
    @GetMapping("/profile/{id}")
    public List<Post> listPost(@PathVariable Long id){
        List<Post> profileListPost = postService.findByUser(id);
        return profileListPost;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostRequest post) {
       User user= userService.findById(post.getUser_id());
        Post p = new Post();
        p.setUser(user);
        p.setContent(post.getContent());
        p.setImgUrl(post.getImgUrl());
        service.save(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PostMapping("/upImg")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImage = serviceUpload.uploadImage(file);
        //
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = serviceUpload.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
}

