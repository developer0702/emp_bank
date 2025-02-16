package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.Employer;
import com.optum.ftps.ob.core.employer.details.model.v1.Status;
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
 * EmployerDetailsResponse
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class EmployerDetailsResponse {

  private String requestId;

  private Status status;

  @Valid
  private List<@Valid Employer> employer;

  public EmployerDetailsResponse requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  /**
   * Get requestId
   * @return requestId
  */
  
  @Schema(name = "requestId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestId")
  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public EmployerDetailsResponse status(Status status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public EmployerDetailsResponse employer(List<@Valid Employer> employer) {
    this.employer = employer;
    return this;
  }

  public EmployerDetailsResponse addEmployerItem(Employer employerItem) {
    if (this.employer == null) {
      this.employer = new ArrayList<>();
    }
    this.employer.add(employerItem);
    return this;
  }

  /**
   * Get employer
   * @return employer
  */
  @Valid 
  @Schema(name = "employer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employer")
  public List<@Valid Employer> getEmployer() {
    return employer;
  }

  public void setEmployer(List<@Valid Employer> employer) {
    this.employer = employer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployerDetailsResponse employerDetailsResponse = (EmployerDetailsResponse) o;
    return Objects.equals(this.requestId, employerDetailsResponse.requestId) &&
        Objects.equals(this.status, employerDetailsResponse.status) &&
        Objects.equals(this.employer, employerDetailsResponse.employer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, status, employer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployerDetailsResponse {\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    employer: ").append(toIndentedString(employer)).append("\n");
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

