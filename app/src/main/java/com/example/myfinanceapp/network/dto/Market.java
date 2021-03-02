package com.example.myfinanceapp.network.dto;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Market {

    private String id;
    private String symbol;

    @SerializedName("current_price")
    private BigDecimal currentPrice;

    @SerializedName("high_24h")
    private BigDecimal high24;

    @SerializedName("low_24h")
    private BigDecimal low24;

    @SerializedName("price_change_24h")
    private BigDecimal priceChange;

    @SerializedName("price_change_percentage_24h")
    private BigDecimal priceChangePercentage24;

}