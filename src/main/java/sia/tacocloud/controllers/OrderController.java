package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.configurations.OrderProperties;
import sia.tacocloud.enums.View;
import sia.tacocloud.objects.Order;
import sia.tacocloud.objects.User;
import sia.tacocloud.repositories.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProperties orderProperties;

    public OrderController(OrderRepository orderRepository, OrderProperties orderProperties) {
        this.orderRepository = orderRepository;
        this.orderProperties = orderProperties;
    }

    @GetMapping
    public String orderForUser(@AuthenticationPrincipal User user, Model model){
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return View.ORDER_FORM.getValue();
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

        if(errors.hasErrors())
            return View.ORDER_FORM.getValue();
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order Saved " + order);
        return View.REDIRECT.getValue();
    }

}
