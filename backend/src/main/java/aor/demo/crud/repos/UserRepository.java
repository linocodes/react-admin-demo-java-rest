package aor.demo.crud.repos;

import aor.demo.crud.entities.PlatformUser;

public interface UserRepository extends GenericRepository<PlatformUser> {
    PlatformUser findOneByUsername(String username);
}