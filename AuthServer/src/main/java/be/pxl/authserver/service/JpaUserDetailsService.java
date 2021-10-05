package be.pxl.authserver.service;

import be.pxl.authserver.details.MFAUser;
import be.pxl.authserver.entity.Authorities;
import be.pxl.authserver.entity.User;
import be.pxl.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final boolean DEFAULT_ACC_NON_EXP = true;
    private static final boolean DEFAULT_CRED_NON_EXP = true;
    private static final boolean DEFAULT_ACC_NON_LOCKED = true;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<String> authorities = new ArrayList<>();

        authorities.add(user.getRoleId());

        MFAUser springUser = new MFAUser(user.getUsername(),
                user.getPassword(),
                user.isVerified(),
                DEFAULT_ACC_NON_EXP,
                DEFAULT_CRED_NON_EXP,
                DEFAULT_ACC_NON_LOCKED,
                buildAuthorities(authorities));
        springUser.setEmail(user.getUsername() + "@email.be");
        springUser.setFirstName(user.getFirstName());
        springUser.setLastName(user.getLastName());
        return springUser;
    }

    private List<GrantedAuthority> buildAuthorities(List<String> authorities) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
        for(String authority : authorities) {
            authList.add(new SimpleGrantedAuthority(authority));
        }
        return authList;
    }
}
