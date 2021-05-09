package vn.isofh.may.tho.config;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class MethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

  @Autowired
  private RoleHierarchy roleHierarchy;

  @Override
  protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
      Authentication authentication, MethodInvocation invocation) {
    MethodSecurityExpressionRoot root = new MethodSecurityExpressionRoot(authentication);
    root.setThis(invocation.getThis());
    root.setPermissionEvaluator(new MethodSecurityPermissionEvaluator());
    root.setTrustResolver(getTrustResolver());
    root.setRoleHierarchy(roleHierarchy);
    root.setDefaultRolePrefix(getDefaultRolePrefix());

    return root;
  }

  @Bean
  public RoleHierarchy roleHierarchy(){
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_bo_y_te > ROLE_vu_ttb"
        + "\nROLE_vu_ttb > ROLE_so_y_te"
        + "\nROLE_so_y_te > ROLE_co_so_y_te"
        + "\nROLE_so_y_te > ROLE_cong_ty"
        + "\nROLE_cong_ty > ROLE_nhom_invitro"
        + "\nROLE_cong_ty > ROLE_nhom_ttbyt"
        + "\nROLE_cong_ty > ROLE_nhom_vtyt");
    return roleHierarchy;
  }
}
