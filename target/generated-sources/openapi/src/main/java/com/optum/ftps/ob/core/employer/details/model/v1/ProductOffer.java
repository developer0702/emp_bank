package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProductOffer
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class ProductOffer {

  private String productCategoryDesc;

  private String offerCode;

  private String offerName;

  private BigDecimal investmentThresholdAmt = null;

  private String investmentAllowedDesc;

  private String investmentFeeAmt;

  private BigDecimal bankProductNumber = null;

  public ProductOffer productCategoryDesc(String productCategoryDesc) {
    this.productCategoryDesc = productCategoryDesc;
    return this;
  }

  /**
   * Get productCategoryDesc
   * @return productCategoryDesc
  */
  
  @Schema(name = "productCategoryDesc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productCategoryDesc")
  public String getProductCategoryDesc() {
    return productCategoryDesc;
  }

  public void setProductCategoryDesc(String productCategoryDesc) {
    this.productCategoryDesc = productCategoryDesc;
  }

  public ProductOffer offerCode(String offerCode) {
    this.offerCode = offerCode;
    return this;
  }

  /**
   * Get offerCode
   * @return offerCode
  */
  
  @Schema(name = "offerCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("offerCode")
  public String getOfferCode() {
    return offerCode;
  }

  public void setOfferCode(String offerCode) {
    this.offerCode = offerCode;
  }

  public ProductOffer offerName(String offerName) {
    this.offerName = offerName;
    return this;
  }

  /**
   * Get offerName
   * @return offerName
  */
  
  @Schema(name = "offerName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("offerName")
  public String getOfferName() {
    return offerName;
  }

  public void setOfferName(String offerName) {
    this.offerName = offerName;
  }

  public ProductOffer investmentThresholdAmt(BigDecimal investmentThresholdAmt) {
    this.investmentThresholdAmt = investmentThresholdAmt;
    return this;
  }

  /**
   * Get investmentThresholdAmt
   * @return investmentThresholdAmt
  */
  @Valid 
  @Schema(name = "investmentThresholdAmt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("investmentThresholdAmt")
  public BigDecimal getInvestmentThresholdAmt() {
    return investmentThresholdAmt;
  }

  public void setInvestmentThresholdAmt(BigDecimal investmentThresholdAmt) {
    this.investmentThresholdAmt = investmentThresholdAmt;
  }

  public ProductOffer investmentAllowedDesc(String investmentAllowedDesc) {
    this.investmentAllowedDesc = investmentAllowedDesc;
    return this;
  }

  /**
   * Get investmentAllowedDesc
   * @return investmentAllowedDesc
  */
  
  @Schema(name = "investmentAllowedDesc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("investmentAllowedDesc")
  public String getInvestmentAllowedDesc() {
    return investmentAllowedDesc;
  }

  public void setInvestmentAllowedDesc(String investmentAllowedDesc) {
    this.investmentAllowedDesc = investmentAllowedDesc;
  }

  public ProductOffer investmentFeeAmt(String investmentFeeAmt) {
    this.investmentFeeAmt = investmentFeeAmt;
    return this;
  }

  /**
   * Get investmentFeeAmt
   * @return investmentFeeAmt
  */
  
  @Schema(name = "investmentFeeAmt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("investmentFeeAmt")
  public String getInvestmentFeeAmt() {
    return investmentFeeAmt;
  }

  public void setInvestmentFeeAmt(String investmentFeeAmt) {
    this.investmentFeeAmt = investmentFeeAmt;
  }

  public ProductOffer bankProductNumber(BigDecimal bankProductNumber) {
    this.bankProductNumber = bankProductNumber;
    return this;
  }

  /**
   * Get bankProductNumber
   * @return bankProductNumber
  */
  @Valid 
  @Schema(name = "bankProductNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankProductNumber")
  public BigDecimal getBankProductNumber() {
    return bankProductNumber;
  }

  public void setBankProductNumber(BigDecimal bankProductNumber) {
    this.bankProductNumber = bankProductNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductOffer productOffer = (ProductOffer) o;
    return Objects.equals(this.productCategoryDesc, productOffer.productCategoryDesc) &&
        Objects.equals(this.offerCode, productOffer.offerCode) &&
        Objects.equals(this.offerName, productOffer.offerName) &&
        Objects.equals(this.investmentThresholdAmt, productOffer.investmentThresholdAmt) &&
        Objects.equals(this.investmentAllowedDesc, productOffer.investmentAllowedDesc) &&
        Objects.equals(this.investmentFeeAmt, productOffer.investmentFeeAmt) &&
        Objects.equals(this.bankProductNumber, productOffer.bankProductNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productCategoryDesc, offerCode, offerName, investmentThresholdAmt, investmentAllowedDesc, investmentFeeAmt, bankProductNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOffer {\n");
    sb.append("    productCategoryDesc: ").append(toIndentedString(productCategoryDesc)).append("\n");
    sb.append("    offerCode: ").append(toIndentedString(offerCode)).append("\n");
    sb.append("    offerName: ").append(toIndentedString(offerName)).append("\n");
    sb.append("    investmentThresholdAmt: ").append(toIndentedString(investmentThresholdAmt)).append("\n");
    sb.append("    investmentAllowedDesc: ").append(toIndentedString(investmentAllowedDesc)).append("\n");
    sb.append("    investmentFeeAmt: ").append(toIndentedString(investmentFeeAmt)).append("\n");
    sb.append("    bankProductNumber: ").append(toIndentedString(bankProductNumber)).append("\n");
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

