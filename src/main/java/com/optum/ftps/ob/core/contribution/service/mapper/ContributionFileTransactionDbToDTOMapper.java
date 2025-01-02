package com.optum.ftps.ob.core.contribution.service.mapper;

import static com.optum.ftps.ob.core.contribution.util.DbUtil.getString;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileItemDTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContributionFileTransactionDbToDTOMapper {
    public List<ContributionFileItemDTO> mapToContributionFileTransactionResponseDTO(
            List<Object[]> dbData) {
        var contributionFileTransactions = new ArrayList<ContributionFileItemDTO>();
        if (!dbData.isEmpty()) {
            for (Object[] data : dbData) {
                var contributionFileItem =
                        new ContributionFileItemDTO(
                                getString(data, 0), // contributionFilePortalId
                                getString(data, 1), // accountNumber
                                getString(data, 2), // transactionDescription
                                getString(data, 3), // ssn
                                getString(data, 4), // crAmt
                                getString(data, 5), // contributionTypeName
                                getString(data, 6)); // portalContributionCommitText
                contributionFileTransactions.add(contributionFileItem);
            }
        }
        return contributionFileTransactions;
    }
}
