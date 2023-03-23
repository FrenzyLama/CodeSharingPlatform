package platform.presentationlayer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.applicationlayer.CodeDTO;
import platform.applicationlayer.CodeService;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    CodeService codeService;

    @GetMapping(value = "/code/new")
    public String saveCode() {
        return "add_code";
    }

    @GetMapping(value = "/code/{id}")
    public String getCode(@PathVariable String id, Model model) {
        CodeDTO codeDTO = codeService.getCodeAPI(id);
        System.out.println("Web date = " + codeDTO.getDate() + "\n " + id);
        model.addAttribute("code", codeDTO);
        return "code";
    }

    @GetMapping(value = "/code/latest")
    public String getLatestCodes(Model model) {
        List<CodeDTO> listOfCodes = codeService.listOfLatestCodes();
        model.addAttribute("codes", listOfCodes);
        return "codes_list";
    }
}
