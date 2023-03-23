package platform.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CodeRepository extends JpaRepository<CodeEntity, Long> {
    CodeEntity findReferenceById(String id);
    List<CodeEntity> findTop10ByMaxTimeForViewEqualsAndMaxViewsEqualsOrderByDateDesc(long maxTimeForView, long maxViews);

    //    List<CodeEntity> findFirst10ByOrderByDateDesc();
}
