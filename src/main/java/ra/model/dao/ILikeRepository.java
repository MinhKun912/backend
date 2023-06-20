package ra.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Like;
import ra.model.entity.Post;
import ra.model.entity.User;

import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<Like,Long> {
    boolean existsByPostAndUser(Post post,User user);
    Optional<Like> findByUserAndPost(User user, Post post);
    Long countLikeByPostPostId(Long id);
}
