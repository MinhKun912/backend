package ra.payload.respone;
import java.util.List;
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String userName;
    private String email;
    private String name;

    private List<String> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JwtResponse(String token, String userName, String email, Long userId, String name, List<String> roles) {
        this.token = token;
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
