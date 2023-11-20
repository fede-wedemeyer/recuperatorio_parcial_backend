package federico.wedemeyer8.parcial.application.controllers;

import federico.wedemeyer8.parcial.application.requests.create.CreateInvoiceRequest;
import federico.wedemeyer8.parcial.application.requests.update.UpdateInvoiceRequest;
import federico.wedemeyer8.parcial.application.responses.InvoiceResponse;
import federico.wedemeyer8.parcial.application.responses.ResponseHandler;
import federico.wedemeyer8.parcial.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Arrays.stream;

@RestController
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/api/invoices")
    public ResponseEntity<Object> getAll() {
        try {
            val invoices = invoiceService.findAll()
                    .stream()
                    .map(InvoiceResponse::from)
                    .toList();
            return ResponseHandler.success(invoices);

        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/api/invoices/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) {
        try {
            val invoice = invoiceService.findById(id);
            return ResponseHandler.success(invoice);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping("/api/invoices")
    public ResponseEntity<Object> create(@RequestBody CreateInvoiceRequest request) {
        try {
            val invoice = invoiceService.createInvoice(request.getCustomerId(), request.getBillingAddress(), request.getBillingCity(), request.getBillingState(), request.getBillingCountry(), request.getBillingPostalCode(), request.getInvoiceItems());
            return ResponseHandler.success(InvoiceResponse.from(invoice));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PutMapping("/api/invoices/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateInvoiceRequest request, @PathVariable Integer id) {
        try {
            val invoice = invoiceService.updateInvoice(id, request.getCustomerId(), request.getBillingAddress(), request.getBillingCity(), request.getBillingState(), request.getBillingCountry(), request.getBillingPostalCode(), request.getInvoiceItems());
            return ResponseHandler.success(InvoiceResponse.from(invoice));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/api/invoices/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseHandler.success("Invoice eliminated");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

}
