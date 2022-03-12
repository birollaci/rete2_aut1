package hu.bme.aut.retelab2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Ad extends DateEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String cim;
    private String leiras;
    private int ar;
    private String kod;
    private LocalDateTime lejarat;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @ElementCollection
    private List<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCim() { return cim; }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public String getKod() { return kod; }

    public void setKod(String kod) { this.kod = kod; }

    public LocalDateTime getLejarat() { return lejarat; }

    public void setLejarat(LocalDateTime lejarat) { this.lejarat = lejarat; }
}
