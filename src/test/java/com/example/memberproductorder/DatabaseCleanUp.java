package com.example.memberproductorder;

import com.google.common.base.CaseFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 데이터베이스 정리 작업을 수행하는 Spring Bean으로, 애플리케이션 시작시 초기화되어 실행됨
 */
@Component
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        // 엔티티 매니저에서 엔티티 정보를 가져와서 테이블 이름 목록을 만듭니다.
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        tableNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e -> e.getJavaType().getAnnotation(Table.class).name())
                .collect(Collectors.toList());

        // 테이블 어노테이션이 없는 엔티티를 위해 엔티티 이름을 가져와서 테이블 이름 목록에 추가합니다.
        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && !hasTableAnnotation(e))
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .toList();
        tableNames.addAll(entityNames);
    }

    // 엔티티가 @Entity 어노테이션을 가지고 있는지 검사합니다.
    private boolean isEntity(EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Entity.class);
    }

    // 엔티티가 @Table 어노테이션을 가지고 있는지 검사합니다.
    private boolean hasTableAnnotation(EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    // 트랜잭션 내에서 데이터베이스를 정리합니다.
    @Transactional
    public void execute() {
        // 엔티티 매니저 캐시를 플러시합니다.
        entityManager.flush();

        // 외래키 제약 조건을 무시하도록 설정합니다.
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        // 모든 테이블을 잘라내고 ID 컬럼을 1부터 다시 시작하도록 설정합니다.
        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        }

        // 외래키 제약 조건을 다시 적용합니다.
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
