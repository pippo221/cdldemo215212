package com.example.cuongducnguyenkp.cdldemo2;

/**
 * Created by cuongducnguyen.kp on 10/26/2017.
 */

public class Customer {

    private String id;
    private String name;
    private String serviceType;
    private String contractDurationStart;
    private String contractDurationEnd;
    private String status;
    private String payment;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getContractDurationStart() {
        return contractDurationStart;
    }

    public String getContractDurationEnd() {
        return contractDurationEnd;
    }

    public String getStatus() {
        return status;
    }

    public String getPayment() {
        return payment;
    }

    public Customer(String id, String name, String serviceType
            , String contractDurationStart, String contractDurationEnd
            , String status, String payment
    ) {
        this.id = id;
        this.name = name;
        this.serviceType = serviceType;
        this.contractDurationStart = contractDurationStart;
        this.contractDurationEnd = contractDurationEnd;
        this.status = status;
        this.payment = payment;
    }


}
