package com.optum.ftps.ob.core.contribution.mapper;

import static com.optum.ftps.ob.core.contribution.util.DbUtil.getString;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContributionFileNamesDbToDTOMapper {
    public List<ContributionFileNamesDTO> mapContributionFileNamesDbToDTO(List<Object[]> rows) {
        var result = new ArrayList<ContributionFileNamesDTO>();

        if (!rows.isEmpty()) {
            for (Object[] row : rows) {
                var contributionFileNamesDTO =
                        new ContributionFileNamesDTO(
                                getString(row, 0), // ContributionFilePortalId
                                getString(row, 1), // EffectiveTransactionDate
                                getString(row, 2), // ProcessTimestamp
                                getString(row, 3), // OriginalFileName
                                getString(row, 4), // NewFileName
                                getString(row, 5)); // FriendlyFileName

                result.add(contributionFileNamesDTO);
            }
        }

        return result;
    }
}
