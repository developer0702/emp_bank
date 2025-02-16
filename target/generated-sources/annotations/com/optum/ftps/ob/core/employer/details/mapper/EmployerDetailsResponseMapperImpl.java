package com.optum.ftps.ob.core.employer.details.mapper;

import com.optum.ftps.ob.core.employer.details.dtos.AddressDTO;
import com.optum.ftps.ob.core.employer.details.dtos.AddressLinesDTO;
import com.optum.ftps.ob.core.employer.details.dtos.AddressPostalCodeDTO;
import com.optum.ftps.ob.core.employer.details.dtos.AddressStateDTO;
import com.optum.ftps.ob.core.employer.details.dtos.AddressTypeCodeDTO;
import com.optum.ftps.ob.core.employer.details.dtos.BankAccountIdentifierDTO;
import com.optum.ftps.ob.core.employer.details.dtos.BankAccountStatusDTO;
import com.optum.ftps.ob.core.employer.details.dtos.BankAccountTypeCodeDTO;
import com.optum.ftps.ob.core.employer.details.dtos.ContributionBankAccountDTO;
import com.optum.ftps.ob.core.employer.details.dtos.EmployerDTO;
import com.optum.ftps.ob.core.employer.details.dtos.EmployerDetailsResponseDTO;
import com.optum.ftps.ob.core.employer.details.dtos.EmployerHDHPPolicyDTO;
import com.optum.ftps.ob.core.employer.details.dtos.PayerDetailDTO;
import com.optum.ftps.ob.core.employer.details.dtos.StatusDTO;
import com.optum.ftps.ob.core.employer.details.model.v1.Address;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressLines;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressPostalCode;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressState;
import com.optum.ftps.ob.core.employer.details.model.v1.AddressTypeCode;
import com.optum.ftps.ob.core.employer.details.model.v1.BankAccountIdentifier;
import com.optum.ftps.ob.core.employer.details.model.v1.BankAccountStatus;
import com.optum.ftps.ob.core.employer.details.model.v1.BankAccountTypeCode;
import com.optum.ftps.ob.core.employer.details.model.v1.ContributionBankAccount;
import com.optum.ftps.ob.core.employer.details.model.v1.Employer;
import com.optum.ftps.ob.core.employer.details.model.v1.EmployerDetailsResponse;
import com.optum.ftps.ob.core.employer.details.model.v1.EmployerHDHPPolicy;
import com.optum.ftps.ob.core.employer.details.model.v1.PayerDetail;
import com.optum.ftps.ob.core.employer.details.model.v1.Status;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T10:36:27+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class EmployerDetailsResponseMapperImpl implements EmployerDetailsResponseMapper {

    @Override
    public EmployerDetailsResponse employerDetailsResponse(EmployerDetailsResponseDTO employerDetailsResponseDTO) {
        if ( employerDetailsResponseDTO == null ) {
            return null;
        }

        EmployerDetailsResponse.EmployerDetailsResponseBuilder employerDetailsResponse = EmployerDetailsResponse.builder();

        employerDetailsResponse.employer( employerDTOListToEmployerList( employerDetailsResponseDTO.getEmployerDTO() ) );
        employerDetailsResponse.status( statusDTOToStatus( employerDetailsResponseDTO.getStatusDTO() ) );

        return employerDetailsResponse.build();
    }

    protected AddressTypeCode addressTypeCodeDTOToAddressTypeCode(AddressTypeCodeDTO addressTypeCodeDTO) {
        if ( addressTypeCodeDTO == null ) {
            return null;
        }

        AddressTypeCode.AddressTypeCodeBuilder addressTypeCode = AddressTypeCode.builder();

        addressTypeCode.code( addressTypeCodeDTO.getCode() );
        addressTypeCode.codeName( addressTypeCodeDTO.getCodeName() );

        return addressTypeCode.build();
    }

    protected AddressState addressStateDTOToAddressState(AddressStateDTO addressStateDTO) {
        if ( addressStateDTO == null ) {
            return null;
        }

        AddressState.AddressStateBuilder addressState = AddressState.builder();

        addressState.stateCode( addressStateDTO.getStateCode() );
        addressState.stateName( addressStateDTO.getStateName() );

        return addressState.build();
    }

    protected AddressPostalCode addressPostalCodeDTOToAddressPostalCode(AddressPostalCodeDTO addressPostalCodeDTO) {
        if ( addressPostalCodeDTO == null ) {
            return null;
        }

        AddressPostalCode.AddressPostalCodeBuilder addressPostalCode = AddressPostalCode.builder();

        addressPostalCode.zipCode( addressPostalCodeDTO.getZipCode() );
        addressPostalCode.zipPlus4( addressPostalCodeDTO.getZipPlus4() );
        addressPostalCode.postalCode( addressPostalCodeDTO.getPostalCode() );

        return addressPostalCode.build();
    }

    protected AddressLines addressLinesDTOToAddressLines(AddressLinesDTO addressLinesDTO) {
        if ( addressLinesDTO == null ) {
            return null;
        }

        AddressLines.AddressLinesBuilder addressLines = AddressLines.builder();

        addressLines.addressDeliveryLine1( addressLinesDTO.getAddressDeliveryLine1() );
        addressLines.addressDeliveryLine2( addressLinesDTO.getAddressDeliveryLine2() );
        addressLines.addressCity( addressLinesDTO.getAddressCity() );
        addressLines.addressState( addressStateDTOToAddressState( addressLinesDTO.getAddressState() ) );
        addressLines.addressPostalCode( addressPostalCodeDTOToAddressPostalCode( addressLinesDTO.getAddressPostalCode() ) );

        return addressLines.build();
    }

    protected Address addressDTOToAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.addressTypeCode( addressTypeCodeDTOToAddressTypeCode( addressDTO.getAddressTypeCode() ) );
        address.primaryAddressIndicator( addressDTO.getPrimaryAddressIndicator() );
        address.addressLines( addressLinesDTOToAddressLines( addressDTO.getAddressLines() ) );

        return address.build();
    }

    protected BankAccountIdentifier bankAccountIdentifierDTOToBankAccountIdentifier(BankAccountIdentifierDTO bankAccountIdentifierDTO) {
        if ( bankAccountIdentifierDTO == null ) {
            return null;
        }

        BankAccountIdentifier.BankAccountIdentifierBuilder bankAccountIdentifier = BankAccountIdentifier.builder();

        bankAccountIdentifier.bankAccountNumber( bankAccountIdentifierDTO.getBankAccountNumber() );
        bankAccountIdentifier.bankRoutingNumber( bankAccountIdentifierDTO.getBankRoutingNumber() );

        return bankAccountIdentifier.build();
    }

    protected BankAccountTypeCode bankAccountTypeCodeDTOToBankAccountTypeCode(BankAccountTypeCodeDTO bankAccountTypeCodeDTO) {
        if ( bankAccountTypeCodeDTO == null ) {
            return null;
        }

        BankAccountTypeCode.BankAccountTypeCodeBuilder bankAccountTypeCode = BankAccountTypeCode.builder();

        bankAccountTypeCode.code( bankAccountTypeCodeDTO.getCode() );
        bankAccountTypeCode.codeName( bankAccountTypeCodeDTO.getCodeName() );

        return bankAccountTypeCode.build();
    }

    protected BankAccountStatus bankAccountStatusDTOToBankAccountStatus(BankAccountStatusDTO bankAccountStatusDTO) {
        if ( bankAccountStatusDTO == null ) {
            return null;
        }

        BankAccountStatus.BankAccountStatusBuilder bankAccountStatus = BankAccountStatus.builder();

        bankAccountStatus.code( bankAccountStatusDTO.getCode() );
        bankAccountStatus.codeName( bankAccountStatusDTO.getCodeName() );

        return bankAccountStatus.build();
    }

    protected ContributionBankAccount contributionBankAccountDTOToContributionBankAccount(ContributionBankAccountDTO contributionBankAccountDTO) {
        if ( contributionBankAccountDTO == null ) {
            return null;
        }

        ContributionBankAccount.ContributionBankAccountBuilder contributionBankAccount = ContributionBankAccount.builder();

        contributionBankAccount.bankAccountIdentifier( bankAccountIdentifierDTOToBankAccountIdentifier( contributionBankAccountDTO.getBankAccountIdentifier() ) );
        contributionBankAccount.bankAccountTypeCode( bankAccountTypeCodeDTOToBankAccountTypeCode( contributionBankAccountDTO.getBankAccountTypeCode() ) );
        contributionBankAccount.bankName( contributionBankAccountDTO.getBankName() );
        contributionBankAccount.bankSequenceNumber( contributionBankAccountDTO.getBankSequenceNumber() );
        contributionBankAccount.bankAccountNickName( contributionBankAccountDTO.getBankAccountNickName() );
        contributionBankAccount.bankAccountStatus( bankAccountStatusDTOToBankAccountStatus( contributionBankAccountDTO.getBankAccountStatus() ) );
        contributionBankAccount.bankAccountOperation( contributionBankAccountDTO.getBankAccountOperation() );

        return contributionBankAccount.build();
    }

    protected List<ContributionBankAccount> contributionBankAccountDTOListToContributionBankAccountList(List<ContributionBankAccountDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ContributionBankAccount> list1 = new ArrayList<ContributionBankAccount>( list.size() );
        for ( ContributionBankAccountDTO contributionBankAccountDTO : list ) {
            list1.add( contributionBankAccountDTOToContributionBankAccount( contributionBankAccountDTO ) );
        }

        return list1;
    }

    protected EmployerHDHPPolicy employerHDHPPolicyDTOToEmployerHDHPPolicy(EmployerHDHPPolicyDTO employerHDHPPolicyDTO) {
        if ( employerHDHPPolicyDTO == null ) {
            return null;
        }

        EmployerHDHPPolicy.EmployerHDHPPolicyBuilder employerHDHPPolicy = EmployerHDHPPolicy.builder();

        return employerHDHPPolicy.build();
    }

    protected PayerDetail payerDetailDTOToPayerDetail(PayerDetailDTO payerDetailDTO) {
        if ( payerDetailDTO == null ) {
            return null;
        }

        PayerDetail.PayerDetailBuilder payerDetail = PayerDetail.builder();

        payerDetail.branchNumber( payerDetailDTO.getBranchNumber() );
        payerDetail.payerName( payerDetailDTO.getPayerName() );

        return payerDetail.build();
    }

    protected Employer employerDTOToEmployer(EmployerDTO employerDTO) {
        if ( employerDTO == null ) {
            return null;
        }

        Employer.EmployerBuilder employer = Employer.builder();

        employer.employerGroupId( employerDTO.getEmployerGroupId() );
        employer.employerName( employerDTO.getEmployerName() );
        employer.employerSetupStatus( employerDTO.getEmployerSetupStatus() );
        employer.address( addressDTOToAddress( employerDTO.getAddress() ) );
        employer.contributionBankAccounts( contributionBankAccountDTOListToContributionBankAccountList( employerDTO.getContributionBankAccounts() ) );
        employer.employerHDHPPolicy( employerHDHPPolicyDTOToEmployerHDHPPolicy( employerDTO.getEmployerHDHPPolicy() ) );
        employer.payerDetail( payerDetailDTOToPayerDetail( employerDTO.getPayerDetail() ) );
        employer.empBankFeeScheduleName( employerDTO.getEmpBankFeeScheduleName() );

        return employer.build();
    }

    protected List<Employer> employerDTOListToEmployerList(List<EmployerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Employer> list1 = new ArrayList<Employer>( list.size() );
        for ( EmployerDTO employerDTO : list ) {
            list1.add( employerDTOToEmployer( employerDTO ) );
        }

        return list1;
    }

    protected Status statusDTOToStatus(StatusDTO statusDTO) {
        if ( statusDTO == null ) {
            return null;
        }

        Status.StatusBuilder status = Status.builder();

        status.statusCode( statusDTO.getStatusCode() );
        status.severity( statusDTO.getSeverity() );
        status.statusDescription( statusDTO.getStatusDescription() );

        return status.build();
    }
}
