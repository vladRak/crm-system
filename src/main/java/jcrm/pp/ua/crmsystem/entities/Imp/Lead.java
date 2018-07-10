package jcrm.pp.ua.crmsystem.entities.Imp;


import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "lead")
@DiscriminatorValue("lead")
@EqualsAndHashCode(callSuper = true)
@Audited
@Data
public class Lead extends BaseBusinessObjImp implements RootAware<BaseClientImp>, Serializable{

    private static final long serialVersionUID = 1L;

    private String leadName;

    private String leadStatus;

    private Long budget;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImp client;

    @Override
    public BaseClientImp root() {
        return client;
    }

    public Lead() {
    }

    public void setClient(BaseClientImp client){
        setClient(client,true);
    }

    void setClient(BaseClientImp client, boolean add){
        this.client = client;
        if (client != null && add){
            client.addLead(this,false);
        }
    }
}
