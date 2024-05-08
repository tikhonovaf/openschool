package ru.openschool.aop.backend.mapper;

import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.model.Role;
import ru.openschool.aop.backend.util.CoreUtil;
import ru.openschool.aop.backend.dto.RefRecordDto;

/**
 * Маппинг:
 * -  между entity и dto rest сервиса
 */
@Service
public class RoleMapper {

    /**
     *
     * Маппинг из entity в DTO
     *
     * @param entity - строка из entity
     * @return Данные в структуре DTO
     */
    public RefRecordDto fromEntityToDto(Role entity) {
        RefRecordDto result = new RefRecordDto();
        CoreUtil.patch(entity, result);
        return result;
    }

}
