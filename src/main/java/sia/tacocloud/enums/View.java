package sia.tacocloud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum View {
    HOME("home"),
    DESIGN("design"),
    ORDER_FORM("orderForm"),
    ORDER("orders"),
    CURRENT("current"),
    REDIRECT("redirect:/");

    @Getter
    private final String value;
}
