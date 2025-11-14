package lk.ijse.cmjd.researchtracker.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lk.ijse.cmjd.researchtracker.user.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // ---------------------- REGISTER -----------------------
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(UserRole.MEMBER)     // default
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        // Create JWT with MEMBER role
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        return AuthResponse.builder().token(token).build();
    }

    // ---------------------- AUTHENTICATE -----------------------
    public AuthResponse authenticate(AuthRequest request) {

        // ✔ SUPER ADMIN CHECK (admin / 123)
        if (request.getUsername().equals("admin") && request.getPassword().equals("123")) {
            String token = jwtService.generateToken("admin", "ADMIN");
            return AuthResponse.builder().token(token).build();
        }

        // ✔ Normal DB user authentication
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole().name()   // IMPORTANT: include correct role!
        );

        return AuthResponse.builder().token(token).build();
    }
}

