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
 * AddressTypeCode
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class AddressTypeCode {

  private String code;

  private String codeName;

  public AddressTypeCode code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  
  @Schema(name = "code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public AddressTypeCode codeName(String codeName) {
    this.codeName = codeName;
    return this;
  }

  /**
   * Get codeName
   * @return codeName
  */
  
  @Schema(name = "codeName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("codeName")
  public String getCodeName() {
    return codeName;
  }

  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressTypeCode addressTypeCode = (AddressTypeCode) o;
    return Objects.equals(this.code, addressTypeCode.code) &&
        Objects.equals(this.codeName, addressTypeCode.codeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, codeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressTypeCode {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codeName: ").append(toIndentedString(codeName)).append("\n");
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

