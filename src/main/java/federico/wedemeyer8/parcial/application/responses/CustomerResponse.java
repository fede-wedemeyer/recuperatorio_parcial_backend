package federico.wedemeyer8.parcial.application.responses;

import federico.wedemeyer8.parcial.models.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    Integer id;
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String country;
    String postalCode;
    String phone;
    String fax;
    String email;
    EmployeeResponse employee;



    public static CustomerResponse from(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .city(customer.getCity())
                .country(customer.getCountry())
                .phone(customer.getPhone())
                .postalCode(customer.getPostalCode())
                .state(customer.getState())
                .employee(EmployeeResponse.from(customer.getSupportRep()))
                .build();
    }

}
