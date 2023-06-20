package ra.model.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dao.ICommentRepository;
import ra.model.entity.Comment;
import ra.model.entity.Post;
import ra.model.entity.User;

import java.util.List;

@Service
public class CommentService {
@Autowired
    private ICommentRepository repository;
public List<Comment> getListCommentByPostId (Post postId){
    return repository.findAllByPostId(postId);

}
public Comment addCmt(Comment comment){
    return repository.save(comment);
}


//public findCommentByPostId(Long id){
//    return repository.findByPostId(id);
//}
public User getUserCmt(User user){
    return repository.findByUserCMT(user);
}

}
