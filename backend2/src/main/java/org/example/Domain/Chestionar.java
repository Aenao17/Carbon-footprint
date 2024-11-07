package org.example.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@jakarta.persistence.Entity
@Table( name = "Chestionare" )
public class Chestionar implements Entity<Integer> {
    Integer id;
    List<Intrebare> intrebari;

    public Chestionar(Integer id, List<Intrebare> intrebari) {
        this.id = id;
        this.intrebari = intrebari;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setIntrebari(List<Intrebare> intrebari) {
        this.intrebari = intrebari;
    }

    @Override
    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }


    @Column(name = "intrebari")
    public List<Intrebare> getIntrebari() {
        return intrebari;
    }
}
