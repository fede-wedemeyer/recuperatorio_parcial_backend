package federico.wedemeyer8.parcial.services;

import federico.wedemeyer8.parcial.models.Customer;

import federico.wedemeyer8.parcial.repositories.CustomerRepository;
import federico.wedemeyer8.parcial.repositories.EmployeeRepository;
import federico.wedemeyer8.parcial.repositories.Identifier.IdentifierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final IdentifierRepository identifierRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Optional<Customer> findById(Integer id){
        return customerRepository.findById(id);
    }

    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer createCustomer(String firstName, String lastName, String company, String address, String city,
                                   String state, String country, String postalCode, String phone, String fax,
                                   String email, Integer suportRepId) {
        Integer id = identifierRepository.nextValue("Customers");
        val suportRep = employeeRepository.findById(suportRepId).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        val newCustomer = new Customer(id, firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email, suportRep);

        System.out.println(newCustomer);

        return customerRepository.save(newCustomer);
    }

    @Transactional
    public Customer updateCustomer(Integer id, String firstName, String lastName, String company, String address, String city,
                                   String state, String country, String postalCode, String phone, String fax,
                                   String email, Integer suportRepId) {

        val existingCustomer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        val suportRep = employeeRepository.findById(suportRepId).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        existingCustomer.setFirstName(firstName);
        existingCustomer.setLastName(lastName);
        existingCustomer.setCompany(company);
        existingCustomer.setAddress(address);
        existingCustomer.setCity(city);
        existingCustomer.setState(state);
        existingCustomer.setCountry(country);
        existingCustomer.setPostalCode(postalCode);
        existingCustomer.setPhone(phone);
        existingCustomer.setFax(fax);
        existingCustomer.setEmail(email);
        existingCustomer.setSupportRep(suportRep);

        return customerRepository.save(existingCustomer);
    }

    @Transactional
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
