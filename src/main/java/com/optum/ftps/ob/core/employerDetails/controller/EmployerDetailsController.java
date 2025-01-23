package com.optum.ftps.ob.core.employerDetails.controller;

import com.optum.ftps.ob.core.employerDetails.api.v1.EmployerDetailsApi;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerDetailsResponseMapper;
import com.optum.ftps.ob.core.employerDetails.model.v1.EmployerDetailsResponse;
import com.optum.ftps.ob.core.employerDetails.service.EmployerDetailsService;
import com.optum.ftps.ob.core.employerDetails.util.StringUtil;
import com.optum.ftps.ob.core.employerDetails.validator.EmployerDetailsValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployerDetailsController implements EmployerDetailsApi {
    private final EmployerDetailsValidator employerDetailsValidator;
    private final EmployerDetailsService employerDetailsService;
    private final EmployerDetailsResponseMapper employerDetailsResponseMapper;

    @Override
    public ResponseEntity<EmployerDetailsResponse> getEmployerDetailsById(String empGroupId) {
        empGroupId = StringUtil.sanitize(empGroupId);
        log.debug("empId", empGroupId);

        var errors = employerDetailsValidator.validateEmployerDetailsById(empGroupId);
        // if (!errors.isEmpty()) {
        // log.debug("Validation failed for employer group id: {}", empGroupId);
        // exceptionService.handleValidationError(errors);
        // }
        var employerDetailsResponseDTO = employerDetailsService.getEmployerDetailsById(empGroupId);
        var response =
                employerDetailsResponseMapper.employerDetailsResponse(employerDetailsResponseDTO);
        /*EmployerDetailsResponse response = new EmployerDetailsResponse();
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
        response.setEmployer(employerList);*/

        return new ResponseEntity<EmployerDetailsResponse>(response, HttpStatus.OK);
    }
}
