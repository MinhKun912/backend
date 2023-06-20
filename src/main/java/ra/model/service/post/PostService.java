package ra.model.service.post;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dao.IPostRepository;
import ra.model.entity.Post;

import java.util.List;
@Service
public class PostService implements IPostService{
    @Autowired
    IPostRepository repository;
    public List<Post> findByUser(Long id){
        return repository.findPostByUserId(id);
    };
    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public void deleteById(Long id) {
    repository.deleteById(id);
    }

    @Override
    public Post findById(Long id) {
        return repository.findById(id).get();
    }
}
