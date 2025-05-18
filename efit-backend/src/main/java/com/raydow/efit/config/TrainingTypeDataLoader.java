package com.raydow.efit.config;

import com.raydow.efit.domain.TrainingType;
import com.raydow.efit.repository.TrainingTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeDataLoader implements CommandLineRunner {

    private final TrainingTypeRepository trainingTypeRepository;

    public TrainingTypeDataLoader(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        insertIfNotExists("Balanceado", "Treinos que promovem o equilíbrio muscular, coordenação e harmonia corporal.");
        insertIfNotExists("Cardio", "Exercícios focados em melhorar a capacidade cardiovascular e a resistência aeróbica.");
        insertIfNotExists("Força", "Treinamento destinado ao aumento da força muscular e desenvolvimento da massa magra.");
        insertIfNotExists("Flexibilidade", "Práticas voltadas para ampliar a amplitude de movimento e prevenir lesões musculares.");
    }

    private void insertIfNotExists(String name, String description) {
        if (!trainingTypeRepository.existsByName(name)) {
            TrainingType type = TrainingType.builder()
                .name(name)
                .description(description)
                .build();
            trainingTypeRepository.save(type);
        }
    }
}
