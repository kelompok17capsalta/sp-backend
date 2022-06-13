package com.sweetpoint.demo.controller;

import com.sweetpoint.demo.domain.dto.StoreDto;
import com.sweetpoint.demo.service.StoreService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public ResponseEntity<Object> get() {
        return storeService.getAllStore();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody StoreDto store){
        return storeService.createNewStore(store);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody StoreDto store) {
        return storeService.updateStore(id, store);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return storeService.deleteStore(id);
    }
}
