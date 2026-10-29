package com.cda.Product.service;

import com.cda.Product.dto.CustomerRequestDto;
import com.cda.Product.dto.CustomerResponseDto;
import com.cda.Product.model.Address;
import com.cda.Product.model.Customer;
import com.cda.Product.repository.AddressRepository;
import com.cda.Product.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{
    @Autowired
    CustomerRepository customerRepository;
    //@Autowired
    //AddressRepository addressRepository;


    public void createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = createCustomerFromDto(customerRequestDto);
        customerRepository.save(customer);
    }

    private Customer createCustomerFromDto(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer(customerRequestDto.getFirstName(), customerRequestDto.getLastName(), customerRequestDto.getEmail(), customerRequestDto.getPhone());
        Address address = new Address();
        address.setStreet(customerRequestDto.getAddress().getStreet());
        address.setCity(customerRequestDto.getAddress().getCity());
        address.setZipCode(customerRequestDto.getAddress().getZipCode());
        //address = addressRepository.save(address);
        customer.setAdress(address);

        return customer;
    }

    public Customer getCustomerById(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.get();
        //Address address = customer.getAdress();
        return customer;
    }

/*    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerResponseDto create(CustomerRequestDto dto){
        Customer customer = new Customer();
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhone(dto.phone());

        return getClienteDto(repository.save(customer));
    }

    public CustomerResponseDto getById(Long id) {
        return getClienteDto(repository.findById(id).get());
    }

    public List<CustomerResponseDto> obtenerClientes() {
        List<CustomerResponseDto> responseList = new ArrayList<>();

        for (Customer c : repository.findAll()) {
            CustomerResponseDto dto = getClienteDto(c);
            responseList.add(dto);
        }

        return responseList;
    }

    private CustomerResponseDto getClienteDto(Customer cliente){
        return new CustomerResponseDto(
                cliente.getId(),
                cliente.getFirstName(),
                cliente.getLastName(),
                cliente.getEmail(),
                cliente.getPhone()
        );
    }

 */
}
