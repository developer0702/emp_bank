package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.Address;
import com.optum.ftps.ob.core.employer.details.model.v1.ContributionBankAccount;
import com.optum.ftps.ob.core.employer.details.model.v1.EmployerHDHPPolicy;
import com.optum.ftps.ob.core.employer.details.model.v1.PayerDetail;
import com.optum.ftps.ob.core.employer.details.model.v1.ProductDetails;
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
 * Employer
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class Employer {

  private String employerGroupId;

  private String employerName;

  private String employerSetupStatus;

  private String uhGLineOfBusiness;

  private Address address;

  @Valid
  private List<@Valid ContributionBankAccount> contributionBankAccounts;

  private EmployerHDHPPolicy employerHDHPPolicy;

  private PayerDetail payerDetail;

  private ProductDetails productDetails;

  private String empBankFeeScheduleName;

  public Employer employerGroupId(String employerGroupId) {
    this.employerGroupId = employerGroupId;
    return this;
  }

  /**
   * Get employerGroupId
   * @return employerGroupId
  */
  
  @Schema(name = "employerGroupId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerGroupId")
  public String getEmployerGroupId() {
    return employerGroupId;
  }

  public void setEmployerGroupId(String employerGroupId) {
    this.employerGroupId = employerGroupId;
  }

  public Employer employerName(String employerName) {
    this.employerName = employerName;
    return this;
  }

  /**
   * Get employerName
   * @return employerName
  */
  
  @Schema(name = "employerName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerName")
  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

  public Employer employerSetupStatus(String employerSetupStatus) {
    this.employerSetupStatus = employerSetupStatus;
    return this;
  }

  /**
   * Get employerSetupStatus
   * @return employerSetupStatus
  */
  
  @Schema(name = "employerSetupStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerSetupStatus")
  public String getEmployerSetupStatus() {
    return employerSetupStatus;
  }

  public void setEmployerSetupStatus(String employerSetupStatus) {
    this.employerSetupStatus = employerSetupStatus;
  }

  public Employer uhGLineOfBusiness(String uhGLineOfBusiness) {
    this.uhGLineOfBusiness = uhGLineOfBusiness;
    return this;
  }

  /**
   * Get uhGLineOfBusiness
   * @return uhGLineOfBusiness
  */
  
  @Schema(name = "UHGLineOfBusiness", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("UHGLineOfBusiness")
  public String getUhGLineOfBusiness() {
    return uhGLineOfBusiness;
  }

  public void setUhGLineOfBusiness(String uhGLineOfBusiness) {
    this.uhGLineOfBusiness = uhGLineOfBusiness;
  }

  public Employer address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @Valid 
  @Schema(name = "address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Employer contributionBankAccounts(List<@Valid ContributionBankAccount> contributionBankAccounts) {
    this.contributionBankAccounts = contributionBankAccounts;
    return this;
  }

  public Employer addContributionBankAccountsItem(ContributionBankAccount contributionBankAccountsItem) {
    if (this.contributionBankAccounts == null) {
      this.contributionBankAccounts = new ArrayList<>();
    }
    this.contributionBankAccounts.add(contributionBankAccountsItem);
    return this;
  }

  /**
   * Get contributionBankAccounts
   * @return contributionBankAccounts
  */
  @Valid 
  @Schema(name = "contributionBankAccounts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contributionBankAccounts")
  public List<@Valid ContributionBankAccount> getContributionBankAccounts() {
    return contributionBankAccounts;
  }

  public void setContributionBankAccounts(List<@Valid ContributionBankAccount> contributionBankAccounts) {
    this.contributionBankAccounts = contributionBankAccounts;
  }

  public Employer employerHDHPPolicy(EmployerHDHPPolicy employerHDHPPolicy) {
    this.employerHDHPPolicy = employerHDHPPolicy;
    return this;
  }

  /**
   * Get employerHDHPPolicy
   * @return employerHDHPPolicy
  */
  @Valid 
  @Schema(name = "employerHDHPPolicy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerHDHPPolicy")
  public EmployerHDHPPolicy getEmployerHDHPPolicy() {
    return employerHDHPPolicy;
  }

  public void setEmployerHDHPPolicy(EmployerHDHPPolicy employerHDHPPolicy) {
    this.employerHDHPPolicy = employerHDHPPolicy;
  }

  public Employer payerDetail(PayerDetail payerDetail) {
    this.payerDetail = payerDetail;
    return this;
  }

  /**
   * Get payerDetail
   * @return payerDetail
  */
  @Valid 
  @Schema(name = "payerDetail", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("payerDetail")
  public PayerDetail getPayerDetail() {
    return payerDetail;
  }

  public void setPayerDetail(PayerDetail payerDetail) {
    this.payerDetail = payerDetail;
  }

  public Employer productDetails(ProductDetails productDetails) {
    this.productDetails = productDetails;
    return this;
  }

  /**
   * Get productDetails
   * @return productDetails
  */
  @Valid 
  @Schema(name = "productDetails", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("productDetails")
  public ProductDetails getProductDetails() {
    return productDetails;
  }

  public void setProductDetails(ProductDetails productDetails) {
    this.productDetails = productDetails;
  }

  public Employer empBankFeeScheduleName(String empBankFeeScheduleName) {
    this.empBankFeeScheduleName = empBankFeeScheduleName;
    return this;
  }

  /**
   * Get empBankFeeScheduleName
   * @return empBankFeeScheduleName
  */
  
  @Schema(name = "empBankFeeScheduleName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("empBankFeeScheduleName")
  public String getEmpBankFeeScheduleName() {
    return empBankFeeScheduleName;
  }

  public void setEmpBankFeeScheduleName(String empBankFeeScheduleName) {
    this.empBankFeeScheduleName = empBankFeeScheduleName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employer employer = (Employer) o;
    return Objects.equals(this.employerGroupId, employer.employerGroupId) &&
        Objects.equals(this.employerName, employer.employerName) &&
        Objects.equals(this.employerSetupStatus, employer.employerSetupStatus) &&
        Objects.equals(this.uhGLineOfBusiness, employer.uhGLineOfBusiness) &&
        Objects.equals(this.address, employer.address) &&
        Objects.equals(this.contributionBankAccounts, employer.contributionBankAccounts) &&
        Objects.equals(this.employerHDHPPolicy, employer.employerHDHPPolicy) &&
        Objects.equals(this.payerDetail, employer.payerDetail) &&
        Objects.equals(this.productDetails, employer.productDetails) &&
        Objects.equals(this.empBankFeeScheduleName, employer.empBankFeeScheduleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employerGroupId, employerName, employerSetupStatus, uhGLineOfBusiness, address, contributionBankAccounts, employerHDHPPolicy, payerDetail, productDetails, empBankFeeScheduleName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Employer {\n");
    sb.append("    employerGroupId: ").append(toIndentedString(employerGroupId)).append("\n");
    sb.append("    employerName: ").append(toIndentedString(employerName)).append("\n");
    sb.append("    employerSetupStatus: ").append(toIndentedString(employerSetupStatus)).append("\n");
    sb.append("    uhGLineOfBusiness: ").append(toIndentedString(uhGLineOfBusiness)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contributionBankAccounts: ").append(toIndentedString(contributionBankAccounts)).append("\n");
    sb.append("    employerHDHPPolicy: ").append(toIndentedString(employerHDHPPolicy)).append("\n");
    sb.append("    payerDetail: ").append(toIndentedString(payerDetail)).append("\n");
    sb.append("    productDetails: ").append(toIndentedString(productDetails)).append("\n");
    sb.append("    empBankFeeScheduleName: ").append(toIndentedString(empBankFeeScheduleName)).append("\n");
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

