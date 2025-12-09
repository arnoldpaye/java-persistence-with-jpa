            // save employee
            Optional<Employee> savedEmployee = employeeRepository.save(employee);
            System.out.println(savedEmployee);
            if (savedEmployee.isPresent()) {
              Employee newEmployee = savedEmployee.get();
            }
          }
