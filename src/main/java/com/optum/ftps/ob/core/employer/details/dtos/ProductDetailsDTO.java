package com.optum.ftps.ob.core.employer.details.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsDTO {

    private String enrollmentMethod;

    private String welcomeKitProcessDesc;

    private String debitCardOrderDesc;

    private String checkOrderDesc;

    private String statementDeliveryMethod;

    private String lineOfCreditParticipation;

    private SignatureMethodDTO signatureMethod;

    private List<EmployerProductSetupFeeDTO> employerProductSetupFee;

    private EmployerProductBrandDTO employerProductBrand;

    private InvestmentDetailDTO investmentDetail;

    private List<ProductOfferDTO> productOffer;
}
