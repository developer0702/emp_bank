package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InvestmentPlans
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class InvestmentPlans {

  private String primaryInvestmentPlanID;

  private String secondaryInvestmentPlanID;

  private String effectiveDate;

  public InvestmentPlans primaryInvestmentPlanID(String primaryInvestmentPlanID) {
    this.primaryInvestmentPlanID = primaryInvestmentPlanID;
    return this;
  }

  /**
   * Get primaryInvestmentPlanID
   * @return primaryInvestmentPlanID
  */
  
  @Schema(name = "primaryInvestmentPlanID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("primaryInvestmentPlanID")
  public String getPrimaryInvestmentPlanID() {
    return primaryInvestmentPlanID;
  }

  public void setPrimaryInvestmentPlanID(String primaryInvestmentPlanID) {
    this.primaryInvestmentPlanID = primaryInvestmentPlanID;
  }

  public InvestmentPlans secondaryInvestmentPlanID(String secondaryInvestmentPlanID) {
    this.secondaryInvestmentPlanID = secondaryInvestmentPlanID;
    return this;
  }

  /**
   * Get secondaryInvestmentPlanID
   * @return secondaryInvestmentPlanID
  */
  
  @Schema(name = "secondaryInvestmentPlanID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("secondaryInvestmentPlanID")
  public String getSecondaryInvestmentPlanID() {
    return secondaryInvestmentPlanID;
  }

  public void setSecondaryInvestmentPlanID(String secondaryInvestmentPlanID) {
    this.secondaryInvestmentPlanID = secondaryInvestmentPlanID;
  }

  public InvestmentPlans effectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
    return this;
  }

  /**
   * Get effectiveDate
   * @return effectiveDate
  */
  
  @Schema(name = "effectiveDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("effectiveDate")
  public String getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InvestmentPlans investmentPlans = (InvestmentPlans) o;
    return Objects.equals(this.primaryInvestmentPlanID, investmentPlans.primaryInvestmentPlanID) &&
        Objects.equals(this.secondaryInvestmentPlanID, investmentPlans.secondaryInvestmentPlanID) &&
        Objects.equals(this.effectiveDate, investmentPlans.effectiveDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(primaryInvestmentPlanID, secondaryInvestmentPlanID, effectiveDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InvestmentPlans {\n");
    sb.append("    primaryInvestmentPlanID: ").append(toIndentedString(primaryInvestmentPlanID)).append("\n");
    sb.append("    secondaryInvestmentPlanID: ").append(toIndentedString(secondaryInvestmentPlanID)).append("\n");
    sb.append("    effectiveDate: ").append(toIndentedString(effectiveDate)).append("\n");
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

