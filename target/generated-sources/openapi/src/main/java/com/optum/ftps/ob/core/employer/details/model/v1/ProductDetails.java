package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.EmployerProductBrand;
import com.optum.ftps.ob.core.employer.details.model.v1.EmployerProductSetupFee;
import com.optum.ftps.ob.core.employer.details.model.v1.InvestmentDetail;
import com.optum.ftps.ob.core.employer.details.model.v1.ProductOffer;
import com.optum.ftps.ob.core.employer.details.model.v1.SignatureMethod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProductDetails
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class ProductDetails {

  private String enrollmentMethod;

  private String welcomeKitProcessDesc;

  private String debitCardOrderDesc;

  private String checkOrderDesc;

  private String statementDeliveryMethod;

  private String lineOfCreditParticipation;

  private SignatureMethod signatureMethod;

  @Valid
  private List<@Valid EmployerProductSetupFee> employerProductSetupFee;

  private EmployerProductBrand employerProductBrand;

  private InvestmentDetail investmentDetail;

  @Valid
  private List<@Valid ProductOffer> productOffer;

  public ProductDetails enrollmentMethod(String enrollmentMethod) {
    this.enrollmentMethod = enrollmentMethod;
    return this;
  }

  /**
   * Get enrollmentMethod
   * @return enrollmentMethod
  */
  
  @Schema(name = "enrollmentMethod", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("enrollmentMethod")
  public String getEnrollmentMethod() {
    return enrollmentMethod;
  }

  public void setEnrollmentMethod(String enrollmentMethod) {
    this.enrollmentMethod = enrollmentMethod;
  }

  public ProductDetails welcomeKitProcessDesc(String welcomeKitProcessDesc) {
    this.welcomeKitProcessDesc = welcomeKitProcessDesc;
    return this;
  }

  /**
   * Get welcomeKitProcessDesc
   * @return welcomeKitProcessDesc
  */
  
  @Schema(name = "welcomeKitProcessDesc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("welcomeKitProcessDesc")
  public String getWelcomeKitProcessDesc() {
    return welcomeKitProcessDesc;
  }

  public void setWelcomeKitProcessDesc(String welcomeKitProcessDesc) {
    this.welcomeKitProcessDesc = welcomeKitProcessDesc;
  }

  public ProductDetails debitCardOrderDesc(String debitCardOrderDesc) {
    this.debitCardOrderDesc = debitCardOrderDesc;
    return this;
  }

  /**
   * Get debitCardOrderDesc
   * @return debitCardOrderDesc
  */
  
  @Schema(name = "debitCardOrderDesc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("debitCardOrderDesc")
  public String getDebitCardOrderDesc() {
    return debitCardOrderDesc;
  }

  public void setDebitCardOrderDesc(String debitCardOrderDesc) {
    this.debitCardOrderDesc = debitCardOrderDesc;
  }

  public ProductDetails checkOrderDesc(String checkOrderDesc) {
    this.checkOrderDesc = checkOrderDesc;
    return this;
  }

  /**
   * Get checkOrderDesc
   * @return checkOrderDesc
  */
  
  @Schema(name = "checkOrderDesc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("checkOrderDesc")
  public String getCheckOrderDesc() {
    return checkOrderDesc;
  }

  public void setCheckOrderDesc(String checkOrderDesc) {
    this.checkOrderDesc = checkOrderDesc;
  }

  public ProductDetails statementDeliveryMethod(String statementDeliveryMethod) {
    this.statementDeliveryMethod = statementDeliveryMethod;
    return this;
  }

  /**
   * Get statementDeliveryMethod
   * @return statementDeliveryMethod
  */
  
  @Schema(name = "statementDeliveryMethod", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("statementDeliveryMethod")
  public String getStatementDeliveryMethod() {
    return statementDeliveryMethod;
  }

  public void setStatementDeliveryMethod(String statementDeliveryMethod) {
    this.statementDeliveryMethod = statementDeliveryMethod;
  }

  public ProductDetails lineOfCreditParticipation(String lineOfCreditParticipation) {
    this.lineOfCreditParticipation = lineOfCreditParticipation;
    return this;
  }

  /**
   * Get lineOfCreditParticipation
   * @return lineOfCreditParticipation
  */
  
  @Schema(name = "lineOfCreditParticipation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lineOfCreditParticipation")
  public String getLineOfCreditParticipation() {
    return lineOfCreditParticipation;
  }

  public void setLineOfCreditParticipation(String lineOfCreditParticipation) {
    this.lineOfCreditParticipation = lineOfCreditParticipation;
  }

  public ProductDetails signatureMethod(SignatureMethod signatureMethod) {
    this.signatureMethod = signatureMethod;
    return this;
  }

  /**
   * Get signatureMethod
   * @return signatureMethod
  */
  @Valid 
  @Schema(name = "signatureMethod", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("signatureMethod")
  public SignatureMethod getSignatureMethod() {
    return signatureMethod;
  }

  public void setSignatureMethod(SignatureMethod signatureMethod) {
    this.signatureMethod = signatureMethod;
  }

  public ProductDetails employerProductSetupFee(List<@Valid EmployerProductSetupFee> employerProductSetupFee) {
    this.employerProductSetupFee = employerProductSetupFee;
    return this;
  }

  public ProductDetails addEmployerProductSetupFeeItem(EmployerProductSetupFee employerProductSetupFeeItem) {
    if (this.employerProductSetupFee == null) {
      this.employerProductSetupFee = new ArrayList<>();
    }
    this.employerProductSetupFee.add(employerProductSetupFeeItem);
    return this;
  }

  /**
   * Get employerProductSetupFee
   * @return employerProductSetupFee
  */
  @Valid 
  @Schema(name = "employerProductSetupFee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerProductSetupFee")
  public List<@Valid EmployerProductSetupFee> getEmployerProductSetupFee() {
    return employerProductSetupFee;
  }

  public void setEmployerProductSetupFee(List<@Valid EmployerProductSetupFee> employerProductSetupFee) {
    this.employerProductSetupFee = employerProductSetupFee;
  }

  public ProductDetails employerProductBrand(EmployerProductBrand employerProductBrand) {
    this.employerProductBrand = employerProductBrand;
    return this;
  }

  /**
   * Get employerProductBrand
   * @return employerProductBrand
  */
  @Valid 
  @Schema(name = "employerProductBrand", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerProductBrand")
  public EmployerProductBrand getEmployerProductBrand() {
    return employerProductBrand;
  }

  public void setEmployerProductBrand(EmployerProductBrand employerProductBrand) {
    this.employerProductBrand = employerProductBrand;
  }

  public ProductDetails investmentDetail(InvestmentDetail investmentDetail) {
    this.investmentDetail = investmentDetail;
    return this;
  }

  /**
   * Get investmentDetail
   * @return investmentDetail
  */
  @Valid 
  @Schema(name = "investmentDetail", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("investmentDetail")
  public InvestmentDetail getInvestmentDetail() {
    return investmentDetail;
  }

  public void setInvestmentDetail(InvestmentDetail investmentDetail) {
    this.investmentDetail = investmentDetail;
  }

  public ProductDetails productOffer(List<@Valid ProductOffer> productOffer) {
    this.productOffer = productOffer;
    return this;
  }

  public ProductDetails addProductOfferItem(ProductOffer productOfferItem) {
    if (this.productOffer == null) {
      this.productOffer = new ArrayList<>();
    }
    this.productOffer.add(productOfferItem);
    return this;
  }

  /**
   * Get productOffer
   * @return productOffer
  */
  @Valid 
  @Schema(name = "productOffer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productOffer")
  public List<@Valid ProductOffer> getProductOffer() {
    return productOffer;
  }

  public void setProductOffer(List<@Valid ProductOffer> productOffer) {
    this.productOffer = productOffer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDetails productDetails = (ProductDetails) o;
    return Objects.equals(this.enrollmentMethod, productDetails.enrollmentMethod) &&
        Objects.equals(this.welcomeKitProcessDesc, productDetails.welcomeKitProcessDesc) &&
        Objects.equals(this.debitCardOrderDesc, productDetails.debitCardOrderDesc) &&
        Objects.equals(this.checkOrderDesc, productDetails.checkOrderDesc) &&
        Objects.equals(this.statementDeliveryMethod, productDetails.statementDeliveryMethod) &&
        Objects.equals(this.lineOfCreditParticipation, productDetails.lineOfCreditParticipation) &&
        Objects.equals(this.signatureMethod, productDetails.signatureMethod) &&
        Objects.equals(this.employerProductSetupFee, productDetails.employerProductSetupFee) &&
        Objects.equals(this.employerProductBrand, productDetails.employerProductBrand) &&
        Objects.equals(this.investmentDetail, productDetails.investmentDetail) &&
        Objects.equals(this.productOffer, productDetails.productOffer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enrollmentMethod, welcomeKitProcessDesc, debitCardOrderDesc, checkOrderDesc, statementDeliveryMethod, lineOfCreditParticipation, signatureMethod, employerProductSetupFee, employerProductBrand, investmentDetail, productOffer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDetails {\n");
    sb.append("    enrollmentMethod: ").append(toIndentedString(enrollmentMethod)).append("\n");
    sb.append("    welcomeKitProcessDesc: ").append(toIndentedString(welcomeKitProcessDesc)).append("\n");
    sb.append("    debitCardOrderDesc: ").append(toIndentedString(debitCardOrderDesc)).append("\n");
    sb.append("    checkOrderDesc: ").append(toIndentedString(checkOrderDesc)).append("\n");
    sb.append("    statementDeliveryMethod: ").append(toIndentedString(statementDeliveryMethod)).append("\n");
    sb.append("    lineOfCreditParticipation: ").append(toIndentedString(lineOfCreditParticipation)).append("\n");
    sb.append("    signatureMethod: ").append(toIndentedString(signatureMethod)).append("\n");
    sb.append("    employerProductSetupFee: ").append(toIndentedString(employerProductSetupFee)).append("\n");
    sb.append("    employerProductBrand: ").append(toIndentedString(employerProductBrand)).append("\n");
    sb.append("    investmentDetail: ").append(toIndentedString(investmentDetail)).append("\n");
    sb.append("    productOffer: ").append(toIndentedString(productOffer)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

