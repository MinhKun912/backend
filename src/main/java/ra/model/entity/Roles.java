package ra.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Data
public class Roles {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int RoleId;
@Column(name ="roleName")
@Enumerated(EnumType.STRING)
    private ERole roleName;
}
