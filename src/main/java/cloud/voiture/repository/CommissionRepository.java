package cloud.voiture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.voiture.model.Commission;

public interface CommissionRepository extends JpaRepository<Commission,Long> {
    
}
