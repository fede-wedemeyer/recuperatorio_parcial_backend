package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreateCustomerRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateCustomerRequest;
import federico.wedemeyer8.parcial.application.responses.AlbumResponse;
import federico.wedemeyer8.parcial.application.responses.CustomerResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.models.Customer;
import federico.wedemeyer8.parcial.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping("/api/customers/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        try {
            val customer = customerService.findById(id)
                    .map(aCustomer -> ResponseHandler.success(CustomerResponse.from(aCustomer)))
                    .orElseGet(ResponseHandler::notFound);
            return ResponseEntity.ok(customer);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/api/customers")
    public ResponseEntity<Object> getAll() {
        try {
            val customers = customerService.findAll()
                    .stream()
                    .map(CustomerResponse::from)
                    .toList();
            return ResponseEntity.ok(customers);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping("/api/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody CreateCustomerRequest request) {
        try {
            val customer = customerService.createCustomer(request.getFirstName(), request.getLastName(), request.getCompany(),
                    request.getAddress(), request.getCity(), request.getState(), request.getCountry(), request.getPostalCode(),
                    request.getPhone(), request.getFax(), request.getEmail(), request.getSupportRepId());

            return ResponseEntity.ok(CustomerResponse.from(customer));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PutMapping("/api/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@RequestBody UpdateCustomerRequest request, @PathVariable Integer id) {
        try {
            val customer = customerService.updateCustomer(id, request.getFirstName(), request.getLastName(), request.getCompany(),
                    request.getAddress(), request.getCity(), request.getState(), request.getCountry(), request.getPostalCode(),
                    request.getPhone(), request.getFax(), request.getEmail(), request.getSupportRepId());
            return ResponseHandler.success(CustomerResponse.from(customer));

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Integer id) {
        try {
        customerService.deleteCustomer(id);
            return ResponseHandler.success("Customer eliminated");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

}
