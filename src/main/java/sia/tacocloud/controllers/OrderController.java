package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.enums.View;
import sia.tacocloud.objects.Order;
import sia.tacocloud.repositories.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return View.ORDER_FORM.getValue();
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {

        if(errors.hasErrors())
            return View.ORDER_FORM.getValue();
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order Saved " + order);
        return View.REDIRECT.getValue();
    }

}
