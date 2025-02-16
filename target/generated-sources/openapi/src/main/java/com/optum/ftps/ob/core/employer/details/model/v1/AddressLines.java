package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressPostalCode;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressState;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AddressLines
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class AddressLines {

  private String addressDeliveryLine1;

  private String addressDeliveryLine2;

  private String addressCity;

  private AddressState addressState;

  private AddressPostalCode addressPostalCode;

  public AddressLines addressDeliveryLine1(String addressDeliveryLine1) {
    this.addressDeliveryLine1 = addressDeliveryLine1;
    return this;
  }

  /**
   * Get addressDeliveryLine1
   * @return addressDeliveryLine1
  */
  
  @Schema(name = "addressDeliveryLine1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressDeliveryLine1")
  public String getAddressDeliveryLine1() {
    return addressDeliveryLine1;
  }

  public void setAddressDeliveryLine1(String addressDeliveryLine1) {
    this.addressDeliveryLine1 = addressDeliveryLine1;
  }

  public AddressLines addressDeliveryLine2(String addressDeliveryLine2) {
    this.addressDeliveryLine2 = addressDeliveryLine2;
    return this;
  }

  /**
   * Get addressDeliveryLine2
   * @return addressDeliveryLine2
  */
  
  @Schema(name = "addressDeliveryLine2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressDeliveryLine2")
  public String getAddressDeliveryLine2() {
    return addressDeliveryLine2;
  }

  public void setAddressDeliveryLine2(String addressDeliveryLine2) {
    this.addressDeliveryLine2 = addressDeliveryLine2;
  }

  public AddressLines addressCity(String addressCity) {
    this.addressCity = addressCity;
    return this;
  }

  /**
   * Get addressCity
   * @return addressCity
  */
  
  @Schema(name = "addressCity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressCity")
  public String getAddressCity() {
    return addressCity;
  }

  public void setAddressCity(String addressCity) {
    this.addressCity = addressCity;
  }

  public AddressLines addressState(AddressState addressState) {
    this.addressState = addressState;
    return this;
  }

  /**
   * Get addressState
   * @return addressState
  */
  @Valid 
  @Schema(name = "addressState", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressState")
  public AddressState getAddressState() {
    return addressState;
  }

  public void setAddressState(AddressState addressState) {
    this.addressState = addressState;
  }

  public AddressLines addressPostalCode(AddressPostalCode addressPostalCode) {
    this.addressPostalCode = addressPostalCode;
    return this;
  }

  /**
   * Get addressPostalCode
   * @return addressPostalCode
  */
  @Valid 
  @Schema(name = "addressPostalCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressPostalCode")
  public AddressPostalCode getAddressPostalCode() {
    return addressPostalCode;
  }

  public void setAddressPostalCode(AddressPostalCode addressPostalCode) {
    this.addressPostalCode = addressPostalCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressLines addressLines = (AddressLines) o;
    return Objects.equals(this.addressDeliveryLine1, addressLines.addressDeliveryLine1) &&
        Objects.equals(this.addressDeliveryLine2, addressLines.addressDeliveryLine2) &&
        Objects.equals(this.addressCity, addressLines.addressCity) &&
        Objects.equals(this.addressState, addressLines.addressState) &&
        Objects.equals(this.addressPostalCode, addressLines.addressPostalCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressDeliveryLine1, addressDeliveryLine2, addressCity, addressState, addressPostalCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressLines {\n");
    sb.append("    addressDeliveryLine1: ").append(toIndentedString(addressDeliveryLine1)).append("\n");
    sb.append("    addressDeliveryLine2: ").append(toIndentedString(addressDeliveryLine2)).append("\n");
    sb.append("    addressCity: ").append(toIndentedString(addressCity)).append("\n");
    sb.append("    addressState: ").append(toIndentedString(addressState)).append("\n");
    sb.append("    addressPostalCode: ").append(toIndentedString(addressPostalCode)).append("\n");
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

