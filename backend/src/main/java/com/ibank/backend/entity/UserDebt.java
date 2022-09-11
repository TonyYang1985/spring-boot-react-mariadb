package com.ibank.backend.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_debt")
public class UserDebt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotEmpty()
    @Column(nullable = true, name = "user_id")
    private int userId;
    private double debt;
    @NotEmpty()
    private String creditor;
    @NotEmpty()
    private String status;

    @Column(nullable = true, name = "created_at")
    private Date createdAt;
    @Column(nullable = true, name = "created_by")
    private String createdBy;
    @Column(nullable = true, name = "updated_at")
    private Date updatedAt;
    @Column(nullable = true, name = "updated_by")
    private String updatedBy;
   
   
}