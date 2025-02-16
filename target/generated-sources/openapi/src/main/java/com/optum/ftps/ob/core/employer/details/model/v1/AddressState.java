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
 * AddressState
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class AddressState {

  private String stateCode;

  private String stateName;

  public AddressState stateCode(String stateCode) {
    this.stateCode = stateCode;
    return this;
  }

  /**
   * Get stateCode
   * @return stateCode
  */
  
  @Schema(name = "stateCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stateCode")
  public String getStateCode() {
    return stateCode;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  public AddressState stateName(String stateName) {
    this.stateName = stateName;
    return this;
  }

  /**
   * Get stateName
   * @return stateName
  */
  
  @Schema(name = "stateName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stateName")
  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressState addressState = (AddressState) o;
    return Objects.equals(this.stateCode, addressState.stateCode) &&
        Objects.equals(this.stateName, addressState.stateName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateCode, stateName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressState {\n");
    sb.append("    stateCode: ").append(toIndentedString(stateCode)).append("\n");
    sb.append("    stateName: ").append(toIndentedString(stateName)).append("\n");
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

