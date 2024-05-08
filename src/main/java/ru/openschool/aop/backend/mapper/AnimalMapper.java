package ru.openschool.aop.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.dto.AnimalInDto;
import ru.openschool.aop.backend.dto.AnimalViewDto;
import ru.openschool.aop.backend.dto.RefRecordDto;
import ru.openschool.aop.backend.dto.RefRecordInDto;
import ru.openschool.aop.backend.model.Animal;
import ru.openschool.aop.backend.model.AnimalType;
import ru.openschool.aop.backend.model.AnimalView;
import ru.openschool.aop.backend.repository.AnimalTypeRepository;
import ru.openschool.aop.backend.util.CoreUtil;

/**
 * Маппинг:
 * -  между view и dto rest сервиса
 * -  между dto rest сервиса и entity сущности
 */
@Service
@RequiredArgsConstructor
public class AnimalMapper {

    private final AnimalTypeRepository animalTypeRepository;

    /**
     *
     * Маппинг из entity в DTO
     *
     * @param entity - строка из entity
     * @return Данные в структуре DTO
     */
    public RefRecordDto fromEntityToDto(AnimalType entity) {
        RefRecordDto result = new RefRecordDto();
        CoreUtil.patch(entity, result);
        return result;
    }

    /**
     *
     * Маппинг из view в DTO
     *
     * @param view - строка из view
     * @return Данные в структуре DTO
     */
    public AnimalViewDto fromViewToDto(AnimalView view) {
        AnimalViewDto result = new AnimalViewDto();
        CoreUtil.patch(view, result);
        return result;
    }

    /**
     * Маппингиз DTO в Entity
     *
     * @param dto - строка из DTO
     * @return данные в структуре Entity
     */
    public AnimalType fromDtoToEntity(RefRecordInDto dto) {
        AnimalType result = new AnimalType();
        CoreUtil.patch(dto, result);

        return result;
    }

    /**
     * Маппингиз DTO в Entity
     *
     * @param dto - строка из DTO
     * @return данные в структуре Entity
     */
    public Animal fromDtoToEntity(AnimalInDto dto) {
        Animal result = new Animal();
        CoreUtil.patch(dto, result);
        AnimalType animalType = animalTypeRepository.findById(dto.getAnimalTypeId()).get();
        result.setAnimalType(animalType);
        return result;
    }

}
