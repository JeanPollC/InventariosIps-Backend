package com.inventariosips.userType.controller;

import com.inventariosips.userType.dto.UserTypeDTO;
import com.inventariosips.userType.mapper.IMapperUserType;
import com.inventariosips.userType.model.UserTypeEntity;
import com.inventariosips.userType.service.IUserTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("userstypes")
@RequiredArgsConstructor
public class UserTypeController {

    private final IUserTypeService userTypeService;
    private final IMapperUserType mapperUserType;

    @GetMapping
    public ResponseEntity<List<UserTypeDTO>> findAllUsersTypes() throws Exception {
        List<UserTypeEntity> lst = userTypeService.findAllUserType().stream().toList();
        return ResponseEntity.ok(mapperUserType.lstUserTypeEntityToLstUserTypeDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTypeDTO> findByIdUserType(@PathVariable("id") Integer id) throws Exception {
        UserTypeDTO dto = mapperUserType.userTypeEntityToUserTypeDTO(userTypeService.findByIdUserType(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserTypeEntity> saveUserType(@Valid @RequestBody UserTypeDTO userTypeDTO) throws Exception{
        UserTypeEntity userTypeEntity = userTypeService.saveUserType(mapperUserType.userTypeDTOToUserTypeEntity(userTypeDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userTypeEntity.getIdUserType()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserTypeEntity> updateUserType(@Valid @RequestBody UserTypeDTO userTypeDTO, @PathVariable("id") Integer id) throws Exception {
        userTypeDTO.setIdUserType(id);
        UserTypeEntity userTypeEntity = userTypeService.updateUserType(mapperUserType.userTypeDTOToUserTypeEntity(userTypeDTO), id);

        return ResponseEntity.ok(userTypeEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable("id") Integer id) throws Exception {
        userTypeService.deleteUserType(id);

        return ResponseEntity.noContent().build();
    }

}
