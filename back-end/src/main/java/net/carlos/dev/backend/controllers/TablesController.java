package net.carlos.dev.backend.controllers;

import net.carlos.dev.backend.dto.TablesDTO;
import net.carlos.dev.backend.service.impl.TablesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1/")
public class TablesController {
    @Autowired
    private TablesServiceImpl tablesService;

    @PostMapping("/table")
    public TablesDTO save(@RequestBody TablesDTO tablesDTO) {
        System.out.println(tablesDTO.getDescription());
        return tablesService.save(tablesDTO);
    }
    @PutMapping("/table/update")
    public TablesDTO update(@RequestBody TablesDTO tablesDTO) {
        return tablesService.save(tablesDTO);
    }

    @DeleteMapping("/table/delete/{id}")
    public void delete(@PathVariable Long id) {
        tablesService.delete(id);
    }

    @GetMapping("/table/{id}")
    public TablesDTO findById(@PathVariable Long id) {
        return tablesService.findById(id);
    }

    @GetMapping("/tables")
    public List<TablesDTO> findAll() {
        return tablesService.findAll();
    }
}
