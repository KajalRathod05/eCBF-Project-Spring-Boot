package com.model.SA;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RoleRightsMST")
public class RoleRightsMST {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleid;
    private String rolecode;
    private String rolename;
   //private Integer moduleid;
    private String status;
    private String remarks;

    //Parent-Child Relationship (One Role Right can have Child Access Rights)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid") 
    private List<UserRightsMST> userRights = new ArrayList<>();

}
