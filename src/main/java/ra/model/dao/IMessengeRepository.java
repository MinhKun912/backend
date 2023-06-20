package ra.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Message;
import ra.model.entity.User;

import java.util.List;
@Repository
public interface IMessengeRepository extends JpaRepository<Message,Long> {
    List<Message> findBySenderOrRecipient(User sender,User recipient);
}
