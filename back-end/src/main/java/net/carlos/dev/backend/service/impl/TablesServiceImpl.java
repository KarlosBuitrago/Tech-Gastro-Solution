package net.carlos.dev.backend.service.impl;

import net.carlos.dev.backend.dto.TablesDTO;
import net.carlos.dev.backend.entities.Tables;
import net.carlos.dev.backend.mappers.TablesMapper;
import net.carlos.dev.backend.repositories.TablesRepository;
import net.carlos.dev.backend.service.ITablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ITablesService")
public class TablesServiceImpl implements ITablesService {

    @Autowired
    private TablesRepository tablesRepository;

    private final TablesMapper tablesMapper = TablesMapper.INSTANCE;

    @Override
    public List<TablesDTO> findAll() {
        return List.of();
    }

    @Override
    public TablesDTO findById(Long id) {
        return null;
    }

    @Override
    public TablesDTO save(TablesDTO tablesDTO) {
        if (tablesDTO != null || tablesDTO.getId() != null ){
            Tables tables = tablesMapper.toEntity(tablesDTO);
            if (tablesRepository.findById(tables.getId()).isEmpty() || tables.getId().equals(tablesDTO.getId())){
                return null;
            }else {
                tablesRepository.save(tables);
                return tablesMapper.toDTO(tables);
            }

        }
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public TablesDTO update(TablesDTO tablesDTO) {
        if (tablesDTO != null || tablesDTO.getId() != null ){
            Tables tables = tablesMapper.toEntity(tablesDTO);
            if (tablesRepository.findById(tables.getId()).isEmpty() || tables.getId().equals(tablesDTO.getId())){
                return null;
            }else {
                tablesRepository.save(tables);
                return tablesMapper.toDTO(tables);
            }

        }
        return null;
    }
}
