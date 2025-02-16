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
 * BankAccountIdentifier
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class BankAccountIdentifier {

  private String bankAccountNumber;

  private String bankRoutingNumber;

  public BankAccountIdentifier bankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
    return this;
  }

  /**
   * Get bankAccountNumber
   * @return bankAccountNumber
  */
  
  @Schema(name = "bankAccountNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankAccountNumber")
  public String getBankAccountNumber() {
    return bankAccountNumber;
  }

  public void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  public BankAccountIdentifier bankRoutingNumber(String bankRoutingNumber) {
    this.bankRoutingNumber = bankRoutingNumber;
    return this;
  }

  /**
   * Get bankRoutingNumber
   * @return bankRoutingNumber
  */
  
  @Schema(name = "bankRoutingNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankRoutingNumber")
  public String getBankRoutingNumber() {
    return bankRoutingNumber;
  }

  public void setBankRoutingNumber(String bankRoutingNumber) {
    this.bankRoutingNumber = bankRoutingNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccountIdentifier bankAccountIdentifier = (BankAccountIdentifier) o;
    return Objects.equals(this.bankAccountNumber, bankAccountIdentifier.bankAccountNumber) &&
        Objects.equals(this.bankRoutingNumber, bankAccountIdentifier.bankRoutingNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankAccountNumber, bankRoutingNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccountIdentifier {\n");
    sb.append("    bankAccountNumber: ").append(toIndentedString(bankAccountNumber)).append("\n");
    sb.append("    bankRoutingNumber: ").append(toIndentedString(bankRoutingNumber)).append("\n");
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

