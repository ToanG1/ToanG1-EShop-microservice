package com.example.UserService.Admin.service;

import com.example.UserService.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("AdminStoreService")
@RequiredArgsConstructor
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;

    public void updateActiveStore(Long id) {
        storeRepository.findById(id).ifPresentOrElse(
                store -> {
                    store.setIsActive(!store.getIsActive());
                    storeRepository.save(store);
                    log.info("Store {} is {} now", id, store.getIsActive() ? "active" : "unactive");
                },
                () -> log.info("Store {} is not available", id));
    }

    public void deleteStore(Long id) {
        storeRepository.findById(id).ifPresentOrElse(
                store -> {
                    storeRepository.delete(store);
                    log.info("Store {} is deleted successfully", id);
                },
                () -> log.info("Store {} is not available", id)
        );
    }
}
