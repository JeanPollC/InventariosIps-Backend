package com.inventariosips.user.controller;

import com.inventariosips.user.dto.request.UserRequestDTO;
import com.inventariosips.user.dto.response.UserResponseDTO;
import com.inventariosips.user.mapper.IMapperUser;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.user.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IMapperUser mapperUser;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() throws Exception {
        List<UserEntity> lst = userService.findAllUser().stream().toList();
        return ResponseEntity.ok(mapperUser.lstUserEntityToLstUserResponseDTO(lst));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<UserResponseDTO>> findAllUsersPageable(
            Pageable pageable,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter) throws Exception {
        // 1. Obtener la página de entidades del servicio
        Page<UserEntity> userPage = userService.findAllUser(pageable, filter);

        // 2. Convertir la lista de entidades (content) a DTOs
        List<UserResponseDTO> dtoList = userPage.getContent().stream()
                .map(mapperUser::UserEntityToUserResponseDTO)
                .collect(Collectors.toList());

        // 3. Reconstruir la respuesta Page usando los metadatos de la página original
        Page<UserResponseDTO> dtoPage = new PageImpl<>(
                dtoList,
                pageable,
                userPage.getTotalElements()
        );

        return ResponseEntity.ok(dtoPage);
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
