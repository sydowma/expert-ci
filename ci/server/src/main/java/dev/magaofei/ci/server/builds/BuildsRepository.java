package dev.magaofei.ci.server.builds;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildsRepository extends JpaRepository<Builds, Integer> {
}
