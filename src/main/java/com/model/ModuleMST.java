package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "modulemst")
public class ModuleMST {

    @Id
    private Integer moduleid;
    private String modulename;
    private String icon;
}