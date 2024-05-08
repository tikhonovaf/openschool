package ru.openschool.aop.backend.mapper;

import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.dto.TrackTimeViewDto;
import ru.openschool.aop.backend.model.TrackTimeStoreView;
import ru.openschool.aop.backend.util.CoreUtil;

/**
 * Маппинг:
 * -  между view и dto rest сервиса
 * -  между dto rest сервиса и entity сущности
 */
@Service
public class TrackTimeMapper {

    /**
     *
     * Маппинг из View в DTO
     *
     * @param view - строка из view
     * @return Данные в структуре DTO
     */
    public TrackTimeViewDto fromViewToDto(TrackTimeStoreView view) {
        TrackTimeViewDto result = new TrackTimeViewDto();
        CoreUtil.patch(view, result);
        return result;
    }

}
