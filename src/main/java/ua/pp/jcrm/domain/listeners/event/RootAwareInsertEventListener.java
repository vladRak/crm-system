package ua.pp.jcrm.domain.listeners.event;

import ua.pp.jcrm.domain.entity.BaseTaskTargetImpl;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;

import java.util.Map;

public class RootAwareInsertEventListener implements PersistEventListener {

//    private static final Logger LOGGER =
//            LoggerFactory.getLogger(RootAwareInsertEventListener.class);

    public static final RootAwareInsertEventListener INSTANCE =
            new RootAwareInsertEventListener();

    @Override
    public void onPersist(PersistEvent event) throws HibernateException {
        final Object entity = event.getObject();

        if(entity instanceof RootAware) {
            RootAware rootAware = (RootAware) entity;
            BaseTaskTargetImpl root = (BaseTaskTargetImpl) rootAware.root();
            if (root != null && root.getId() != null) {
                event.getSession().lock(root, LockMode.OPTIMISTIC_FORCE_INCREMENT);
            }
//            LOGGER.info("Incrementing {} entity version because a {} child entity has been inserted",
//                    root, entity);
        }
    }

    @Override
    public void onPersist(PersistEvent event, Map createdAlready)
            throws HibernateException {
        onPersist(event);
    }
}
