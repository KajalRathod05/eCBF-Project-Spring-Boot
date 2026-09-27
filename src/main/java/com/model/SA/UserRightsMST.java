package com.model.SA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UserRightsMST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRightsMST {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userrightsid;
    private Integer moduleid;
    private String mastername;
    private Integer addopn;
    private Integer editopn;
    private Integer viewopn;
    private Integer deleteopn;
    
    @ManyToOne
    @JoinColumn(name = "roleid")
    private RoleRightsMST roleRights;
}
