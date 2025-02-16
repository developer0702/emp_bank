package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Date;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * EmployerHDHPPolicy
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class EmployerHDHPPolicy {

  private Date hdHPEffectiveDate = null;

  private Date hdHPCaseSoldDate = null;

  public EmployerHDHPPolicy hdHPEffectiveDate(Date hdHPEffectiveDate) {
    this.hdHPEffectiveDate = hdHPEffectiveDate;
    return this;
  }

  /**
   * Get hdHPEffectiveDate
   * @return hdHPEffectiveDate
  */
  @Valid 
  @Schema(name = "HDHPEffectiveDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("HDHPEffectiveDate")
  public Date getHdHPEffectiveDate() {
    return hdHPEffectiveDate;
  }

  public void setHdHPEffectiveDate(Date hdHPEffectiveDate) {
    this.hdHPEffectiveDate = hdHPEffectiveDate;
  }

  public EmployerHDHPPolicy hdHPCaseSoldDate(Date hdHPCaseSoldDate) {
    this.hdHPCaseSoldDate = hdHPCaseSoldDate;
    return this;
  }

  /**
   * Get hdHPCaseSoldDate
   * @return hdHPCaseSoldDate
  */
  @Valid 
  @Schema(name = "HDHPCaseSoldDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("HDHPCaseSoldDate")
  public Date getHdHPCaseSoldDate() {
    return hdHPCaseSoldDate;
  }

  public void setHdHPCaseSoldDate(Date hdHPCaseSoldDate) {
    this.hdHPCaseSoldDate = hdHPCaseSoldDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployerHDHPPolicy employerHDHPPolicy = (EmployerHDHPPolicy) o;
    return Objects.equals(this.hdHPEffectiveDate, employerHDHPPolicy.hdHPEffectiveDate) &&
        Objects.equals(this.hdHPCaseSoldDate, employerHDHPPolicy.hdHPCaseSoldDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hdHPEffectiveDate, hdHPCaseSoldDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployerHDHPPolicy {\n");
    sb.append("    hdHPEffectiveDate: ").append(toIndentedString(hdHPEffectiveDate)).append("\n");
    sb.append("    hdHPCaseSoldDate: ").append(toIndentedString(hdHPCaseSoldDate)).append("\n");
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

