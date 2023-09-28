package com.denisov.journal.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class RequestDTO {
    private String name;
    private Inventory inventory;
    private List<Car> carList = new ArrayList<>();
    

}
