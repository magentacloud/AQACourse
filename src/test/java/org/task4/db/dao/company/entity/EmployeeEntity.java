package org.task4.db.dao.company.entity;

import lombok.*;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Data
public class EmployeeEntity {
    @ColumnName("ID")
    private int id;

    @ColumnName("Name")
    private String name;

    @ColumnName("DepartmentID")
    private int departmentId;
}
