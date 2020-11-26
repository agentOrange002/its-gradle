/*
 * package sys.app.its;
 * 
 * import java.util.Arrays; import java.util.Collection;
 * 
 * import org.springframework.boot.context.event.ApplicationReadyEvent; import
 * org.springframework.context.event.EventListener; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.stereotype.Component; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import lombok.AllArgsConstructor; import sys.app.its.entity.AuthorityEntity;
 * import sys.app.its.entity.RoleEntity; import sys.app.its.entity.UserEntity;
 * import sys.app.its.enums.Roles; import
 * sys.app.its.repository.AuthorityRepository; import
 * sys.app.its.repository.RoleRepository; import
 * sys.app.its.repository.UserRepository; import sys.app.its.utility.Utility;
 * 
 * @AllArgsConstructor
 * 
 * @Component
 * 
 * @Transactional public class InitialSetup {
 * 
 * private UserRepository userRepository; private AuthorityRepository
 * authorityRepository; private RoleRepository roleRepository; private Utility
 * utility; private BCryptPasswordEncoder encoder;
 * 
 * @EventListener
 * 
 * @Transactional public void onApplicationEvent(ApplicationReadyEvent event) {
 * 
 * AuthorityEntity readAuthority = createAuthority("GET_USER_AUTHORITY");
 * AuthorityEntity writeAuthority = createAuthority("SAVE_USER_AUTHORITY");
 * AuthorityEntity deleteAuthority = createAuthority("DELETE_USER_AUTHORITY");
 * AuthorityEntity updateAuthority = createAuthority("UPDATE_USER_AUTHORITY");
 * 
 * AuthorityEntity a = createAuthority("DASHBOARD"); AuthorityEntity b =
 * createAuthority("SUPPORT_DASHBOARD"); AuthorityEntity c =
 * createAuthority("ISSUE_MAINTENEANCE"); AuthorityEntity d =
 * createAuthority("TICKET_MAINTENANCE"); AuthorityEntity e =
 * createAuthority("TASK_MAINTENANCE"); AuthorityEntity f =
 * createAuthority("USER_MAINTENANCE"); AuthorityEntity g =
 * createAuthority("ADMIN TOOLS"); AuthorityEntity h = createAuthority("SUPER");
 * AuthorityEntity i = createAuthority("MASTER");
 * 
 * RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(),
 * Arrays.asList(readAuthority, writeAuthority, deleteAuthority,
 * updateAuthority, a, b, c, d, e, f, g, h, i));
 * 
 * if (roleAdmin == null) return;
 * 
 * UserEntity adminUser = new UserEntity(); adminUser.setFirstName("Nehemias");
 * adminUser.setMiddleName("Cajurao"); adminUser.setLastName("Belong");
 * adminUser.setSuffixName("Jr");
 * adminUser.setFullName("Nehemias Cajurao Belong Jr");
 * adminUser.setEmail("nehemiasbelong@gmail.com");
 * adminUser.setEmailVerificationStatus(true);
 * adminUser.setUserId(utility.generateUserId(10));
 * adminUser.setEncryptedPassword(encoder.encode("password123"));
 * adminUser.setRoles(Arrays.asList(roleAdmin)); userRepository.save(adminUser);
 * }
 * 
 * @Transactional private AuthorityEntity createAuthority(String name) {
 * AuthorityEntity authority = authorityRepository.findByName(name); if
 * (authority == null) { authority = new AuthorityEntity();
 * authority.setName(name); authorityRepository.save(authority); } return
 * authority; }
 * 
 * @SuppressWarnings("null")
 * 
 * @Transactional private RoleEntity createRole(String name,
 * Collection<AuthorityEntity> authorities) { RoleEntity resultEntity = new
 * RoleEntity(); RoleEntity role = roleRepository.findByName(name); if (role ==
 * null) { role = new RoleEntity(); role.setName(name);
 * role.setAuthorities(authorities); resultEntity = roleRepository.save(role); }
 * else { for (AuthorityEntity auth : authorities) { if
 * (!role.getAuthorities().contains(auth)) { role.getAuthorities().add(auth); }
 * } resultEntity = roleRepository.save(role); } return resultEntity; }
 * 
 * }
 */