package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")

public class Authentication {

    /** Role用の列挙型 */
    public static enum Role {
        一般, 管理者
    }

    /** 主キー。Null不許可 */
    @Id
    @Column(length = 20, nullable = false)
    @NotEmpty
    private String code;

    @Column(length = 255, nullable = false)
    @NotEmpty
    private String password;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    
}