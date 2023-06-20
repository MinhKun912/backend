package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.jwt.JwtTokenProvider;
import ra.model.entity.ERole;
import ra.model.entity.Roles;
import ra.model.entity.User;
import ra.model.service.Role.IRoleService;
import ra.model.service.user.IUserService;
import ra.payload.request.LoginRequest;
import ra.payload.request.SignupRequest;
import ra.payload.respone.JwtResponse;
import ra.payload.respone.MessageResponse;
import ra.security.UserDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider provider;
    private IRoleService iRoleService;
    private IUserService services;
    private PasswordEncoder passwordEncoder ;



@GetMapping
    public List<User> findAll(){
      List<User> list =  services.findAll();
      return list;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {
        if (services.existsByUserName(request.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Tài khoản đã tồn tại"));
        }if (services.exitsByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Email đã tồn tại"));
        }
        User user=new User();
        user.setUserName(request.getUserName());
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(true);
        Set<String> strRoles=request.getRoles();
        Set<Roles> listRole=new HashSet<>(  );
        if (strRoles==null){
            Roles userRole=iRoleService.findByRoleName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("Error: Role is not found"));
            listRole.add(userRole);
        }else {
            strRoles.forEach(role->{
                switch (role){
                    case "admin":
                        Roles adminRole=iRoleService.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(()-> new RuntimeException("error"));
                        listRole.add(adminRole);
                    case "user":
                        Roles userRole = iRoleService.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
                        listRole.add(userRole);
                }
            });
        }
        user.setListRole(listRole);
        services.saveOrUpdate(user);
        return ResponseEntity.ok(new MessageResponse("Đăng ký thành công !!"));
    }
@PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
    Authentication authentication =authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetails userDetails=(UserDetails) authentication.getPrincipal();
    String jwt = provider.generateToken(userDetails);
    List<String> listRole=userDetails.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toList());
    return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getEmail(),userDetails.getUserID(),userDetails.getName(),listRole));
}
}
