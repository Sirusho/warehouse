package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Log4j2
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse implements CommonEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    @ToString.Include
    private UUID id;

    @Column(name = "name")
    private String name;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "warehouse_materials", joinColumns = {
            @JoinColumn(name = "warehouse_id")}, inverseJoinColumns = {
            @JoinColumn(name = "material_id")})
    private Set<Material> materials = new HashSet<>();

}
