package platform.applicationlayer;

import org.springframework.stereotype.Component;
import platform.datalayer.CodeEntity;

import java.time.format.DateTimeFormatter;

@Component
public class Mapper {
    public CodeEntity mapToEntity(CodeDTO codeDTO) {
        CodeEntity entity = new CodeEntity();
        entity.setCode(codeDTO.getCode());
        entity.setMaxTimeForView(codeDTO.getTime());
        entity.setRemainingTime(codeDTO.getTime());
        entity.setMaxViews(codeDTO.getViews());
        entity.setReminingViews(codeDTO.getViews());
        return entity;
    }

    public CodeDTO mapToDto(CodeEntity codeEntity) {
        CodeDTO codeDTO = new CodeDTO();
        codeDTO.setCode(codeEntity.getCode());
        if (codeEntity.getMaxViews() > 0) {
            codeDTO.setViews(codeEntity.getReminingViews());
            codeDTO.setViewsRestriction(true);
        } else {
            codeDTO.setViews(0L);
            codeDTO.setViewsRestriction(false);
        }
        if (codeEntity.getMaxTimeForView() > 0) {
            codeDTO.setTime(codeEntity.getRemainingTime());
            codeDTO.setTimeRestriction(true);
        } else {
            codeDTO.setTime(0L);
            codeDTO.setTimeRestriction(false);
        }
        // Date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSSSS");
        codeDTO.setDate(codeEntity.getDate().format(formatter));
        return codeDTO;
    }
}

//        "yyyy/MM/dd HH:mm:ss:SSSSSS"
