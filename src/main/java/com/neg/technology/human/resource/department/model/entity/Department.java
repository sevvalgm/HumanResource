package com.neg.technology.human.resource.department.model.entity;

import com.neg.technology.human.resource.utility.AuditableEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("department") // R2DBC tablo adı
public class Department extends AuditableEntity {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("location")
    private String location;
}
