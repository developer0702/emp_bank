package com.optum.ftps.ob.core.employer.details.helper;

import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.BankAccountResponseDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.EmployeeIdSearchResponseDTO;
import com.optum.ftps.ob.core.employer.details.dtos.bankaccount.EmployerIdSearchDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountHelper {

    private static final String EMPLOYER_ID_SEARCH = "/association/search";
    private static final String ADD_BANK_ACCOUNT_API = "/api/v1/employer/{employerId}/bank-account";
    private static final String GET_SPECIFIC_BANK_ACCOUNT_API =
            "/api/v1/employer/{employerId}/bank-account/{id}";
    private static final String UPDATE_SPECIFIC_BANK_ACCOUNT_API =
            "/api/v1/employer/{employerId}/bank-account/{id}";

    private final RestTemplate restTemplate;
//    private final WebClient webClient;
    @Value("${aggregate.base.url}")
    private String aggregateBaseUrl;
    @Value("${base.url}")
    private String baseUrl;

//    public BankAccountHelper(WebClient webClient) {
//        this.webClient = webClient;
//    }

    public EmployeeIdSearchResponseDTO getAggregatorServiceResponse(
            EmployerIdSearchDTO employerIdSearchDTO) {

        String url = aggregateBaseUrl + EMPLOYER_ID_SEARCH;

        return restTemplate.postForObject(
                url, employerIdSearchDTO, EmployeeIdSearchResponseDTO.class);
    }

    public BankAccountResponseDTO addBankAccountResponse(
            BankAccountDTO bankAccountDTO, int employeeId) {

        String bankAccountUrl = baseUrl + ADD_BANK_ACCOUNT_API;
        Map<String, Integer> uriVariable = new HashMap<>();
        uriVariable.put("employerId ", employeeId);
        return restTemplate.postForObject(
                bankAccountUrl, bankAccountDTO, BankAccountResponseDTO.class, uriVariable);
    }

    public BankAccountDTO getBankAccountInfoFromBankService(int employeeId, int data) {

        String url = baseUrl + GET_SPECIFIC_BANK_ACCOUNT_API;

        Map<String, Integer> uriVariable = new HashMap<>();
        uriVariable.put("employerId", employeeId);
        uriVariable.put("id", data);
        return restTemplate.getForObject(url, BankAccountDTO.class, uriVariable);
    }

    public BankAccountResponseDTO updateBankAccountResponse(
            BankAccountDTO bankAccountDTO, int employerId, int bankAccountId) {

        String bankAccountUrl = baseUrl + UPDATE_SPECIFIC_BANK_ACCOUNT_API;
        Map<String, Integer> uriVariable = new HashMap<>();
        uriVariable.put("employerId ", employerId);
        uriVariable.put("id ", bankAccountId);
        return restTemplate.postForObject(
                bankAccountUrl, bankAccountDTO, BankAccountResponseDTO.class, uriVariable);
    }

//    public void sendPostRequest() {
//        String url = "https://api-stg.uhg.com/api/financial/banking/core-banking-bis-employer-service/1.0.0/employer/1/bank-account/4";
//        String token = "eyJraWQiOiJHakJ2c2NVZlZ5dlF4dWhuazVRNllwUnZLSkxBS05BYUJjREtTSVlwd3hVIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJhdWQiOiJodHRwczovL2FwaS51aGcuY29tIiwic3ViIjoiODEzOTE4MDktM2E2OC00YTlhLTkxMzYtODQyMGJlN2RlYzdhIiwiYXpwIjoiODEzOTE4MDktM2E2OC00YTlhLTkxMzYtODQyMGJlN2RlYzdhIiwic2NvcGUiOiJodHRwczovL2FwaS51aGcuY29tLy5kZWZhdWx0IiwiaXNzIjoiaHR0cHM6Ly9ub25wcm9kLmlkZW50aXR5LnVoZy5jb20iLCJ0eXAiOiJCZWFyZXIiLCJvaWQiOiI4MTM5MTgwOS0zYTY4LTRhOWEtOTEzNi04NDIwYmU3ZGVjN2EiLCJleHAiOjE3MzkxODcxNTMsImlhdCI6MTczOTE4MzU1MywianRpIjoiM2M3ZDFhMzMtNDc0Ni00ZGYyLWFlZTAtZTE1Mzc3NWU4YWMwIn0.JlRy6B5jOB7d4z_ghx7mJRVJ5WcaJw88lIAck3xNhzaQVvkXOoAw3G66zm-spA2wTJg_8O6euCWSY-0wLckIMcq9eTOD16js4Gzdfhn5XePoGcTme9dmnWeaE90imVuDmsBdNj-DX1H-jlODd0OJcWPC2tLshZYFC4-11UOXeK37PKU3rRRTlFu6NGdY_4nInkpw1dqKKOl6Fi4BiNi3d0IJUNhxMzjbxmLU8zsXIRvVnGUERGcIs8RuMrioOLAyXCdOuVXZco0OZ7-Hmu_-wMSJSAc0Xk94t2NYexKnxSuY-0IyVfla5LkYi4iyB9tpb6-5U47lxvNoddaaItu5lQ";
//
//        String requestBody = "{\n" +
//                "  \"source\": \"EUREKA\",\n" +
//                "  \"correlationId\": \"STRING\",\n" +
//                "  \"usage\": \"HSA_FUNDING\",\n" +
//                "  \"bankName\": \"SBI\",\n" +
//                "  \"routingNumber\": \"223456789\",\n" +
//                "  \"accountNumber\": \"787654321\",\n" +
//                "  \"accountType\": \"C\",\n" +
//                "  \"nickName\": \"hdfc bank\",\n" +
//                "  \"accountStatus\": \"ACTIVE\"\n" +
//                "}";
//
//        try {
//            String response = webClient.post()
//                    .uri(url)
//                    .header("X-Upstream-ENV", "dev")
//                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Bearer " + token)
//                    .bodyValue(requestBody)
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
//
//            log.info("Response: {}", response);
//        } catch (WebClientResponseException e) {
//            log.error("Error in sendPostRequest: {}", e.getMessage());
//        }
//    }
}
