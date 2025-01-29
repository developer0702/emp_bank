package com.optum.ftps.ob.core.employerDetails.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductOfferDTO {
    private String productCategoryDesc;

    private String offerCode;

    private String offerName;

    private BigDecimal investmentThresholdAmt;

    private String investmentAllowedDesc;

    private BigDecimal investmentFeeAmt;

    private String bankProductNumber;
}
