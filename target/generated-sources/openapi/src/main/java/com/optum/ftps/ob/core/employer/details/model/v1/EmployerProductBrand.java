package com.optum.ftps.ob.core.employer.details.model.v1;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.optum.ftps.ob.core.employer.details.model.v1.FinanacialProductWebBrandCode;
import com.optum.ftps.ob.core.employer.details.model.v1.FinancialProductCardsBrandCode;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * EmployerProductBrand
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-16T10:35:46.195443200+05:30[Asia/Calcutta]")
public class EmployerProductBrand {

  private FinancialProductCardsBrandCode financialProductCardsBrandCode;

  private FinanacialProductWebBrandCode finanacialProductWebBrandCode;

  public EmployerProductBrand financialProductCardsBrandCode(FinancialProductCardsBrandCode financialProductCardsBrandCode) {
    this.financialProductCardsBrandCode = financialProductCardsBrandCode;
    return this;
  }

  /**
   * Get financialProductCardsBrandCode
   * @return financialProductCardsBrandCode
  */
  @Valid 
  @Schema(name = "financialProductCardsBrandCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("financialProductCardsBrandCode")
  public FinancialProductCardsBrandCode getFinancialProductCardsBrandCode() {
    return financialProductCardsBrandCode;
  }

  public void setFinancialProductCardsBrandCode(FinancialProductCardsBrandCode financialProductCardsBrandCode) {
    this.financialProductCardsBrandCode = financialProductCardsBrandCode;
  }

  public EmployerProductBrand finanacialProductWebBrandCode(FinanacialProductWebBrandCode finanacialProductWebBrandCode) {
    this.finanacialProductWebBrandCode = finanacialProductWebBrandCode;
    return this;
  }

  /**
   * Get finanacialProductWebBrandCode
   * @return finanacialProductWebBrandCode
  */
  @Valid 
  @Schema(name = "finanacialProductWebBrandCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("finanacialProductWebBrandCode")
  public FinanacialProductWebBrandCode getFinanacialProductWebBrandCode() {
    return finanacialProductWebBrandCode;
  }

  public void setFinanacialProductWebBrandCode(FinanacialProductWebBrandCode finanacialProductWebBrandCode) {
    this.finanacialProductWebBrandCode = finanacialProductWebBrandCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployerProductBrand employerProductBrand = (EmployerProductBrand) o;
    return Objects.equals(this.financialProductCardsBrandCode, employerProductBrand.financialProductCardsBrandCode) &&
        Objects.equals(this.finanacialProductWebBrandCode, employerProductBrand.finanacialProductWebBrandCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(financialProductCardsBrandCode, finanacialProductWebBrandCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployerProductBrand {\n");
    sb.append("    financialProductCardsBrandCode: ").append(toIndentedString(financialProductCardsBrandCode)).append("\n");
    sb.append("    finanacialProductWebBrandCode: ").append(toIndentedString(finanacialProductWebBrandCode)).append("\n");
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

