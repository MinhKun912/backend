package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Lob
    private String imgUrl;
    private String content;
    @ManyToOne
    private Like like;
    @ManyToOne
    private User user;
    @OneToMany
   private Set<ImageData> listImages;
    @OneToMany(mappedBy = "postId",fetch = FetchType.EAGER)
    private List<Comment> listComment;
}
