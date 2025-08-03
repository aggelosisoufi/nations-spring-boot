package com.isoufi.angelos.nationservice.entity.mariadb;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "guests")
@Getter
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}
