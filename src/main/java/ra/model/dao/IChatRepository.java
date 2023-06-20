package ra.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.ChatMessage;

public interface IChatRepository extends JpaRepository<ChatMessage,Long> {
}
