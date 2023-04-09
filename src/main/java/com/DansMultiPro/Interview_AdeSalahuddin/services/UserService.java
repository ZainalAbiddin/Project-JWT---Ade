package com.DansMultiPro.Interview_AdeSalahuddin.services;

import com.DansMultiPro.Interview_AdeSalahuddin.repository.RoleRepository;
import com.DansMultiPro.Interview_AdeSalahuddin.repository.UserRepository;
import com.DansMultiPro.Interview_AdeSalahuddin.repository.UserRoleRepository;
import com.DansMultiPro.Interview_AdeSalahuddin.security.JwtUtils;
import com.DansMultiPro.Interview_AdeSalahuddin.utils.LoggerUtils;
import com.DansMultiPro.Interview_AdeSalahuddin.dto.JwtResponse;
import com.DansMultiPro.Interview_AdeSalahuddin.dto.LoginUserRequest;
import com.DansMultiPro.Interview_AdeSalahuddin.dto.RegistrationDTO;
import com.DansMultiPro.Interview_AdeSalahuddin.dto.UserResponseDTO;
import com.DansMultiPro.Interview_AdeSalahuddin.models.Role;
import com.DansMultiPro.Interview_AdeSalahuddin.models.User;
import com.DansMultiPro.Interview_AdeSalahuddin.models.UserRole;
import com.DansMultiPro.Interview_AdeSalahuddin.security.MyUserDetails;
import com.DansMultiPro.Interview_AdeSalahuddin.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private LoggerUtils loggerUtils;

    public ResponseEntity<Object> authenticationUser(LoginUserRequest userRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            JwtResponse jwtResponse = JwtResponse.builder()
                    .token(jwt)
                    .id(userDetails.getUserId())
                    .username(userDetails.getUsername())
                    .email(userDetails.getEmail())
                    .roles(roles)
                    .build();
            loggerUtils.infoLogger("Success", "user-service", jwtResponse.toString());
            return ResponseHandler.generateResponse("successfully login", HttpStatus.OK, jwtResponse);

        } catch (Exception e) {
            loggerUtils.errorLogger(e.getMessage(), "user-service");
            return ResponseHandler.generateResponse("Login Failed", HttpStatus.BAD_REQUEST, "Failed Login!");
        }
    }

    public ResponseEntity<Object> registrationUser(RegistrationDTO registrationDTO) {

        if (registrationDTO.getUsername() == null || registrationDTO.getPassword() == null
                || registrationDTO.getName() == null || registrationDTO.getEmail() == null) {
            loggerUtils.errorLogger("Request Null", "user-service");
            return ResponseHandler.generateResponse("Please check again your input", HttpStatus.BAD_REQUEST, "Failed registration!");
        }
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            loggerUtils.errorLogger("Username Exist", "user-service");
            return ResponseHandler.generateResponse("Username has been exist", HttpStatus.BAD_REQUEST, "Failed registration!");
        }

        User user = registrationDTO.convertToEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSave = userRepository.save(user);
        this.newRole(userSave);
        UserResponseDTO result = this.convertResponse(userSave);

        loggerUtils.infoLogger("p", "user-service", String.valueOf(result));
        return ResponseHandler.generateResponse("successfully registered! please login", HttpStatus.CREATED, result);

    }

    public UserResponseDTO convertResponse(User userSave) {
        List<Role> rolesUser = roleRepository.findByUsersUserUserId(userSave.getUserId());
        List<String> roleDTO = rolesUser.stream().map(Role::getRoleName).collect(Collectors.toList());
        return userSave.convertToResponse(roleDTO);
    }

    public void newRole(User user) {
        Role role;
        var roleDB = roleRepository.findByRoleNameIgnoreCase("ROLE_USER");

        if (roleDB.isPresent()) {
            role = roleDB.get();
        } else {
            Role roleSave = new Role();
            roleSave.setRoleName("ROLE_USER");
            role = roleRepository.save(roleSave);
        }

        UserRole addRole = new UserRole();
        addRole.setRole(role);
        addRole.setUser(user);

        userRoleRepository.save(addRole);
    }

}

