package ru.openschool.aop.backend.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.openschool.aop.backend.dto.AnimalViewDto;
import ru.openschool.aop.backend.dto.RefRecordDto;
import ru.openschool.aop.backend.exception.ValidateException;
import ru.openschool.aop.backend.mapper.AnimalMapper;
import ru.openschool.aop.backend.model.Animal;
import ru.openschool.aop.backend.model.AnimalType;
import ru.openschool.aop.backend.model.AnimalView;
import ru.openschool.aop.backend.repository.AnimalTypeRepository;
import ru.openschool.aop.backend.repository.AnimalViewRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(MockitoExtension.class)
public class AnimalApiServiceTest {

    @Mock
    AnimalTypeRepository animalTypeRepository;

    @Mock
    AnimalViewRepository animalViewRepository;

    @InjectMocks
    AnimalApiService animalApiService;

    @BeforeEach
    public void initMocks() {
        ReflectionUtils.setField(
                ReflectionUtils.findRequiredField(AnimalApiService.class, "animalTypeRepository"),
                animalApiService,
                animalTypeRepository
        );
        ReflectionUtils.setField(
                ReflectionUtils.findRequiredField(AnimalApiService.class, "animalMapper"),
                animalApiService,
                new AnimalMapper(animalTypeRepository)
        );
        ReflectionUtils.setField(
                ReflectionUtils.findRequiredField(AnimalApiService.class, "animalViewRepository"),
                animalApiService,
                animalViewRepository
        );
    }

    @Test
    @DisplayName("Выбор типа животного")
    void getAnimalTypeSuccess() {
        Long id = 1l;
        String name = "Зверь";
        // Ожидаемый RefRecordDto, олпределяющийся в  animalApiService.getAnimalType
        RefRecordDto expectedResult = getTestRefRecordDto(id, name);
        // Моск восвращаемого репозиторием AnimalType
        AnimalType animalType = getTestAnimalType(id, name);

        when(animalTypeRepository.findById(id)).thenReturn(Optional.of(animalType));
        ResponseEntity<RefRecordDto> testResult = animalApiService.getAnimalType(id);

        assertEquals(ResponseEntity.ok(expectedResult), testResult);
    }

    @DisplayName("Выбор несуществующего типа животного")
    @Test
    void getAnimalTypeNotFound() {
        Long notExistedId = 99999l;
        String ecpectedMsg = "Не найден объект 'Тип животного' с идентификатором = 99999";
        Exception thrown = assertThrows(ValidateException.class, () -> {
            animalApiService.getAnimalType(notExistedId);
        });
        assertEquals(ecpectedMsg, thrown.getMessage());
    }

    @Test
    @DisplayName("Выбор животного")
    void getAnimalSuccess() {
        Long id = 7l;
        String name = "Медведь";
        Long animalTypeId = 1l;
        Long legs = 4l;
        String animalTypeName = "Зверь";
        // Ожидаемый AnimalViewDto, олпределяющийся в  animalApiService.getAnimalType
        AnimalViewDto expectedResult = getTestAnimalDto(id, name,legs, animalTypeId, animalTypeName );
        // Моск восвращаемого репозиторием Animal
        AnimalView animalView = getTestAnimal(id, name,legs, animalTypeId, animalTypeName );

        when(animalViewRepository.findById(id)).thenReturn(Optional.of(animalView));
        ResponseEntity<AnimalViewDto> testResult = animalApiService.getAnimal(id);

        assertEquals(ResponseEntity.ok(expectedResult), testResult);
    }

    @DisplayName("Выбор несуществующего животного")
    @Test
    void getAnimalNotFound() {
        Long notExistedId = 99999l;
        String ecpectedMsg = "Не найден объект 'Животное' с идентификатором = 99999";
        Exception thrown = assertThrows(ValidateException.class, () -> {
            animalApiService.getAnimal(notExistedId);
        });
        assertEquals(ecpectedMsg, thrown.getMessage());
    }


    private AnimalType getTestAnimalType(Long id, String name) {
        AnimalType at = new AnimalType();
        at.setId(id);
        at.setName(name);
        return at;
    }

    private RefRecordDto getTestRefRecordDto(Long id, String name) {
        RefRecordDto r = new RefRecordDto();
        r.setId(id);
        r.setName(name);
        return r;
    }

    private AnimalView getTestAnimal(Long id, String name, Long legs, Long animalTypeId, String animalTypeName) {
        AnimalView a = new AnimalView();
        a.setId(id);
        a.setName(name);
        a.setLegs(legs);
        a.setAnimalTypeId(animalTypeId);
        a.setAnimalTypeName(animalTypeName);
        return a;
    }

    private AnimalViewDto getTestAnimalDto(Long id, String name, Long legs, Long animalTypeId, String animalTypeName) {
        AnimalViewDto a = new AnimalViewDto();
        a.setId(id);
        a.setName(name);
        a.setLegs(legs);
        a.setAnimalTypeId(animalTypeId);
        a.setAnimalTypeName(animalTypeName);
        return a;
    }
}
