package com.denisov.journal.Controllers;

import com.denisov.journal.Model.RequestDTO;
import com.denisov.journal.Model.ResponseDTO;
import com.denisov.journal.Services.Service;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/App")


public class Controller { 

    private Service service;

    public void setService(Service service){
        this.service = service;
    }

    @PostMapping(value = "/Add",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO addUser(@RequestBody RequestDTO user){
        return service.addUser(user);
    }
    @PostMapping(value = "/Find",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ResponseDTO> findUser(@RequestParam String name){
        return service.findUser(name);
    }
    @PutMapping(value = "/Update",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO updateUser(@RequestBody ResponseDTO user,@RequestParam UUID id){
        return service.updateUser(user,id);
    }
    @DeleteMapping(value = "/Delete",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ResponseDTO> deleteUser(@RequestParam String name){
        return service.findUser(name);
    }

}
