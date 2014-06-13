/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotaw.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alberto
 */
@Entity
@Table(name = "tinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tinfo.findAll", query = "SELECT t FROM Tinfo t"),
    @NamedQuery(name = "Tinfo.findById", query = "SELECT t FROM Tinfo t WHERE t.id = :id"),
    @NamedQuery(name = "Tinfo.findByType", query = "SELECT t FROM Tinfo t WHERE t.type = :type"),
    @NamedQuery(name = "Tinfo.findByDate", query = "SELECT t FROM Tinfo t WHERE t.date = :date"),
    @NamedQuery(name = "Tinfo.findByDescription", query = "SELECT t FROM Tinfo t WHERE t.description = :description")})
public class Tinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 90)
    @Column(name = "description")
    private String description;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tinfo")
    private Tpatientinfo tpatientinfo;

    public Tinfo() {
    }

    public Tinfo(Integer id) {
        this.id = id;
    }

    public Tinfo(Integer id, int type, Date date) {
        this.id = id;
        this.type = type;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tpatientinfo getTpatientinfo() {
        return tpatientinfo;
    }

    public void setTpatientinfo(Tpatientinfo tpatientinfo) {
        this.tpatientinfo = tpatientinfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tinfo)) {
            return false;
        }
        Tinfo other = (Tinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectotaw.entity.Tinfo[ id=" + id + " ]";
    }
    
}
