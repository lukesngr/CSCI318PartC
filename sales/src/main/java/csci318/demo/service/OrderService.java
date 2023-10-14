package csci318.demo.service;

import csci318.demo.controller.dto.OrderDTO;
import csci318.demo.model.Orders;
import csci318.demo.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Orders order = new Orders();
        BeanUtils.copyProperties(orderDTO, order);
        Orders savedOrder = orderRepository.save(order);

        OrderDTO responseDTO = new OrderDTO();
        BeanUtils.copyProperties(savedOrder, responseDTO);

        return responseDTO;
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> {
                    OrderDTO dto = new OrderDTO();
                    BeanUtils.copyProperties(order, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        Optional<Orders> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            OrderDTO responseDTO = new OrderDTO();
            BeanUtils.copyProperties(orderOptional.get(), responseDTO);
            return responseDTO;
        } else {
            return null; // You can return null or throw a custom exception here
        }
    }

    public void deleteCustomer(Long id) {
        orderRepository.deleteById(id);
    }
}
