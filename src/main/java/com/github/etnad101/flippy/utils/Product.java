package com.github.etnad101.flippy.utils;

import java.util.List;

public class Product {
    public String product_id;
    public List<Summary> sell_summary;
    public List<Summary> buy_summary;
    public QuickStatus quick_status;

    public float calculateProfit(float amount) {
        float unit_profit = quick_status.buyPrice - quick_status.sellPrice;
        return unit_profit * amount;
    }
}
