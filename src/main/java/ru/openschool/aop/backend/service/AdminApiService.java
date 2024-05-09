package ru.openschool.aop.backend.service;//package ru.openschool.aspect.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.openschool.aop.backend.dto.TrackTimeViewDto;
import ru.openschool.aop.backend.mapper.RoleMapper;
import ru.openschool.aop.backend.mapper.TrackTimeMapper;
import ru.openschool.aop.backend.repository.RoleRepository;
import ru.openschool.aop.backend.api.AdminApiDelegate;
import ru.openschool.aop.backend.dto.RefRecordDto;
import ru.openschool.aop.backend.repository.TrackTimeViewRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для выполнения функций rest сервисов (GET, POST, PATCH, DELETE)
 *
 * @author Аркадий Тихонов
 */
@Service
@RequiredArgsConstructor
public class AdminApiService implements AdminApiDelegate {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final TrackTimeMapper trackTimeMapper;
    private final TrackTimeViewRepository trackTimeViewRepository;


//    Справочники

    /**
     * Список ролей
     */
    @Override
    public ResponseEntity<List<RefRecordDto>> getRoles() {
        List<RefRecordDto> result =
                roleRepository
                        .findAll()
                        .stream()
                        .map(roleMapper::fromEntityToDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    /**
     * GET /admin/trackTimeMetrics : Выборка статистики выполнения методов
     *
     * @return Статистика выполнения методов (status code 200)
     */
    @Override
    public ResponseEntity<List<TrackTimeViewDto>> getTrackTimeMetrics() {
        List<TrackTimeViewDto> result =
                trackTimeViewRepository
                        .findAll()
                        .stream()
                        .map(trackTimeMapper::fromViewToDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);

    }
}

