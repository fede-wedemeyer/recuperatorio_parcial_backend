package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.Employee;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    String firstName;
    String lastName;
    String Title;
    String reportsTo;
    LocalDateTime birthDate;
    LocalDateTime hireDate;
    String address;
    String city;
    String state;
    String country;
    String postalCode;
    String phone;
    String fax;
    String email;


    public static EmployeeResponse from(Employee employee){
        if (employee == null) { return null;}
        else if (employee.getReportsTo() == null) {
            return EmployeeResponse.builder()
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .Title(employee.getTitle())
                    .reportsTo("No one")
                    .birthDate(employee.getBirthDate())
                    .hireDate(employee.getHireDate())
                    .address(employee.getAddress())
                    .city(employee.getCity())
                    .country(employee.getCountry())
                    .phone(employee.getPhone())
                    .postalCode(employee.getPostalCode())
                    .state(employee.getState())
                    .build();
        }
        else {
            return EmployeeResponse.builder()
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .Title(employee.getTitle())
                    .reportsTo(employee.getReportsTo().getFirstName() + " " + employee.getReportsTo().getLastName())
                    .birthDate(employee.getBirthDate())
                    .hireDate(employee.getHireDate())
                    .address(employee.getAddress())
                    .city(employee.getCity())
                    .country(employee.getCountry())
                    .phone(employee.getPhone())
                    .postalCode(employee.getPostalCode())
                    .state(employee.getState())
                    .build();
        }
    }
}
