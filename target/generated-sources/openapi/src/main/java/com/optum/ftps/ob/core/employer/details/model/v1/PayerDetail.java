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
 * PayerDetail
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class PayerDetail {

  private String branchNumber;

  private String payerName;

  public PayerDetail branchNumber(String branchNumber) {
    this.branchNumber = branchNumber;
    return this;
  }

  /**
   * Get branchNumber
   * @return branchNumber
  */
  
  @Schema(name = "branchNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("branchNumber")
  public String getBranchNumber() {
    return branchNumber;
  }

  public void setBranchNumber(String branchNumber) {
    this.branchNumber = branchNumber;
  }

  public PayerDetail payerName(String payerName) {
    this.payerName = payerName;
    return this;
  }

  /**
   * Get payerName
   * @return payerName
  */
  
  @Schema(name = "payerName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("payerName")
  public String getPayerName() {
    return payerName;
  }

  public void setPayerName(String payerName) {
    this.payerName = payerName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayerDetail payerDetail = (PayerDetail) o;
    return Objects.equals(this.branchNumber, payerDetail.branchNumber) &&
        Objects.equals(this.payerName, payerDetail.payerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(branchNumber, payerName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayerDetail {\n");
    sb.append("    branchNumber: ").append(toIndentedString(branchNumber)).append("\n");
    sb.append("    payerName: ").append(toIndentedString(payerName)).append("\n");
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

