package ru.openschool.aop.backend.service;//package ru.openschool.aspect.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.access.ActionId;
import ru.openschool.aop.backend.access.ResourceId;
import ru.openschool.aop.backend.annotation.TrackAsyncTime;
import ru.openschool.aop.backend.annotation.TrackTime;
import ru.openschool.aop.backend.annotation.UserAccess;
import ru.openschool.aop.backend.api.AdminApiDelegate;
import ru.openschool.aop.backend.api.AnimalApi;
import ru.openschool.aop.backend.api.AnimalApiDelegate;
import ru.openschool.aop.backend.api.ApiUtil;
import ru.openschool.aop.backend.dto.AnimalInDto;
import ru.openschool.aop.backend.dto.AnimalViewDto;
import ru.openschool.aop.backend.dto.RefRecordDto;
import ru.openschool.aop.backend.dto.RefRecordInDto;
import ru.openschool.aop.backend.exception.ValidateException;
import ru.openschool.aop.backend.mapper.AnimalMapper;
import ru.openschool.aop.backend.mapper.RoleMapper;
import ru.openschool.aop.backend.model.Animal;
import ru.openschool.aop.backend.model.AnimalType;
import ru.openschool.aop.backend.model.AnimalView;
import ru.openschool.aop.backend.repository.AnimalRepository;
import ru.openschool.aop.backend.repository.AnimalTypeRepository;
import ru.openschool.aop.backend.repository.AnimalViewRepository;
import ru.openschool.aop.backend.repository.RoleRepository;
import ru.openschool.aop.backend.util.CoreUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для выполнения функций rest сервисов (GET, POST, PATCH, DELETE)
 *
 * @author Аркадий Тихонов
 */
@Service
@RequiredArgsConstructor
public class AnimalApiService implements AnimalApiDelegate {
    private final AnimalRepository animalRepository;

    private final AnimalViewRepository animalViewRepository;

    private final AnimalTypeRepository animalTypeRepository;

    private final AnimalMapper animalMapper;

    /**
     * POST /animal/animals : Добавление животного
     *
     * @param animalInDto Данные о животном
     * @return Животное (status code 200)
     */
    @Override
    public ResponseEntity<AnimalViewDto> addAnimal(AnimalInDto animalInDto) {
        Animal animal = animalMapper.fromDtoToEntity(animalInDto);
        animalRepository.save(animal);

        AnimalViewDto result = animalMapper
                .fromViewToDto(animalViewRepository.findById(animal.getId()).get());
        return ResponseEntity.ok(result);
    }

    /**
     * POST /animal/animalTypes : Добавление типа животных
     *
     * @param refRecordInDto Данные о типе животного
     * @return Тип животного (status code 200)
     */
    @Override
    public ResponseEntity<RefRecordDto> addAnimalType(RefRecordInDto refRecordInDto) {

        AnimalType animalType = animalMapper.fromDtoToEntity(refRecordInDto);
        animalTypeRepository.save(animalType);

        RefRecordDto result = animalMapper
                .fromEntityToDto(animalTypeRepository.findById(animalType.getId()).get());
        return ResponseEntity.ok(result);

    }

    /**
     * GET /animal/animals/{id} : Выборка животного
     *
     * @param id Идентификатор (required)
     * @return Тип животного (status code 200)
     * @see AnimalApi#getAnimal
     */
    @Override
    public ResponseEntity<AnimalViewDto> getAnimal(Long id) {
        if (animalViewRepository.findById(id).isPresent()) {
            AnimalViewDto result = animalMapper
                    .fromViewToDto(animalViewRepository.findById(id).get());
            return ResponseEntity.ok(result);
        } else {
            throw ValidateException.notFound("Животное", id);
        }
    }

    /**
     * GET /animal/animalTypes/{id} : Выборка типа животного
     *
     * @param id Идентификатор (required)
     * @return Тип животного (status code 200)
     */
    @Override
    public ResponseEntity<RefRecordDto> getAnimalType(Long id) {
        if (animalTypeRepository.findById(id).isPresent()) {
            RefRecordDto result = animalMapper
                    .fromEntityToDto(animalTypeRepository.findById(id).get());
            return ResponseEntity.ok(result);
        } else {
            throw ValidateException.notFound("Тип животного", id);
        }

    }

    /**
     * GET /animal/animalTypes : Выборка типов животных
     *
     * @return Список типов животных (status code 200)
     */
    @Override
    @TrackAsyncTime
    public ResponseEntity<List<RefRecordDto>> getAnimalTypes() {

        List<RefRecordDto> result =
                animalTypeRepository
                        .findAll()
                        .stream()
                        .map(animalMapper::fromEntityToDto)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    /**
     * GET /animal/animals : Выборка животных
     *
     * @return Список животных (status code 200)
     */
    @Override
    @TrackTime
    public ResponseEntity<List<AnimalViewDto>> getAnimals() {
        List<AnimalViewDto> result =
                animalViewRepository
                        .findAll()
                        .stream()
                        .map(animalMapper::fromViewToDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    /**
     * PATCH /animal/animals/{id} : Изменение животного
     *
     * @param id Идентификатор (required)
     * @param animalInDto Данные о животном
     * @return Животное (status code 200)
     */
    @Override
    @UserAccess(action = ActionId.FULL, resource = ResourceId.ANIMAL)
    @TrackTime
    public ResponseEntity<AnimalViewDto> modifyAnimal(Long id,
                                                      AnimalInDto animalInDto) {
        if (animalRepository.findById(id).isPresent()) {
            Animal entity = animalRepository.findById(id).get();
            Animal entityNew = animalMapper.fromDtoToEntity(animalInDto);
            CoreUtil.patch(entityNew, entity);
            animalRepository.save(entity);
            AnimalViewDto result = animalMapper
                    .fromViewToDto(animalViewRepository.findById(entity.getId()).get());
            return ResponseEntity.ok(result);
        } else {
            throw ValidateException.notFound("Животное", id);
        }
    }

    /**
     * PATCH /animal/animalTypes/{id} : Изменение типа животных
     *
     * @param id Идентификатор (required)
     * @param refRecordInDto Данные о типе животного
     * @return Тип животного (status code 200)
     */
    @Override
    public ResponseEntity<RefRecordDto> modifyAnimalType(Long id,
                                                         RefRecordInDto refRecordInDto) {
        if (animalTypeRepository.findById(id).isPresent()) {
            AnimalType entity = animalTypeRepository.findById(id).get();
            AnimalType entityNew = animalMapper.fromDtoToEntity(refRecordInDto);
            CoreUtil.patch(entityNew, entity);
            animalTypeRepository.save(entity);
            RefRecordDto result = animalMapper
                    .fromEntityToDto(animalTypeRepository.findById(entity.getId()).get());
            return ResponseEntity.ok(result);
        } else {
            throw ValidateException.notFound("Животное", id);
        }
    }
}

