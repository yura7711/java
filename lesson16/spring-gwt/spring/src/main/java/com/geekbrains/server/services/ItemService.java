package com.geekbrains.server.services;

import com.geekbrains.gwt.common.ItemDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemService {
    private List<ItemDto> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
        items.add(new ItemDto(1L, "Milk"));
        items.add(new ItemDto(2L, "Bread"));
    }

    public ItemDto save(ItemDto itemDto) {
        items.add(itemDto);
        return itemDto;
    }

    public List<ItemDto> findAll() {
        return Collections.unmodifiableList(items);
    }

    public void remove(Long id) {
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
            }
        }
    }
}
