package com.startuptank.wbteam.constant;

public enum InvestmentType {
    EQUITY("Equity"),
    DEBT("Debt"),
    DONATION("Donation"),
    NFT("NFT");

    private String type;

    InvestmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    }

