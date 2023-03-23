package platform.presentationlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.applicationlayer.CodeDTO;
import platform.applicationlayer.CodeService;
import platform.applicationlayer.IdDTO;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    CodeService codeService;

    @PostMapping("/api/code/new")
    public @ResponseBody ResponseEntity<IdDTO> saveCode(@RequestBody CodeDTO codeDTO) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "application/json");

        IdDTO idDTO = codeService.saveNewCodeAPI(codeDTO);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(idDTO);
    }

    @GetMapping("/api/code/{id}")
    public @ResponseBody ResponseEntity<CodeDTO> getCode(@PathVariable String id) {
        CodeDTO codeDTO = codeService.getCodeAPI(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeDTO);
    }

    @GetMapping("/api/code/latest")
    public @ResponseBody ResponseEntity<List<CodeDTO>> getTenLatest() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeService.listOfLatestCodes());
    }
}
