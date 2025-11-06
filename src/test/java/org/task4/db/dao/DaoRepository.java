package org.task4.db.dao;

import lombok.experimental.UtilityClass;
import org.task4.db.dao.company.DepartmentDAO;
import org.task4.db.dao.company.EmployeeDAO;

@UtilityClass
public class DaoRepository {
    public static final EmployeeDAO employeeDAO = new EmployeeDAO();
    public static final DepartmentDAO departmentDAO = new DepartmentDAO();

}
