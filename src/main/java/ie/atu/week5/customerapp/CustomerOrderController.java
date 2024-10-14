package ie.atu.week5.customerapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerOrderController {

    private final CustomerService customerService;
    private final OrderService orderService; // Include OrderService

    public CustomerOrderController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService; // Inject OrderService
    }

    @PostMapping("/customer-with-orders")
    public ResponseEntity<String> createCustomerWithOrders(@Valid @RequestBody CustomerOrderRequest customerOrderRequest) {
        // Step 1: Save the customer
        Customer savedCustomer = customerService.createCustomer(customerOrderRequest.getCustomer());

        // Step 2: Link each order to the saved customer
        for (Order order : customerOrderRequest.getOrders()) {
            order.setCustomerId(savedCustomer.getId()); // Set customerId for each order
            orderService.createOrder(order); // Use OrderService to create the order
        }

        return ResponseEntity.ok("Customer and orders created successfully");
    }
}
