package sia.tacocloud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum View {
    HOME("home"),
    DESIGN("design"),
    ORDER_FORM("orderForm"),
    REGISTRATION_FORM("registration"),
    ORDER("orders"),
    CURRENT("current"),
    LOGIN("login"),
    REDIRECT("redirect:/");

    @Getter
    private final String value;
}
