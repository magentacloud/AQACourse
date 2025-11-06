package org.task4;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.task4.db.dao.DaoRepository;
import org.task4.db.dao.company.EmployeeDAO;
import org.task4.db.dao.company.entity.DepartmentEntity;
import org.task4.db.dao.company.entity.EmployeeEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@Slf4j
public class Tests {
    @Test
    public void test(){
        List<EmployeeEntity> entities = DaoRepository.employeeDAO.getAllEmployee();

        Assertions.assertNotNull(entities);
    }

    @Test
    public void test1(){
        List<EmployeeEntity> entities = DaoRepository.employeeDAO.getEmployeeByName("Ann");
        int departmentID = DaoRepository.departmentDAO.getDepartmentByName("HR").get(0).getId();

        if(entities.size() == 1){

            DaoRepository.employeeDAO.setDepartmentByID(entities.get(0).getId(), departmentID);
        }

        entities = DaoRepository.employeeDAO.getEmployeeByName("Ann");

        Assertions.assertEquals(departmentID, entities.get(0).getDepartmentId());
    }

    @Test
    public void test2(){
        int count = 0;
        List<EmployeeEntity> entities = DaoRepository.employeeDAO.getAllEmployee();

        for (int i = 0; i < entities.size(); i++) {
            EmployeeEntity entity = entities.get(i);
            String initialName = entity.getName();
            String updatedName;
            if(initialName.substring(0, 1).equals(initialName.substring(0, 1).toLowerCase())){
                count++;
                updatedName = initialName.substring(0, 1).toUpperCase() + initialName.substring(1);
                DaoRepository.employeeDAO.setNameByID(entity.getId(),updatedName);
            }
        }
    }

    @Test
    public void test3(){
        int departmentID = DaoRepository.departmentDAO.getDepartmentByName("IT").get(0).getId();

        List<EmployeeEntity> entities = DaoRepository.employeeDAO.getEmployeeByDepartmentID(departmentID);

        log.info("Всего сотрудников в IT-отделе:{}", entities.size());
    }

    //Задание 2 удаление отдела
    @Test
    public void deleteDepartmentTest(){
        int departmentID = 3;

        try {
            DepartmentEntity entity = DaoRepository.departmentDAO.getDepartmentByID(2);
        } catch (IllegalStateException exception){
            Assertions.assertEquals("Expected one element, but found none", exception.getMessage());
        }

        try{
            List<EmployeeEntity> entities = DaoRepository.employeeDAO.getEmployeeByDepartmentID(departmentID);
        } catch (IllegalStateException exception){
            Assertions.assertEquals("Expected one element, but found none", exception.getMessage());
        }
    }

}
