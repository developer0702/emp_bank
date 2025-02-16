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
 * ErrorItem
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class ErrorItem {

  private String statusCode;

  private String severity;

  private String statusDescription;

  public ErrorItem statusCode(String statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  /**
   * The status code based on the type of error.
   * @return statusCode
  */
  
  @Schema(name = "statusCode", example = "2", description = "The status code based on the type of error.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("statusCode")
  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public ErrorItem severity(String severity) {
    this.severity = severity;
    return this;
  }

  /**
   * Get severity
   * @return severity
  */
  
  @Schema(name = "severity", example = "ERR", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("severity")
  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public ErrorItem statusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
    return this;
  }

  /**
   * Get statusDescription
   * @return statusDescription
  */
  
  @Schema(name = "statusDescription", example = "INCORRECT INPUT FORMAT", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("statusDescription")
  public String getStatusDescription() {
    return statusDescription;
  }

  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorItem errorItem = (ErrorItem) o;
    return Objects.equals(this.statusCode, errorItem.statusCode) &&
        Objects.equals(this.severity, errorItem.severity) &&
        Objects.equals(this.statusDescription, errorItem.statusDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, severity, statusDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorItem {\n");
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    severity: ").append(toIndentedString(severity)).append("\n");
    sb.append("    statusDescription: ").append(toIndentedString(statusDescription)).append("\n");
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

