package com.angegonzalez.springaoplp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.angegonzalez.springaoplp.model.Student;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringRestClient {

    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/api/v1/students";
    private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/students/{id}";
    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/students";
    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/students/{id}";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/students/{id}";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();

        // Step1: first create a new employee
        springRestClient.createEmployee();

        // Step 2: get new created employee from step1
        springRestClient.getEmployeeById();

        // Step3: get all students
        springRestClient.getEmployees();

        // Step4: Update employee with id = 1
        springRestClient.updateEmployee();

        // Step5: Delete employee with id = 1
        springRestClient.deleteEmployee();
    }

    private void getEmployees() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity < String > entity = new HttpEntity < String > ("parameters", headers);

        ResponseEntity < String > result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
                String.class);

        System.out.println(result);
    }

    private void getEmployeeById() {

        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "1");

        RestTemplate restTemplate = new RestTemplate();
        Student result = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, Student.class, params);

        System.out.println(result);
    }

    private void createEmployee() {

        Student newEmployee = new Student("admin", "admin", "admin@gmail.com");

        RestTemplate restTemplate = new RestTemplate();
        Student result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newEmployee, Student.class);

        System.out.println(result);
    }

    private void updateEmployee() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "1");
        Student updatedEmployee = new Student("admin123", "admin123", "admin123@gmail.com");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updatedEmployee, params);
    }

    private void deleteEmployee() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "1");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
    }
}