package com.pos.system.impl.Lookup;



import com.pos.system.entity.Lookup.TransactionType;
import com.pos.system.repository.lookup.TransactionTypeRepository;
import com.pos.system.service.Lookup.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    public void createIfNotExists(String name) {
        transactionTypeRepository.findByName(name)
                .orElseGet(() -> {
                    TransactionType type = new TransactionType();
                    type.setName(name);
                    return transactionTypeRepository.save(type);
                });
    }


}