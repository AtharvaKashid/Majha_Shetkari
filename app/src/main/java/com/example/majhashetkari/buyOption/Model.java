package com.example.majhashetkari.buyOption;

public class Model {
    private String name;
    private String upiID;
    private String amt;
    private String msg;
    private String trnId;
    private String refId;

    public Model(String name,String upiId,String amt,String msg,String trnId,String refId) {
        this.name = name;
        this.upiID = upiId;
        this.amt = amt;
        this.msg=msg;
        this.trnId = trnId;
        this.refId = refId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpiID() {
        return upiID;
    }

    public void setUpiID(String upiID) {
        this.upiID = upiID;
    }

    public String getAmount() {
        return amt;
    }

    public void setAmount(String amount) {
        this.amt = amount;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
    public String getTrnId() {
        return trnId;
    }

    public void setTrnId(String trnId) {
        this.trnId = trnId;
    }


    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }



}
