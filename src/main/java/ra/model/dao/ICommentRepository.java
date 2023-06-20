package ra.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.model.entity.Comment;
import ra.model.entity.Post;
import ra.model.entity.User;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPostId(Post post);

    User findByUserCMT(User user);
}
