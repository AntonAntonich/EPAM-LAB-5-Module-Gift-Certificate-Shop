package com.epam.esm.web.controller;

import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.domain.model.entity.dto.OrderDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.service.OrderService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private OrderService service;

    public ContractController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/user/idGift/{idGift}/idTag/{idTag}/idUser/{idUser}")
    public ResponseEntity<OrderDto> createNewOrder(@PathVariable int idGift,
                                                   @PathVariable int idTag,
                                                   @PathVariable int idUser) throws CustomServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(service.createNewOrder(idGift, idTag, idUser));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByUserId(@PathVariable int id) throws CustomServiceException {
        List<OrderDto> orderDtoList = service.getOrderByUserId(id);
        for(OrderDto orderDto : orderDtoList) {
            setLink(orderDto);
            Link link = linkTo(ContractController.class).slash(orderDto.getId()).withSelfRel();
            orderDto.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderDtoList);
    }

    @GetMapping("/admin/user-contracts/cost-time/{id}")
    public ResponseEntity<List<OrderDto>> getUserOrderCostTime(@PathVariable int id) throws CustomServiceException {
        List<OrderDto> orderDtoList = service.getOrderCostTimeByUserId(id);
        for(OrderDto orderDto : orderDtoList) {
            Link linkGift = linkTo(GiftController.class).slash(orderDto.getGift().getId()).withSelfRel();
            orderDto.getGift().add(linkGift);
            Link link = linkTo(ContractController.class).slash(orderDto.getId()).withSelfRel();
            orderDto.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderDtoList);
    }


    @GetMapping("/user/widely-used-tag")
    public ResponseEntity<CustomTagWithoutListDto> getWidelyUsedTagWithHighestOrderCost() throws CustomServiceException {
        CustomTagWithoutListDto tag = service.getWidelyUsedTagWithHighestOrderCost();
        Link link = linkTo(TagController.class).slash(tag.getId()).withSelfRel();
        tag.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }


    private void setLink(OrderDto orderDto) {
        Link linkUser = linkTo(UserController.class).slash(orderDto.getUser().getId()).withSelfRel();
        orderDto.getUser().add(linkUser);
        Link linkTag = linkTo(TagController.class).slash(orderDto.getTag().getId()).withSelfRel();
        orderDto.getTag().add(linkTag);
        Link linkGift = linkTo(GiftController.class).slash(orderDto.getGift().getId()).withSelfRel();
        orderDto.getGift().add(linkGift);
    }
}
