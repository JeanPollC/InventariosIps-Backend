package com.inventariosips.user.controller;

import com.inventariosips.user.dto.UserDTO;
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
    public ResponseEntity<List<UserDTO>> findAllUsersTypes() throws Exception {
        List<UserEntity> lst = userService.findAllUser().stream().toList();
        return ResponseEntity.ok(mapperUser.lstUserEntityToLstUserDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findByIdUser(@PathVariable("id") Integer id) throws Exception {
        UserDTO dto = mapperUser.userEntityToUserDTO(userService.findByIdUser(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@Valid @RequestBody UserDTO userDTO) throws Exception{
        UserEntity userEntity = userService.saveUser(mapperUser.userDTOToUserEntity(userDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userEntity.getIdUser()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") Integer id) throws Exception {
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
