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
 * AddressPostalCode
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class AddressPostalCode {

  private String zipCode;

  private String zipPlus4;

  private String postalCode;

  public AddressPostalCode zipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  /**
   * Get zipCode
   * @return zipCode
  */
  
  @Schema(name = "zipCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("zipCode")
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public AddressPostalCode zipPlus4(String zipPlus4) {
    this.zipPlus4 = zipPlus4;
    return this;
  }

  /**
   * Get zipPlus4
   * @return zipPlus4
  */
  
  @Schema(name = "zipPlus4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("zipPlus4")
  public String getZipPlus4() {
    return zipPlus4;
  }

  public void setZipPlus4(String zipPlus4) {
    this.zipPlus4 = zipPlus4;
  }

  public AddressPostalCode postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  /**
   * Get postalCode
   * @return postalCode
  */
  
  @Schema(name = "postalCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("postalCode")
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressPostalCode addressPostalCode = (AddressPostalCode) o;
    return Objects.equals(this.zipCode, addressPostalCode.zipCode) &&
        Objects.equals(this.zipPlus4, addressPostalCode.zipPlus4) &&
        Objects.equals(this.postalCode, addressPostalCode.postalCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zipCode, zipPlus4, postalCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressPostalCode {\n");
    sb.append("    zipCode: ").append(toIndentedString(zipCode)).append("\n");
    sb.append("    zipPlus4: ").append(toIndentedString(zipPlus4)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
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

