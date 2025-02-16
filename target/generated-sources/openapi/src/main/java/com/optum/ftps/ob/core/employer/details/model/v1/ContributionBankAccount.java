package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.BankAccountIdentifier;
import com.optum.ftps.ob.core.employer.details.model.v1.BankAccountStatus;
import com.optum.ftps.ob.core.employer.details.model.v1.BankAccountTypeCode;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ContributionBankAccount
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class ContributionBankAccount {

  private BankAccountIdentifier bankAccountIdentifier;

  private BankAccountTypeCode bankAccountTypeCode;

  private String bankName;

  private String bankSequenceNumber;

  private String bankAccountNickName;

  private BankAccountStatus bankAccountStatus;

  private String bankAccountOperation;

  public ContributionBankAccount bankAccountIdentifier(BankAccountIdentifier bankAccountIdentifier) {
    this.bankAccountIdentifier = bankAccountIdentifier;
    return this;
  }

  /**
   * Get bankAccountIdentifier
   * @return bankAccountIdentifier
  */
  @Valid 
  @Schema(name = "bankAccountIdentifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankAccountIdentifier")
  public BankAccountIdentifier getBankAccountIdentifier() {
    return bankAccountIdentifier;
  }

  public void setBankAccountIdentifier(BankAccountIdentifier bankAccountIdentifier) {
    this.bankAccountIdentifier = bankAccountIdentifier;
  }

  public ContributionBankAccount bankAccountTypeCode(BankAccountTypeCode bankAccountTypeCode) {
    this.bankAccountTypeCode = bankAccountTypeCode;
    return this;
  }

  /**
   * Get bankAccountTypeCode
   * @return bankAccountTypeCode
  */
  @Valid 
  @Schema(name = "bankAccountTypeCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankAccountTypeCode")
  public BankAccountTypeCode getBankAccountTypeCode() {
    return bankAccountTypeCode;
  }

  public void setBankAccountTypeCode(BankAccountTypeCode bankAccountTypeCode) {
    this.bankAccountTypeCode = bankAccountTypeCode;
  }

  public ContributionBankAccount bankName(String bankName) {
    this.bankName = bankName;
    return this;
  }

  /**
   * Get bankName
   * @return bankName
  */
  
  @Schema(name = "bankName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankName")
  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public ContributionBankAccount bankSequenceNumber(String bankSequenceNumber) {
    this.bankSequenceNumber = bankSequenceNumber;
    return this;
  }

  /**
   * Get bankSequenceNumber
   * @return bankSequenceNumber
  */
  
  @Schema(name = "bankSequenceNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankSequenceNumber")
  public String getBankSequenceNumber() {
    return bankSequenceNumber;
  }

  public void setBankSequenceNumber(String bankSequenceNumber) {
    this.bankSequenceNumber = bankSequenceNumber;
  }

  public ContributionBankAccount bankAccountNickName(String bankAccountNickName) {
    this.bankAccountNickName = bankAccountNickName;
    return this;
  }

  /**
   * Get bankAccountNickName
   * @return bankAccountNickName
  */
  
  @Schema(name = "bankAccountNickName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankAccountNickName")
  public String getBankAccountNickName() {
    return bankAccountNickName;
  }

  public void setBankAccountNickName(String bankAccountNickName) {
    this.bankAccountNickName = bankAccountNickName;
  }

  public ContributionBankAccount bankAccountStatus(BankAccountStatus bankAccountStatus) {
    this.bankAccountStatus = bankAccountStatus;
    return this;
  }

  /**
   * Get bankAccountStatus
   * @return bankAccountStatus
  */
  @Valid 
  @Schema(name = "bankAccountStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankAccountStatus")
  public BankAccountStatus getBankAccountStatus() {
    return bankAccountStatus;
  }

  public void setBankAccountStatus(BankAccountStatus bankAccountStatus) {
    this.bankAccountStatus = bankAccountStatus;
  }

  public ContributionBankAccount bankAccountOperation(String bankAccountOperation) {
    this.bankAccountOperation = bankAccountOperation;
    return this;
  }

  /**
   * Get bankAccountOperation
   * @return bankAccountOperation
  */
  
  @Schema(name = "bankAccountOperation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankAccountOperation")
  public String getBankAccountOperation() {
    return bankAccountOperation;
  }

  public void setBankAccountOperation(String bankAccountOperation) {
    this.bankAccountOperation = bankAccountOperation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContributionBankAccount contributionBankAccount = (ContributionBankAccount) o;
    return Objects.equals(this.bankAccountIdentifier, contributionBankAccount.bankAccountIdentifier) &&
        Objects.equals(this.bankAccountTypeCode, contributionBankAccount.bankAccountTypeCode) &&
        Objects.equals(this.bankName, contributionBankAccount.bankName) &&
        Objects.equals(this.bankSequenceNumber, contributionBankAccount.bankSequenceNumber) &&
        Objects.equals(this.bankAccountNickName, contributionBankAccount.bankAccountNickName) &&
        Objects.equals(this.bankAccountStatus, contributionBankAccount.bankAccountStatus) &&
        Objects.equals(this.bankAccountOperation, contributionBankAccount.bankAccountOperation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankAccountIdentifier, bankAccountTypeCode, bankName, bankSequenceNumber, bankAccountNickName, bankAccountStatus, bankAccountOperation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContributionBankAccount {\n");
    sb.append("    bankAccountIdentifier: ").append(toIndentedString(bankAccountIdentifier)).append("\n");
    sb.append("    bankAccountTypeCode: ").append(toIndentedString(bankAccountTypeCode)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    bankSequenceNumber: ").append(toIndentedString(bankSequenceNumber)).append("\n");
    sb.append("    bankAccountNickName: ").append(toIndentedString(bankAccountNickName)).append("\n");
    sb.append("    bankAccountStatus: ").append(toIndentedString(bankAccountStatus)).append("\n");
    sb.append("    bankAccountOperation: ").append(toIndentedString(bankAccountOperation)).append("\n");
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

