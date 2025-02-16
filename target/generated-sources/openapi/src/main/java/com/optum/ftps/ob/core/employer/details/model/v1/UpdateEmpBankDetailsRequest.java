package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateEmpBankDetailsRequest
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class UpdateEmpBankDetailsRequest {

  @lombok.NonNull
  private String requestId;

  @lombok.NonNull
  private String employerGroupId;

  @lombok.NonNull
  private String employerBankSeqNum;

  private JsonNullable<String> employerAccNum = JsonNullable.<String>undefined();

  private JsonNullable<String> employerRoutingNum = JsonNullable.<String>undefined();

  private JsonNullable<String> employerBankName = JsonNullable.<String>undefined();

  private JsonNullable<String> employerAccNickName = JsonNullable.<String>undefined();

  private JsonNullable<String> employerAccStatCode = JsonNullable.<String>undefined();

  private JsonNullable<String> employerAccOperation = JsonNullable.<String>undefined();

  /**
   * Constructor with only required parameters
   */
  public UpdateEmpBankDetailsRequest(String requestId, String employerGroupId, String employerBankSeqNum) {
    this.requestId = requestId;
    this.employerGroupId = employerGroupId;
    this.employerBankSeqNum = employerBankSeqNum;
  }

  public UpdateEmpBankDetailsRequest requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  /**
   * Get requestId
   * @return requestId
  */
  @NotNull 
  @Schema(name = "requestId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("requestId")
  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public UpdateEmpBankDetailsRequest employerGroupId(String employerGroupId) {
    this.employerGroupId = employerGroupId;
    return this;
  }

  /**
   * Get employerGroupId
   * @return employerGroupId
  */
  @NotNull 
  @Schema(name = "employerGroupId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("employerGroupId")
  public String getEmployerGroupId() {
    return employerGroupId;
  }

  public void setEmployerGroupId(String employerGroupId) {
    this.employerGroupId = employerGroupId;
  }

  public UpdateEmpBankDetailsRequest employerBankSeqNum(String employerBankSeqNum) {
    this.employerBankSeqNum = employerBankSeqNum;
    return this;
  }

  /**
   * Get employerBankSeqNum
   * @return employerBankSeqNum
  */
  @NotNull 
  @Schema(name = "employerBankSeqNum", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("employerBankSeqNum")
  public String getEmployerBankSeqNum() {
    return employerBankSeqNum;
  }

  public void setEmployerBankSeqNum(String employerBankSeqNum) {
    this.employerBankSeqNum = employerBankSeqNum;
  }

  public UpdateEmpBankDetailsRequest employerAccNum(String employerAccNum) {
    this.employerAccNum = JsonNullable.of(employerAccNum);
    return this;
  }

  /**
   * Get employerAccNum
   * @return employerAccNum
  */
  
  @Schema(name = "employerAccNum", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerAccNum")
  public JsonNullable<String> getEmployerAccNum() {
    return employerAccNum;
  }

  public void setEmployerAccNum(JsonNullable<String> employerAccNum) {
    this.employerAccNum = employerAccNum;
  }

  public UpdateEmpBankDetailsRequest employerRoutingNum(String employerRoutingNum) {
    this.employerRoutingNum = JsonNullable.of(employerRoutingNum);
    return this;
  }

  /**
   * Get employerRoutingNum
   * @return employerRoutingNum
  */
  
  @Schema(name = "employerRoutingNum", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerRoutingNum")
  public JsonNullable<String> getEmployerRoutingNum() {
    return employerRoutingNum;
  }

  public void setEmployerRoutingNum(JsonNullable<String> employerRoutingNum) {
    this.employerRoutingNum = employerRoutingNum;
  }

  public UpdateEmpBankDetailsRequest employerBankName(String employerBankName) {
    this.employerBankName = JsonNullable.of(employerBankName);
    return this;
  }

  /**
   * Get employerBankName
   * @return employerBankName
  */
  
  @Schema(name = "employerBankName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerBankName")
  public JsonNullable<String> getEmployerBankName() {
    return employerBankName;
  }

  public void setEmployerBankName(JsonNullable<String> employerBankName) {
    this.employerBankName = employerBankName;
  }

  public UpdateEmpBankDetailsRequest employerAccNickName(String employerAccNickName) {
    this.employerAccNickName = JsonNullable.of(employerAccNickName);
    return this;
  }

  /**
   * Get employerAccNickName
   * @return employerAccNickName
  */
  
  @Schema(name = "employerAccNickName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerAccNickName")
  public JsonNullable<String> getEmployerAccNickName() {
    return employerAccNickName;
  }

  public void setEmployerAccNickName(JsonNullable<String> employerAccNickName) {
    this.employerAccNickName = employerAccNickName;
  }

  public UpdateEmpBankDetailsRequest employerAccStatCode(String employerAccStatCode) {
    this.employerAccStatCode = JsonNullable.of(employerAccStatCode);
    return this;
  }

  /**
   * Get employerAccStatCode
   * @return employerAccStatCode
  */
  
  @Schema(name = "employerAccStatCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerAccStatCode")
  public JsonNullable<String> getEmployerAccStatCode() {
    return employerAccStatCode;
  }

  public void setEmployerAccStatCode(JsonNullable<String> employerAccStatCode) {
    this.employerAccStatCode = employerAccStatCode;
  }

  public UpdateEmpBankDetailsRequest employerAccOperation(String employerAccOperation) {
    this.employerAccOperation = JsonNullable.of(employerAccOperation);
    return this;
  }

  /**
   * Get employerAccOperation
   * @return employerAccOperation
  */
  
  @Schema(name = "employerAccOperation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employerAccOperation")
  public JsonNullable<String> getEmployerAccOperation() {
    return employerAccOperation;
  }

  public void setEmployerAccOperation(JsonNullable<String> employerAccOperation) {
    this.employerAccOperation = employerAccOperation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateEmpBankDetailsRequest updateEmpBankDetailsRequest = (UpdateEmpBankDetailsRequest) o;
    return Objects.equals(this.requestId, updateEmpBankDetailsRequest.requestId) &&
        Objects.equals(this.employerGroupId, updateEmpBankDetailsRequest.employerGroupId) &&
        Objects.equals(this.employerBankSeqNum, updateEmpBankDetailsRequest.employerBankSeqNum) &&
        equalsNullable(this.employerAccNum, updateEmpBankDetailsRequest.employerAccNum) &&
        equalsNullable(this.employerRoutingNum, updateEmpBankDetailsRequest.employerRoutingNum) &&
        equalsNullable(this.employerBankName, updateEmpBankDetailsRequest.employerBankName) &&
        equalsNullable(this.employerAccNickName, updateEmpBankDetailsRequest.employerAccNickName) &&
        equalsNullable(this.employerAccStatCode, updateEmpBankDetailsRequest.employerAccStatCode) &&
        equalsNullable(this.employerAccOperation, updateEmpBankDetailsRequest.employerAccOperation);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, employerGroupId, employerBankSeqNum, hashCodeNullable(employerAccNum), hashCodeNullable(employerRoutingNum), hashCodeNullable(employerBankName), hashCodeNullable(employerAccNickName), hashCodeNullable(employerAccStatCode), hashCodeNullable(employerAccOperation));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateEmpBankDetailsRequest {\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    employerGroupId: ").append(toIndentedString(employerGroupId)).append("\n");
    sb.append("    employerBankSeqNum: ").append(toIndentedString(employerBankSeqNum)).append("\n");
    sb.append("    employerAccNum: ").append(toIndentedString(employerAccNum)).append("\n");
    sb.append("    employerRoutingNum: ").append(toIndentedString(employerRoutingNum)).append("\n");
    sb.append("    employerBankName: ").append(toIndentedString(employerBankName)).append("\n");
    sb.append("    employerAccNickName: ").append(toIndentedString(employerAccNickName)).append("\n");
    sb.append("    employerAccStatCode: ").append(toIndentedString(employerAccStatCode)).append("\n");
    sb.append("    employerAccOperation: ").append(toIndentedString(employerAccOperation)).append("\n");
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

