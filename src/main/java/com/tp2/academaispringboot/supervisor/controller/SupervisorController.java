package com.tp2.academaispringboot.supervisor.controller;

import com.tp2.academaispringboot.supervisor.dto.*;
import com.tp2.academaispringboot.supervisor.mapping.SupervisorMapper;
import com.tp2.academaispringboot.supervisor.model.SupervisorEntity;
import com.tp2.academaispringboot.supervisor.service.SupervisorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Supervisor", description = "Supervisor API")
@RestController
@RequestMapping("/api/v1/supervisors")
@CrossOrigin
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private SupervisorMapper supervisorMapper;

    @Operation(summary = "Get all Supervisors", description = "Get all Supervisors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all Supervisors"),
            @ApiResponse(responseCode = "404", description = "Supervisors not found")})
    @GetMapping
    public List<SupervisorResource> getAllSupervisors() {
        return supervisorMapper.toResource(supervisorService.getAllSupervisors());
    }

    @Operation(summary = "Create Supervisor", description = "Create Supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Supervisor"),
            @ApiResponse(responseCode = "404", description = "Supervisor not created")})
    @PostMapping("/create")
    public SupervisorResource createSupervisor(@RequestBody CreateSupervisorResource supervisorResource) {
        return supervisorMapper.toResource(supervisorService.createSupervisor(supervisorMapper.toEntity(supervisorResource)));
    }

    @Operation(summary = "Update Supervisor", description = "Update Supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Supervisor"),
            @ApiResponse(responseCode = "404", description = "Supervisor not updated")})
    @PutMapping("/{id}")
    public SupervisorResource updateSupervisor(@PathVariable(name = "id") Long id, @RequestBody UpdateSupervisorResource supervisorResource) {
        return supervisorMapper.toResource(supervisorService.updateSupervisor(id, supervisorMapper.toEntity(supervisorResource)));
    }

    @Operation(summary = "Login Supervisor", description = "Login Supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login Supervisor"),
            @ApiResponse(responseCode = "404", description = "Supervisor not logged in")})
    @PostMapping("/login")
    public SupervisorResource loginSupervisor(@RequestBody LoginSupervisorResource loginSupervisorResource) {
        return supervisorMapper.toResource(supervisorService.loginSupervisor(loginSupervisorResource.getEmail(), loginSupervisorResource.getPassword()));
    }

    @Operation(summary = "Get Supervisor by id", description = "Get Supervisor by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Supervisor"),
            @ApiResponse(responseCode = "404", description = "Supervisor not found")})
    @GetMapping("/{id}")
    public SupervisorResource getSupervisorById(@PathVariable(name = "id") Long id) {
        return supervisorMapper.toResource(supervisorService.getSupervisorById(id));
    }

    @Operation(summary = "Delete Supervisor by id", description = "Delete Supervisor by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Supervisor"),
            @ApiResponse(responseCode = "404", description = "Supervisor not deleted")})
    @DeleteMapping("/{id}")
    public SupervisorResource deleteSupervisorById(@PathVariable(name = "id") Long id) {
        SupervisorResource supervisorResource = supervisorMapper.toResource(supervisorService.getSupervisorById(id));
        supervisorService.deleteSupervisor(id);
        return supervisorResource;
    }

    //update password
    @Operation(summary = "Update Supervisor password", description = "Update Supervisor password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Supervisor password"),
            @ApiResponse(responseCode = "404", description = "Supervisor password not updated")})
    @PatchMapping("/update-password")
    public SupervisorResource updatePassword(@RequestBody UpdatePasswordResource updatePasswordResource) {
        SupervisorEntity supervisorEntity = supervisorService.getSupervisorByEmail(updatePasswordResource.getEmail());
        supervisorService.updatePassword(updatePasswordResource.getPassword(), updatePasswordResource.getEmail());
        return supervisorMapper.toResource(supervisorEntity);
    }

}
