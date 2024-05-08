package ru.openschool.aop.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.openschool.aop.backend.model.Access;

import java.util.List;


public interface AccessRepository extends JpaRepository<Access, Long> {
    @Query(value = "SELECT * FROM access\n" +
            "JOIN resource r ON access.resource_id = r.id\n" +
            "JOIN action a ON access.action_id = a.id\n" +
            "WHERE role_id=?1 AND r.id=?2 AND a.id=?3", nativeQuery = true)
    List<Access> findByRoleAndResourceIdAndActionId(
            Long roleId,
            Long resourceId,
            Long actionId
    );

    /**
     * Выборка всех записей
     * @param roleId - Идентифкатор роли
     * @return - список записей
     */
    public List<Access> findAllByRoleId(Long roleId);

}
