package dev.aman.jobportalauthservice.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
public class Role extends BaseModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
