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
 * SignatureMethod
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class SignatureMethod {

  private String employerObtainedInd;

  private String wetSignatureInd;

  private String eSignatureInd;

  public SignatureMethod employerObtainedInd(String employerObtainedInd) {
    this.employerObtainedInd = employerObtainedInd;
    return this;
  }

  /**
   * Get employerObtainedInd
   * @return employerObtainedInd
  */
  
  @Schema(name = "employerObtainedInd", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerObtainedInd")
  public String getEmployerObtainedInd() {
    return employerObtainedInd;
  }

  public void setEmployerObtainedInd(String employerObtainedInd) {
    this.employerObtainedInd = employerObtainedInd;
  }

  public SignatureMethod wetSignatureInd(String wetSignatureInd) {
    this.wetSignatureInd = wetSignatureInd;
    return this;
  }

  /**
   * Get wetSignatureInd
   * @return wetSignatureInd
  */
  
  @Schema(name = "wetSignatureInd", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("wetSignatureInd")
  public String getWetSignatureInd() {
    return wetSignatureInd;
  }

  public void setWetSignatureInd(String wetSignatureInd) {
    this.wetSignatureInd = wetSignatureInd;
  }

  public SignatureMethod eSignatureInd(String eSignatureInd) {
    this.eSignatureInd = eSignatureInd;
    return this;
  }

  /**
   * Get eSignatureInd
   * @return eSignatureInd
  */
  
  @Schema(name = "eSignatureInd", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("eSignatureInd")
  public String geteSignatureInd() {
    return eSignatureInd;
  }

  public void seteSignatureInd(String eSignatureInd) {
    this.eSignatureInd = eSignatureInd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignatureMethod signatureMethod = (SignatureMethod) o;
    return Objects.equals(this.employerObtainedInd, signatureMethod.employerObtainedInd) &&
        Objects.equals(this.wetSignatureInd, signatureMethod.wetSignatureInd) &&
        Objects.equals(this.eSignatureInd, signatureMethod.eSignatureInd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employerObtainedInd, wetSignatureInd, eSignatureInd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignatureMethod {\n");
    sb.append("    employerObtainedInd: ").append(toIndentedString(employerObtainedInd)).append("\n");
    sb.append("    wetSignatureInd: ").append(toIndentedString(wetSignatureInd)).append("\n");
    sb.append("    eSignatureInd: ").append(toIndentedString(eSignatureInd)).append("\n");
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

