package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressLines;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressTypeCode;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Address
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class Address {

  private AddressTypeCode addressTypeCode;

  private String primaryAddressIndicator;

  private AddressLines addressLines;

  public Address addressTypeCode(AddressTypeCode addressTypeCode) {
    this.addressTypeCode = addressTypeCode;
    return this;
  }

  /**
   * Get addressTypeCode
   * @return addressTypeCode
  */
  @Valid 
  @Schema(name = "addressTypeCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressTypeCode")
  public AddressTypeCode getAddressTypeCode() {
    return addressTypeCode;
  }

  public void setAddressTypeCode(AddressTypeCode addressTypeCode) {
    this.addressTypeCode = addressTypeCode;
  }

  public Address primaryAddressIndicator(String primaryAddressIndicator) {
    this.primaryAddressIndicator = primaryAddressIndicator;
    return this;
  }

  /**
   * Get primaryAddressIndicator
   * @return primaryAddressIndicator
  */
  
  @Schema(name = "primaryAddressIndicator", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("primaryAddressIndicator")
  public String getPrimaryAddressIndicator() {
    return primaryAddressIndicator;
  }

  public void setPrimaryAddressIndicator(String primaryAddressIndicator) {
    this.primaryAddressIndicator = primaryAddressIndicator;
  }

  public Address addressLines(AddressLines addressLines) {
    this.addressLines = addressLines;
    return this;
  }

  /**
   * Get addressLines
   * @return addressLines
  */
  @Valid 
  @Schema(name = "addressLines", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressLines")
  public AddressLines getAddressLines() {
    return addressLines;
  }

  public void setAddressLines(AddressLines addressLines) {
    this.addressLines = addressLines;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.addressTypeCode, address.addressTypeCode) &&
        Objects.equals(this.primaryAddressIndicator, address.primaryAddressIndicator) &&
        Objects.equals(this.addressLines, address.addressLines);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressTypeCode, primaryAddressIndicator, addressLines);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    sb.append("    addressTypeCode: ").append(toIndentedString(addressTypeCode)).append("\n");
    sb.append("    primaryAddressIndicator: ").append(toIndentedString(primaryAddressIndicator)).append("\n");
    sb.append("    addressLines: ").append(toIndentedString(addressLines)).append("\n");
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

