import com.fasterxml.jackson.annotation.JsonIgnore
import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import javax.persistence.Entity
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/api")
class MyController {

    @GET
    fun entryPoint() = EntityFilterData(::addSomeFilter)

    private fun addSomeFilter(qf: Map<String, Any>, root: Root<MyEntity>, cb: CriteriaBuilder): Predicate? {
        return null
    }
}

class EntityFilterData<T : PanacheEntityBase>(

        // THE WARNING COMPLAINS ABOUT THIS CODE:
        @JsonIgnore
        val addCondition: ((@ParameterName(name = "queryFragment") Map<String, Any>,
                            @ParameterName(name = "root") Root<T>,
                            @ParameterName(name = "cb") CriteriaBuilder) -> Predicate?)? = null
)

@Entity
class MyEntity : PanacheEntity()