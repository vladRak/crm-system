package jcrm.pp.ua.crmsystem.configs;

import jcrm.pp.ua.crmsystem.entities.User;
import jcrm.pp.ua.crmsystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    UserRepo userRepo;

    @Autowired
    EntityManager entityManager;

    private User auditor = null;
    private final static String SYSTEM = "system";

    public void setAuditor(User auditor) {
        this.auditor = auditor;
    }

    @Override
    public User getCurrentAuditor() {
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            return getSystem();
        }else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))
                || SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
            return getSystem();
        }else {
            User user = ((UserAwareUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUser();
            return user;
        }
    }

    private User getSystem(){
        entityManager.setFlushMode(FlushModeType.COMMIT);
        User system = userRepo.findByUsername(SYSTEM);
        entityManager.setFlushMode(FlushModeType.AUTO);
        return system;
    }
}
