package somsap.webapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import somsap.webapi.model.Member;
import somsap.webapi.model.MemberType;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "member", path = "member")
public interface MemberRepository extends JpaRepository<Member, String> {
}
