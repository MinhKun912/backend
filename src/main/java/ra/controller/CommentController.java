package ra.controller;

import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Comment;
import ra.model.entity.Post;
import ra.model.entity.User;
import ra.model.service.comment.CommentService;
import ra.model.service.post.PostService;
import ra.model.service.user.UserService;
import ra.payload.request.CommentRequest;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth/comments")
public class CommentController {
    private UserService userService;
    private PostService postService;
    private CommentService commentService;
    @GetMapping("/getByPostId/{id}")
    public ResponseEntity<List<Comment>> getListCmtById(@PathVariable Long id){
        List<Comment> listComment = commentService.getListCommentByPostId(postService.findById(id));
        return new ResponseEntity<>(listComment,HttpStatus.CREATED);
    }
    @PostMapping("/comment")
        public ResponseEntity<?>addComment(@RequestBody CommentRequest comment){
        User user_id= userService.findById(comment.getUser_id());
        Post post_id=postService.findById(comment.getPost_id());
        Comment comment1=new Comment();
        comment1.setContent(comment.getContent());
        comment1.setUserCMT(user_id);
        comment1.setPostId(post_id);
        return new ResponseEntity<>(commentService.addCmt(comment1), HttpStatus.OK);
    }
//    @GetMapping("/post/{post_id}")
//    public ResponseEntity<?> getCommentsByPostId(@PathVariable Long postId) {
//        List<Comment> comments = commentService.getCmt(postId);
//        return new ResponseEntity<>(comments, HttpStatus.OK);
//    }
}
