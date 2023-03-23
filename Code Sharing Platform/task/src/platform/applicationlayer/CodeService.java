package platform.applicationlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.datalayer.CodeEntity;
import platform.datalayer.CodeRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CodeService {
    private final CodeRepository codeRepository;
    private final Mapper mapper;

    @Autowired
    public CodeService(CodeRepository codeRepository, Mapper mapper) {
        this.codeRepository = codeRepository;
        this.mapper = mapper;
    }

    // API requests

    public IdDTO saveNewCodeAPI(CodeDTO dto) {
        CodeEntity codeEntity = mapper.mapToEntity(dto);
        String id = codeRepository.save(codeEntity).getId();
        return new IdDTO(id);
    }

    public CodeDTO getCodeAPI(String id) {
        CodeEntity codeEntity = codeRepository.findReferenceById(id);
        // Check limitation of views
        checkNumberOfViews(codeEntity);

        // Check limitation viewing time
        checkViewingTime(codeEntity);

        // Save changes in limitations counters
        codeRepository.save(codeEntity);
        return mapper.mapToDto(codeEntity);
    }

    public List<CodeDTO> listOfLatestCodes() {
        List<CodeEntity> codeEntityList = codeRepository.findTop10ByMaxTimeForViewEqualsAndMaxViewsEqualsOrderByDateDesc(0,0);
        return codeEntityList.stream()
                .map(codeEntity -> mapper.mapToDto(codeEntity))
                .toList();
    }

    private void deleteExpiredCode(CodeEntity codeEntity) {
        codeRepository.delete(codeEntity);
    }

    private void checkNumberOfViews(CodeEntity codeEntity) {
        if (codeEntity.getMaxViews() == 0) {
            return;
        }
        if (codeEntity.getReminingViews() > 0) {
            long views = codeEntity.getReminingViews();
            codeEntity.setReminingViews(views - 1);
        } else {
            deleteExpiredCode(codeEntity);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private void checkViewingTime(CodeEntity codeEntity) {
        if (codeEntity.getMaxTimeForView() == 0) {
            return;
        }
        LocalDateTime timeOfCreateEntity = codeEntity.getDate();
        Duration duration = Duration.between(timeOfCreateEntity, LocalDateTime.now());
        if (codeEntity.getMaxTimeForView() > duration.toSeconds()) {
            codeEntity.setRemainingTime(codeEntity.getMaxTimeForView() - duration.toSeconds());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

