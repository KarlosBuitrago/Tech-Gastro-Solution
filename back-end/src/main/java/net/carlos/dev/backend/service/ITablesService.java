package net.carlos.dev.backend.service;

import net.carlos.dev.backend.dto.TablesDTO;

import java.util.List;

public interface ITablesService {
    List<TablesDTO> findAll();
    TablesDTO findById(Long id);
    TablesDTO save(TablesDTO tablesDTO);
    boolean delete(Long id);
    TablesDTO update(TablesDTO tablesDTO);
}
