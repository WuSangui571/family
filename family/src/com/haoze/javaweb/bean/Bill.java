package com.haoze.javaweb.bean;


import java.sql.Date;
import java.util.Objects;

/**
 * @author 寿豪泽
 */
public class Bill {
    private int billID;
   private Date billDate;
   private String consumptionType;
   private float consumptionAmount;
   private String consumer;

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Bill(int billID, Date billDate, String consumptionType, float consumptionAmount, String consumer) {
        this.billID = billID;
        this.billDate = billDate;
        this.consumptionType = consumptionType;
        this.consumptionAmount = consumptionAmount;
        this.consumer = consumer;
    }

    public Bill() {
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", billDate=" + billDate +
                ", consumptionType='" + consumptionType + '\'' +
                ", consumptionAmount=" + consumptionAmount +
                ", consumer=" + consumer +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bill bill = (Bill) o;
        return billID == bill.billID && Float.compare(bill.consumptionAmount, consumptionAmount) == 0 && Objects.equals(billDate, bill.billDate) && Objects.equals(consumptionType, bill.consumptionType) && Objects.equals(consumer, bill.consumer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billID, billDate, consumptionType, consumptionAmount, consumer);
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(String consumptionType) {
        this.consumptionType = consumptionType;
    }

    public float getConsumptionAmount() {
        return consumptionAmount;
    }

    public void setConsumptionAmount(float consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }
}
