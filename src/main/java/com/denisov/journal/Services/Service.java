package com.denisov.journal.Services;

import com.denisov.journal.Model.RequestDTO;
import com.denisov.journal.Model.ResponseDTO;
import com.denisov.journal.Model.Type;
import com.denisov.journal.Model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Service {
    private  List<ResponseDTO> dataBase = new ArrayList<>();

    public void log(String type, ResponseDTO user){
        byte[] data = ("\nType: " + type + "\nName: " + user.getName()).getBytes();

        try(FileOutputStream fileOutputStream = new FileOutputStream("log.dat");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            byteArrayOutputStream.write(data);
            byteArrayOutputStream.writeTo(fileOutputStream);
            dataOutputStream.writeUTF("\nId: "+ user.getId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCar(RequestDTO requestDTO,ResponseDTO user){
        for (Car x: requestDTO.getCarList()){
            switch (x.getValue()){
                case "BMW" -> user.setCarType(Type.BMW);
                case "AUDI" -> user.setCarType(Type.AUDI);
                case "FERRARI" -> user.setCarType(Type.FERRARI);
                default -> user.setCarType(Type.WALKER);
            }
        }
    }

    public ResponseDTO addUser(RequestDTO info){
        ResponseDTO user = new ResponseDTO();
        user.setName(info.getName());
        user.setInventory(info.getInventory());
        setCar(info,user);
        log("Created",user);
        dataBase.add(user);
		System.out.println(dataBase);
        return user;
    }
    public List<ResponseDTO> findUser(String name){

        System.out.println("Searching for user with name: " + name);

		List<ResponseDTO> matchingUsers = dataBase.stream()
            .filter(user -> user.getName().trim().equals(name.trim()))
            .collect(Collectors.toList());

		System.out.println("Matching users: " + matchingUsers);
		return matchingUsers;
    }

    public ResponseDTO updateUser(ResponseDTO info, UUID id){
        Optional<ResponseDTO> updated = dataBase.stream()
                .filter(person -> person.getId() == id)
                .map(person-> {
                    person.setName(info.getName());
                    person.setCarType(info.getCarType());
                    person.setId(info.getId());
                    person.setInventory(info.getInventory());
                    log("Updated",person);
                    return person;
        }).findFirst();
        if(updated.isPresent()){
            return updated.get();
        }else {
            System.out.println("Person not found");
        }
        return null;
    }
    public UUID deleteUser(UUID id){
        Optional<UUID> deleted = dataBase.stream()
                .filter(person -> person.getId() == id)
                .map(person ->{
                    dataBase.remove(person);
                    log("Deleted",person);
                    return person.getId();
                }).findFirst();
        if(deleted.isPresent()){
            return deleted.get();
        }else {
            System.out.println("Person not deleted");
        }
        return null;
    }
}
