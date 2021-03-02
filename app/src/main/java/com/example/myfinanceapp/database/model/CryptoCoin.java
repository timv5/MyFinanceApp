package com.example.myfinanceapp.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(tableName = "crypto_coin")
public class CryptoCoin {

    @ColumnInfo(name = "crypto_coin_id")
    @PrimaryKey(autoGenerate = true)
    private long cryptoCoinId;

    @ColumnInfo(name = "external_id")
    private String externalId;

    @ColumnInfo(name = "symbol")
    private String symbol;

    @ColumnInfo(name = "current_price")
    private long currentPrice;

    @ColumnInfo(name = "high_24h")
    private long high24;

    @ColumnInfo(name = "low_24h")
    private long low24;

    @ColumnInfo(name = "price_change_24h")
    private long priceChange;

    @ColumnInfo(name = "price_change_percentage_24h")
    private long priceChangePercentage24;

}
