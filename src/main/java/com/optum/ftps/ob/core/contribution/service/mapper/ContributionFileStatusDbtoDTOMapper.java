package com.optum.ftps.ob.core.contribution.service.mapper;

import static com.optum.ftps.ob.core.contribution.util.DbUtil.getString;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDbDTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ContributionFileStatusDbtoDTOMapper {
    public List<ContributionFileStatusDbDTO> map(List<Object[]> dbData) {

        var result = new ArrayList<ContributionFileStatusDbDTO>();
        if (Objects.isNull(dbData) || dbData.isEmpty()) {
            return result;
        }

        for (Object[] data : dbData) {
            var dto =
                    ContributionFileStatusDbDTO.builder()
                            .contributionFileId(getString(data, 0))
                            .statusCode(getString(data, 1))
                            .build();
            result.add(dto);
        }

        return result;
    }
}
