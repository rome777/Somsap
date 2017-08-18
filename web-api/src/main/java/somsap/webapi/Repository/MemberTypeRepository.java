package somsap.webapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import somsap.webapi.model.MemberType;
import somsap.webapi.model.Person;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "memberType", path = "memberType")
public interface MemberTypeRepository extends JpaRepository<MemberType, String> {
	List<MemberType> findByType(@Param("type") String type);
}
