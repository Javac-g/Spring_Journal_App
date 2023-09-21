package com.denisov.journal.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.*;

@Getter
@Setter
public class ResponseDTO {
    private String name ;
    private Type carType;
    private Inventory inventory;
    private UUID id;

    public ResponseDTO(){
        Supplier<UUID> randomSupplier = UUID::randomUUID;
        Stream<UUID> infiniteStream = Stream.generate(randomSupplier);
        id = infiniteStream.findFirst().orElseThrow(()-> new IllegalStateException("No UUID generated"));
    }
    @Override
    public boolean equals(Object ob){
        if (this == ob){
            return true;
        }
        if (ob == null || getClass() != ob.getClass()){
            return false;
        }
        ResponseDTO response = (ResponseDTO)ob;
        return Objects.equals(this.getId(),response.getId())
                && Objects.equals(this.getCarType(),response.getCarType())
                && Objects.equals(this.inventory,response.getInventory())
                && Objects.equals(this.getName(),response.getName());
    }
    @Override
    public int hashCode(){
        int hash = 7;
        hash = hash * 5 + Objects.hashCode(id);
        hash = hash * 5 + Objects.hashCode(carType);
        hash = hash * 5 + Objects.hashCode(inventory);
        hash = hash * 5 + Objects.hashCode(name);
        return hash;
    }

}
