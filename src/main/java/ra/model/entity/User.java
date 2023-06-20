package ra.model.entity;

import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
//    @Column(name = "friend")
//    private FriendRequest friend;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "username",nullable = false)
    private String userName;
    @Column (name = "age",nullable = false)
    private int age;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name = "avatarUrl")
    @Lob
    private  String avatarUrl;
    @Column(name = "Status")
    private  Boolean Status;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Roles> listRole = new HashSet<>();

}
