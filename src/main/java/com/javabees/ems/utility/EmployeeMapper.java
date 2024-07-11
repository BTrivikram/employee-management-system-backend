package com.javabees.ems.utility;

import com.javabees.ems.dto.EmployeeDto;
import com.javabees.ems.entity.Employee;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeMapper {
    public static EmployeeDto employeeToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee employeeeDtoToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
