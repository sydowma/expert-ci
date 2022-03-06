package dev.magaofei.ci.server.step;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StepsRepository extends JpaRepository<Steps, Integer> {
}
