package com.example.orders.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orders.mapper.OrderMapper;
import com.example.orders.model.Item;
import com.example.orders.model.Order;
import com.example.orders.service.OrderService;
import com.example.webpos.rest.api.CartsApi;
import com.example.webpos.rest.api.OrdersApi;
import com.example.webpos.rest.dto.CartDto;
import com.example.webpos.rest.dto.ItemDto;
import com.example.webpos.rest.dto.OrderDto;
import com.example.webpos.rest.dto.ProductDto;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api")
public class OrderRestController implements OrdersApi{

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    public OrderRestController(OrderService orderService,OrderMapper orderMapper){
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<OrderDto> generateOrder(List<ItemDto> itemDto){
        Order order = orderService.newOrder();
        List<Item> items = orderMapper.toItems(itemDto, order.getId());
        order.setItems(items);
        orderService.saveOrder(order);
        OrderDto orderDto = orderMapper.toOrderDto(order);
        return new ResponseEntity<>(orderDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> listOrders(){
        List<OrderDto> ordersDto = new ArrayList<>(orderMapper.toOrdersDto(this.orderService.orders()));
        if(ordersDto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDto> showOrderById(Integer orderId){
        OrderDto order = this.orderMapper.toOrderDto(this.orderService.findOrderById(orderId));
        if(order==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

}
