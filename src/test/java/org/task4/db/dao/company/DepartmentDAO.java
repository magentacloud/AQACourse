package org.task4.db.dao.company;

import org.task4.db.dao.base.BaseDAO;
import org.task4.db.dao.company.entity.DepartmentEntity;
import org.task4.db.dao.company.entity.EmployeeEntity;

import java.util.List;

public class DepartmentDAO extends BaseDAO {
    public List<DepartmentEntity> getAllDepartments(){
        final String query = """
                SELECT * FROM DEPARTMENT
                """;
        return jdbi.withHandle(
                handle -> handle.createQuery(query)
                        .mapToBean(DepartmentEntity.class)
                        .list()
        );
    }

    public List<DepartmentEntity> getDepartmentByName(String name){
        final String query = """
                SELECT * FROM DEPARTMENT
                WHERE Name = '%s'
                """.formatted(name);
        return jdbi.withHandle(
                handle -> handle.createQuery(query)
                        .mapToBean(DepartmentEntity.class)
                        .list()
        );
    }
    public DepartmentEntity getDepartmentByID(Integer id){
        final String query = """
                SELECT * FROM DEPARTMENT
                WHERE ID = '%n'
                """.formatted(id);
        return jdbi.withHandle(
                handle -> handle.createQuery(query)
                        .mapToBean(DepartmentEntity.class).one()
        );
    }
}
