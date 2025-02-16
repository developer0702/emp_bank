package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.InvestmentPlans;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InvestmentDetail
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class InvestmentDetail {

  private String autoSweepDesc;

  private String minTransferAmount;

  private InvestmentPlans investmentPlans;

  public InvestmentDetail autoSweepDesc(String autoSweepDesc) {
    this.autoSweepDesc = autoSweepDesc;
    return this;
  }

  /**
   * Get autoSweepDesc
   * @return autoSweepDesc
  */
  
  @Schema(name = "autoSweepDesc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("autoSweepDesc")
  public String getAutoSweepDesc() {
    return autoSweepDesc;
  }

  public void setAutoSweepDesc(String autoSweepDesc) {
    this.autoSweepDesc = autoSweepDesc;
  }

  public InvestmentDetail minTransferAmount(String minTransferAmount) {
    this.minTransferAmount = minTransferAmount;
    return this;
  }

  /**
   * Get minTransferAmount
   * @return minTransferAmount
  */
  
  @Schema(name = "minTransferAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minTransferAmount")
  public String getMinTransferAmount() {
    return minTransferAmount;
  }

  public void setMinTransferAmount(String minTransferAmount) {
    this.minTransferAmount = minTransferAmount;
  }

  public InvestmentDetail investmentPlans(InvestmentPlans investmentPlans) {
    this.investmentPlans = investmentPlans;
    return this;
  }

  /**
   * Get investmentPlans
   * @return investmentPlans
  */
  @Valid 
  @Schema(name = "investmentPlans", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("investmentPlans")
  public InvestmentPlans getInvestmentPlans() {
    return investmentPlans;
  }

  public void setInvestmentPlans(InvestmentPlans investmentPlans) {
    this.investmentPlans = investmentPlans;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InvestmentDetail investmentDetail = (InvestmentDetail) o;
    return Objects.equals(this.autoSweepDesc, investmentDetail.autoSweepDesc) &&
        Objects.equals(this.minTransferAmount, investmentDetail.minTransferAmount) &&
        Objects.equals(this.investmentPlans, investmentDetail.investmentPlans);
  }

  @Override
  public int hashCode() {
    return Objects.hash(autoSweepDesc, minTransferAmount, investmentPlans);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InvestmentDetail {\n");
    sb.append("    autoSweepDesc: ").append(toIndentedString(autoSweepDesc)).append("\n");
    sb.append("    minTransferAmount: ").append(toIndentedString(minTransferAmount)).append("\n");
    sb.append("    investmentPlans: ").append(toIndentedString(investmentPlans)).append("\n");
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

