package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pagetypemst")
public class PageTypeMST {

    @Id
    private Integer pagetypeid;

    private String mastername;

    private String icon;

    private String filename;

    private Integer moduleid;

}
