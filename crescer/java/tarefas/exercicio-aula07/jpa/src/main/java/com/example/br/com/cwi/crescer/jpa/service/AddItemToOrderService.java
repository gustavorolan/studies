package com.example.br.com.cwi.crescer.jpa.service;

import com.example.br.com.cwi.crescer.jpa.controller.request.ItemRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.ItemResponse;
import com.example.br.com.cwi.crescer.jpa.mapper.AddItemToOrderMapper;
import com.example.br.com.cwi.crescer.jpa.model.Item;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import com.example.br.com.cwi.crescer.jpa.model.Product;
import com.example.br.com.cwi.crescer.jpa.repository.ItemRepository;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;
import com.example.br.com.cwi.crescer.jpa.repository.ProductRepository;
import com.example.br.com.cwi.crescer.jpa.validator.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;

@Service
public class AddItemToOrderService {
    @Autowired
    private AddItemToOrderMapper addItemToOrderMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderFromUserRepository orderFromUserRepository;
    @Autowired
    private Validators validators;
    @Autowired
    private OrderFromUserFinderService orderFromUserFinderService;

    public ItemResponse add(ItemRequest request, Long id) {
        Item item = new Item();
        item.setQuantity(request.getQuantity());


        Product product = productRepository.getById(request.getProdcutId());
        item.setProduct(product);

        OrderFromUser order = orderFromUserFinderService.findByIdWithException(id);
        order.getItemList().add(item);

        validators.IfOrderIsOpenThrowsAnException(order);

        item.setUser(order);

        orderFromUserRepository.save(order);
        itemRepository.save(item);

        return addItemToOrderMapper.toResponse(item);
    }
}
