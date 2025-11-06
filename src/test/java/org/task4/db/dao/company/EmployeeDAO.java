package org.task4.db.dao.company;

import org.task4.db.dao.base.BaseDAO;
import org.task4.db.dao.company.entity.EmployeeEntity;

import java.util.List;

public class EmployeeDAO extends BaseDAO {
    public List<EmployeeEntity> getAllEmployee(){
        final String query = """
        SELECT *
        FROM Employee
                           \s""";

        return jdbi.withHandle(
                handle -> handle.createQuery(query)
                        .mapToBean(EmployeeEntity.class)
                        .list()
        );
    }

    public List<EmployeeEntity> getEmployeeByName(String name){
        final String query = """
        SELECT *
        FROM Employee
        WHERE NAME = '%s'
                           \s""".formatted(name);

        return jdbi.withHandle(
                handle -> handle.createQuery(query)
                        .mapToBean(EmployeeEntity.class)
                        .list()
        );
    }
    public List<EmployeeEntity> getEmployeeByDepartmentID(int departmentID){
        final String query = """
        SELECT *
        FROM Employee
        WHERE DepartmentID = %d
                           \s""".formatted(departmentID);

        return jdbi.withHandle(
                handle -> handle.createQuery(query)
                        .mapToBean(EmployeeEntity.class)
                        .list()
        );
    }

    public void setDepartmentByID(int employeeID, int departmentID){
        final String query = """
                UPDATE Employee
                SET DepartmentID = %d
                WHERE ID = %d
                """.formatted(departmentID, employeeID);
        jdbi.withHandle(handle -> handle.createUpdate(query).execute());
    }

    public void setNameByID(int employeeID, String employeeName){
        final String query = """
                UPDATE Employee
                SET Name = '%s'
                WHERE ID = %d
                """.formatted(employeeName, employeeID);
        jdbi.withHandle(handle -> handle.createUpdate(query).execute());
    }
}
