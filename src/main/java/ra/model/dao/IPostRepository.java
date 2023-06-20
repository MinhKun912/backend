package ra.model.dao;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.model.entity.Post;
import ra.model.entity.User;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
@Query(value = "select * from posts where user_id = ?1",nativeQuery = true)
    List<Post> findPostByUserId(Long id);

}
