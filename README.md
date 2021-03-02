# MyFinanceApp
Simple Android application for stock and crypto market.

## Prerequences and Features
- emulator
- java, gradle
- created account on: https://rapidapi.com/
- Stock list:
    - stocks data obtained with: com.yahoofinance-api:YahooFinanceAPI
    - by default includes (can be changed in Constants.java): TSLA, AAPL, AMZN, MSFT, MTCH, UPWK, FVRR, PINS, DIS, CRM
- Crypto market list:
    - crypto market data obtained from: https://rapidapi.com/ using coingecko.p.rapidapi.com
    - create account on https://rapidapi.com/ and set your Constatns.CRYPTO_HEADER_KEY
- Archive crypto market coin snapshots:
    - save crypto coins snapshots by swiping right on crypto market list

## Technologies
- java
- retrofit
- room
