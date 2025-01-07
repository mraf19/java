package programmerzamannow.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
@NamedQueries({
        @NamedQuery(name = "Brand.findAll", query = "select b from Brand b"),
        @NamedQuery(name = "Brand.findAllByName", query = "select b from Brand b where b.name = :name")
})
@NamedNativeQuery(name = "Brand.Native.findAll", query = "select * from brands", resultClass = Brand.class)
public class Brand extends AuditableEntity<String> {

    private String name;

    private String description;

    @Version
    private Long version;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
