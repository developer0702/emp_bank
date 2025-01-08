package com.optum.ftps.ob.core.employerDetails.controller;

import com.optum.ftps.ob.core.employerDetails.api.v1.EmployerDetailsApi;
import com.optum.ftps.ob.core.employerDetails.model.v1.Address;
import com.optum.ftps.ob.core.employerDetails.model.v1.AddressLines;
import com.optum.ftps.ob.core.employerDetails.model.v1.AddressPostalCode;
import com.optum.ftps.ob.core.employerDetails.model.v1.AddressState;
import com.optum.ftps.ob.core.employerDetails.model.v1.AddressTypeCode;
import com.optum.ftps.ob.core.employerDetails.model.v1.Employer;
import com.optum.ftps.ob.core.employerDetails.model.v1.EmployerDetailsResponse;
import com.optum.ftps.ob.core.employerDetails.model.v1.Status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployerDetailsController implements EmployerDetailsApi {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return EmployerDetailsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<EmployerDetailsResponse> getEmployerDetails(String empGroupId) {
        empGroupId = "1";

        EmployerDetailsResponse response = new EmployerDetailsResponse();
        Map<String, Object> responseDetails = new HashMap<>();
        response.setRequestId("1");

        Status status = new Status();
        status.setStatusCode("0000");
        status.setSeverity("INF");
        status.setStatusDescription("REQUEST SUCCESSFULLY PROCESSED");
        response.setStatus(status);

        List<Employer> employerList = new ArrayList<>();
        Employer employer = new Employer();
        employer.setEmployerGroupId("4H9960");
        employer.setEmployerName("BMC SOUTHEAST");
        employer.setEmployerSetupStatus("Active");
        employer.setUhGLineOfBusiness("Small Business");

        Address address = new Address();
        AddressTypeCode addressTypeCode = new AddressTypeCode();
        addressTypeCode.setCode("B");
        addressTypeCode.setCodeName("Business");
        address.setAddressTypeCode(addressTypeCode);
        address.setPrimaryAddressIndicator("Y");

        AddressLines addressLines = new AddressLines();
        addressLines.setAddressDeliveryLine1("1406 HAYS ST UNIT 8");
        addressLines.setAddressDeliveryLine2("POSTSTREET");
        addressLines.setAddressCity("TALLAHASSEE");

        AddressState addressState = new AddressState();
        addressState.setStateCode("FL");
        addressState.setStateName("AP");
        addressLines.setAddressState(addressState);

        AddressPostalCode addressPostalCode = new AddressPostalCode();
        addressPostalCode.setZipCode("0000");
        addressPostalCode.setZipPlus4("32301");
        addressPostalCode.setPostalCode("0000");
        addressLines.setAddressPostalCode(addressPostalCode);

        address.setAddressLines(addressLines);
        employer.setAddress(address);

        // Add other fields similarly...
        employerList.add(employer);
        response.setEmployer(employerList);

        return new ResponseEntity<EmployerDetailsResponse>(response, HttpStatus.OK);
    }
}
