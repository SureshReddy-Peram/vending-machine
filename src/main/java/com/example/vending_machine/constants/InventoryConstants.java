package com.example.vending_machine.constants;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InventoryConstants {
    public enum InventoryNames{
        COFFEE("coffee"),
        MILK("milk"),
        SUGAR("sugar"),
        WATER("water"),
        TEA("tea");

        private String value;

        InventoryNames(String value){ this.value = value; }

    }
}
