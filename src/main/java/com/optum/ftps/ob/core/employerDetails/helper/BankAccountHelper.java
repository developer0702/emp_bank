package com.optum.ftps.ob.core.employerDetails.helper;

import com.optum.ftps.ob.core.employerDetails.dtos.bankaccount.BankAccountDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.bankaccount.BankAccountResponseDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.bankaccount.EmployeeIdSearchResponseDTO;
import com.optum.ftps.ob.core.employerDetails.dtos.bankaccount.EmployerIdSearchDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountHelper {

    private static final String EMPLOYER_ID_SEARCH = "/association/search";
    private static final String ADD_BANK_ACCOUNT_API=  "/api/v1/employer/{employerId}/bank-account";
    private static final String GET_SPECIFIC_BANK_ACCOUNT_API="/api/v1/employer/{employerId}/bank-account/{id}";
    private static final String UPDATE_SPECIFIC_BANK_ACCOUNT_API="/api/v1/employer/{employerId}/bank-account/{id}";

    private final RestTemplate restTemplate;
    @Value("${aggregate.base.url}")
    private String aggregateBaseUrl;

    @Value("${base.url}")
    private String baseUrl;

    public EmployeeIdSearchResponseDTO getAggregatorServiceResponse(EmployerIdSearchDTO employerIdSearchDTO ){

        String url = aggregateBaseUrl + EMPLOYER_ID_SEARCH;


        return restTemplate.postForObject(
                url, employerIdSearchDTO, EmployeeIdSearchResponseDTO.class);
    }

    public BankAccountResponseDTO addBankAccountResponse(BankAccountDTO bankAccountDTO, int employeeId){

        String bankAccountUrl=baseUrl+ADD_BANK_ACCOUNT_API;
        Map<String,Integer> uriVariable=new HashMap<>();
        uriVariable.put("employerId ",employeeId);
        return restTemplate.postForObject(bankAccountUrl,bankAccountDTO,BankAccountResponseDTO.class,uriVariable);

    }

    public BankAccountDTO getBankAccountInfoFromBankService(int employeeId, int data) {

        String url = baseUrl + GET_SPECIFIC_BANK_ACCOUNT_API;

        Map<String,Integer> uriVariable=new HashMap<>();
        uriVariable.put("employerId",employeeId);
        uriVariable.put("id",data);
        return restTemplate.getForObject(url,BankAccountDTO.class,uriVariable);
    }
    public BankAccountResponseDTO updateBankAccountResponse(BankAccountDTO bankAccountDTO, int employerId,int bankAccountId){

        String bankAccountUrl=baseUrl+UPDATE_SPECIFIC_BANK_ACCOUNT_API;
        Map<String,Integer> uriVariable=new HashMap<>();
        uriVariable.put("employerId ",employerId);
        uriVariable.put("id ",bankAccountId);
        return restTemplate.postForObject(bankAccountUrl,bankAccountDTO,BankAccountResponseDTO.class,uriVariable);

    }
}
