package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.EmployerProductSetupFeeCode;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * EmployerProductSetupFee
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class EmployerProductSetupFee {

  private EmployerProductSetupFeeCode employerProductSetupFeeCode;

  private String employerProductSetupFeeAmount;

  public EmployerProductSetupFee employerProductSetupFeeCode(EmployerProductSetupFeeCode employerProductSetupFeeCode) {
    this.employerProductSetupFeeCode = employerProductSetupFeeCode;
    return this;
  }

  /**
   * Get employerProductSetupFeeCode
   * @return employerProductSetupFeeCode
  */
  @Valid 
  @Schema(name = "employerProductSetupFeeCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerProductSetupFeeCode")
  public EmployerProductSetupFeeCode getEmployerProductSetupFeeCode() {
    return employerProductSetupFeeCode;
  }

  public void setEmployerProductSetupFeeCode(EmployerProductSetupFeeCode employerProductSetupFeeCode) {
    this.employerProductSetupFeeCode = employerProductSetupFeeCode;
  }

  public EmployerProductSetupFee employerProductSetupFeeAmount(String employerProductSetupFeeAmount) {
    this.employerProductSetupFeeAmount = employerProductSetupFeeAmount;
    return this;
  }

  /**
   * Get employerProductSetupFeeAmount
   * @return employerProductSetupFeeAmount
  */
  
  @Schema(name = "employerProductSetupFeeAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerProductSetupFeeAmount")
  public String getEmployerProductSetupFeeAmount() {
    return employerProductSetupFeeAmount;
  }

  public void setEmployerProductSetupFeeAmount(String employerProductSetupFeeAmount) {
    this.employerProductSetupFeeAmount = employerProductSetupFeeAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployerProductSetupFee employerProductSetupFee = (EmployerProductSetupFee) o;
    return Objects.equals(this.employerProductSetupFeeCode, employerProductSetupFee.employerProductSetupFeeCode) &&
        Objects.equals(this.employerProductSetupFeeAmount, employerProductSetupFee.employerProductSetupFeeAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employerProductSetupFeeCode, employerProductSetupFeeAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployerProductSetupFee {\n");
    sb.append("    employerProductSetupFeeCode: ").append(toIndentedString(employerProductSetupFeeCode)).append("\n");
    sb.append("    employerProductSetupFeeAmount: ").append(toIndentedString(employerProductSetupFeeAmount)).append("\n");
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

