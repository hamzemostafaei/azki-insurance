package com.azki.insurance.reservation.service.domain.api.query;

import com.azki.insurance.domain.api.dto.BaseSearchCriteria;
import com.azki.insurance.domain.api.dto.query.condition.Condition;
import com.azki.insurance.domain.api.dto.query.condition.ConditionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserCriteriaDTO extends BaseSearchCriteria {

    @Condition(type = ConditionTypeEnum.EQUAL, fieldName = "userId")
    private Integer userIdEquals;

    @Condition(type = ConditionTypeEnum.EQUAL, fieldName = "username")
    private String usernameEquals;

    @Condition(type = ConditionTypeEnum.EQUAL, fieldName = "email")
    private String emailEquals;
}
