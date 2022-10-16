package com.example.br.com.cwi.crescer.jpa.controller;

import com.example.br.com.cwi.crescer.jpa.controller.request.CreditCardRequest;
import com.example.br.com.cwi.crescer.jpa.controller.request.ItemRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.*;
import com.example.br.com.cwi.crescer.jpa.controller.request.AddNewOrderRequest;
import com.example.br.com.cwi.crescer.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/store")
public class EcommerceControler {
    @Autowired
    private AddNewOrderService addNewOrderService;
    @Autowired
    private AddNewCreditCardService addNewCreditCardService;
    @Autowired
    private AddItemToOrderService addItemToOrderService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderCloseService orderCloseService;
    @Autowired
    private AllOrdersService allOrdersService;
    @Autowired
    private ChangeCreditCardService changeCreditCardService;

    @GetMapping
    public List<OrderResponse> allOrders () {
        return allOrdersService.get();
    }

    @GetMapping("/{id}")
    public OrderResponse add (@PathVariable Long id) {
        return orderService.get(id);
    }

    @PutMapping("/{id}/finalizar")
    public OrderResponse close (@PathVariable Long id) {
        return orderCloseService.close(id);
    }

    @PostMapping
    public AddNewOrderResponse add (@Valid @RequestBody AddNewOrderRequest request) {
        return addNewOrderService.add(request);
    }
    @PostMapping("/{id}/creditCard")
    public CreditCardResponse add (@Valid @RequestBody CreditCardRequest request, @PathVariable Long id) {
        return addNewCreditCardService.add(request,id);
    }

    @PutMapping("/{id}/creditCard")
    public CreditCardResponse change (@Valid @RequestBody CreditCardRequest request, @PathVariable Long id) {
        return changeCreditCardService.change(request,id);
    }

    @PostMapping("{id}/item")
    public ItemResponse add (@Valid @RequestBody ItemRequest request, @PathVariable Long id) {
        return addItemToOrderService.add(request,id);
    }
}
