package ie.atu.week5.customerapp;


public class CustomerService {

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;

    }

}
