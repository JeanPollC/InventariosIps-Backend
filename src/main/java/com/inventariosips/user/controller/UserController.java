package com.inventariosips.user.controller;

import com.inventariosips.user.dto.request.UserRequestDTO;
import com.inventariosips.user.dto.response.UserResponseDTO;
import com.inventariosips.user.mapper.IMapperUser;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.user.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IMapperUser mapperUser;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsersTypes() throws Exception {
        List<UserEntity> lst = userService.findAllUser().stream().toList();
        return ResponseEntity.ok(mapperUser.lstUserEntityToLstUserResponseDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findByIdUser(@PathVariable("id") Integer id) throws Exception {
        UserResponseDTO dto = mapperUser.UserEntityToUserResponseDTO(userService.findByIdUser(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@Valid @RequestBody UserRequestDTO userDTO) throws Exception{
        UserEntity userEntity = userService.saveUser(mapperUser.userDTOToUserEntity(userDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userEntity.getIdUser()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@Valid @RequestBody UserRequestDTO userDTO, @PathVariable("id") Integer id) throws Exception {
        userDTO.setIdUser(id);
        UserEntity userEntity = userService.updateUser(mapperUser.userDTOToUserEntity(userDTO), id);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) throws Exception {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
