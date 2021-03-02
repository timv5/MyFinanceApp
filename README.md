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

## Showcase
![stocks](https://user-images.githubusercontent.com/17574739/109728160-f5ea4480-7bb5-11eb-9b2a-4ebda2185f1e.png)
![crypto](https://user-images.githubusercontent.com/17574739/109728186-03073380-7bb6-11eb-85ee-31c226ce8e16.png)
![crypto_arcive](https://user-images.githubusercontent.com/17574739/109728210-0bf80500-7bb6-11eb-8558-1dfa727d320b.png)

