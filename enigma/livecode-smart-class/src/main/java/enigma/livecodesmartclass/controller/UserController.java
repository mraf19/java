package enigma.livecodesmartclass.controller;

import enigma.livecodesmartclass.model.dto.CommonResponse;
import enigma.livecodesmartclass.model.dto.UserRequest;
import enigma.livecodesmartclass.model.dto.UserResponse;
import enigma.livecodesmartclass.model.entity.User;
import enigma.livecodesmartclass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<CommonResponse<UserResponse>>createUser(
            @RequestBody UserRequest request
            ){
        UserResponse user = userService.createUser(request);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse
                        .<UserResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create user")
                        .data(user)
                        .build());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<UserResponse>>> getAllUser(){
        List<UserResponse> users = userService.getAllUsers();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse
                        .<List<UserResponse>>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully get all users")
                        .data(users)
                        .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<UserResponse>>getUserById(
            @PathVariable(name = "id") String id
    ){
        UserResponse user = userService.getUserById(id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse
                        .<UserResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully get user")
                        .data(user)
                        .build());
    }

    public ResponseEntity<CommonResponse<UserResponse>>updateUser(
            @RequestBody UserRequest request,
            @PathVariable("id") String id
    ){
        request.setId(id);
        UserResponse user = userService.updateUser(request);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse
                        .<UserResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully update user")
                        .data(user)
                        .build());
    }
}
