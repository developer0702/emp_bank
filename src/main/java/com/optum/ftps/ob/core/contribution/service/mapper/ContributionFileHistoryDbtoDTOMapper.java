package com.optum.ftps.ob.core.contribution.service.mapper;

import static com.optum.ftps.ob.core.contribution.util.DbUtil.getString;
import static com.optum.ftps.ob.core.contribution.util.DbUtil.getStringWithLengthCheck;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryRecordsDTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ContributionFileHistoryDbtoDTOMapper {
    public List<ContributionFileHistoryRecordsDTO> mapToContributionFileHistoryResponseDTO(
            List<Object[]> dbData) {

        var contributionFileHistoryRecords = new ArrayList<ContributionFileHistoryRecordsDTO>();
        if (Objects.isNull(dbData) || dbData.isEmpty()) {
            return new ArrayList<>();
        }

        for (Object[] data : dbData) {

            var contributionFileHistoryRecordsDTO =
                    new ContributionFileHistoryRecordsDTO(
                            getString(data, 0), // contributionFilePortalId
                            getString(data, 1), // effectiveTransactionDate
                            getString(data, 2), // statusCode
                            getString(data, 3), // submittedMethodCode
                            getString(data, 4), // totalFileContributionAmount
                            getString(data, 5), // originalFileName
                            getString(data, 6), // newFileName
                            getString(data, 7), // friendlyFileName
                            getString(data, 8), // submittedByName
                            getStringWithLengthCheck(data, 9, 19), // submittedTimestamp
                            getString(data, 10), // approvedByName
                            getStringWithLengthCheck(data, 11, 19), // approvedTimestamp
                            getString(data, 12), // denyByName
                            getStringWithLengthCheck(data, 13, 19), // denyTimestamp
                            getString(data, 14), // denyCommitText
                            getStringWithLengthCheck(data, 15, 19), // processedTimestamp
                            getString(data, 16), // customerPolicyNumber
                            getStringWithLengthCheck(data, 17, 19), // approverEmailAndTimestamp
                            getString(data, 18), // customerFundId
                            getString(data, 19), // bankName
                            getString(data, 20)); // bankNickName
            contributionFileHistoryRecords.add(contributionFileHistoryRecordsDTO);
        }

        return contributionFileHistoryRecords;
    }
}
