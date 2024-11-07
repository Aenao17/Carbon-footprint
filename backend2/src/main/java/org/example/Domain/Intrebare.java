package org.example.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table( name = "Intrebari" )
public class Intrebare implements Entity<Integer> {
    Integer id;
    String intrebare;
    String raspuns;

    public Intrebare() {
        id = 0;
        intrebare = raspuns = "default";
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public void setRaspuns(String raspuns) {
        this.raspuns = raspuns;
    }


    @Column(name = "intrebare")
    public String getIntrebare() {
        return intrebare;
    }

    @Column(name = "raspuns")
    public String getRaspuns() {
        return raspuns;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    @Override
    @Id
    @Column(name = "id")
    public Integer getId() {
        return 0;
    }
}
