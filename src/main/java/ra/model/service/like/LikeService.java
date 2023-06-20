package ra.model.service.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dao.ILikeRepository;
import ra.model.entity.Like;
import ra.model.entity.Post;
import ra.model.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService implements ILikeService{
    @Autowired
    private ILikeRepository repository;

    @Override
    public List<Like> findAll() {
        return null;
    }

    public Like save(Like like){
       return repository.save(like);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Like findById(Long id) {
        return null;
    }

    public Long countLike(Long id) {
        return repository.countLikeByPostPostId(id);
    }

    public Optional<Like> findByPostAndUser( User user,Post post) {
        return repository.findByUserAndPost(user, post);
    }
    public boolean existsByPostAndUser(Post post,User user){
        return repository.existsByPostAndUser(post,user);
    }

    public void addLike(User userLike, Post postLike) {
        Like like = new Like();
        like.setUser(userLike);
        like.setPost(postLike);
        repository.save(like);
    }

    public void removeLike(User user, Post post) {
        Optional<Like> like = repository.findByUserAndPost(user, post);
        if (like.isPresent()) {
            repository.delete(like.get());
        }
    }
}
