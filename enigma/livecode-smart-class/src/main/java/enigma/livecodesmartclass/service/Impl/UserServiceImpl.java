package enigma.livecodesmartclass.service.Impl;

import enigma.livecodesmartclass.model.dto.UserRequest;
import enigma.livecodesmartclass.model.dto.UserResponse;
import enigma.livecodesmartclass.model.entity.User;
import enigma.livecodesmartclass.repository.UserRepository;
import enigma.livecodesmartclass.service.UserService;
import enigma.livecodesmartclass.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    @Override
    public UserResponse createUser(UserRequest request) {
        validationService.validate(request);
        User user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .role(request.getRole())
                .fullName(request.getFullName())
                .build();

        userRepository.save(user);
        return toUserResponse(user);
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return toUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        validationService.validate(request);
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setRole(request.getRole());
        userRepository.save(user);
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user){
        return UserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
