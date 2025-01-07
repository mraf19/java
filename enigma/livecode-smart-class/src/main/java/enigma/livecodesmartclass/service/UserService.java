package enigma.livecodesmartclass.service;

import enigma.livecodesmartclass.model.dto.UserRequest;
import enigma.livecodesmartclass.model.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponse createUser(UserRequest request);
    UserResponse getUserById(String id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UserRequest request);
}
