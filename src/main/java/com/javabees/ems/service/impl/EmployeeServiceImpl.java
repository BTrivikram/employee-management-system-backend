package com.javabees.ems.service.impl;

import com.javabees.ems.dto.EmployeeDto;
import com.javabees.ems.entity.Employee;
import com.javabees.ems.exception.ResourceNotFoundException;
import com.javabees.ems.repository.EmployeeRepository;
import com.javabees.ems.service.EmployeeService;
import com.javabees.ems.utility.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.employeeeDtoToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not Found"));
        return EmployeeMapper.employeeToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)->EmployeeMapper.employeeToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException(("Employee doesn't exist with given Id: "+employeeId)));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeObj=employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee doesn't exist with given Id: "+employeeId));
        employeeRepository.deleteById(employeeId);
    }


}
