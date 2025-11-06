package org.task4.db.dao.company.entity;

import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Data
public class DepartmentEntity {
    @ColumnName("ID")
    private int id;

    @ColumnName("Name")
    private String name;
}
