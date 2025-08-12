package org.example.animalcare.controller;

import lombok.RequiredArgsConstructor;
import org.example.animalcare.dto.user.UserRequestDto;
import org.example.animalcare.dto.user.UserResponseDto;
import org.example.animalcare.service.UserService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adoption/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<Void> newUser(@RequestBody UserRequestDto user) {
        System.out.println("DEBUG userType: " + user.getUserType());
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
        public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("edit/{Id}")
    public ResponseEntity<UserResponseDto> editUser(@PathVariable long Id, @RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.updateUserFields(user, Id));
    }
}
